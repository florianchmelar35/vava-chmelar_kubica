package service;

import model.Event;
import model.Group;
import model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Stateless
@Remote (LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote{
    public LoginBean() {
    }

    @Override
    public int checkUser(User user) {

        Connection c = null;
        Statement stmt;
        ResultSet rs;

        try {
            c = log(c);

            stmt = c.createStatement();

            String querry = String.format("SELECT * FROM organizeit.user WHERE name = '%s'", user.getLogin());
            rs = stmt.executeQuery(querry);

            c.close();

            if(!rs.next())
                return -1;
            if (!rs.getString(3).equals(user.getPassword()))
                return -2;

            return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -3;
    }

    @Override
    public List<Group> getData(int idUser) {
        Connection c = null;
        Statement stmt, stmt2;
        ResultSet rs, rs2;
        Group g;
        Event e;
        List<Event> events;


        try {
            c = log(c);

            stmt = c.createStatement();
            stmt2 = c.createStatement();
            System.out.println("ID USER = " + idUser);

            String querry = String.format("SELECT * FROM organizeit.group g " +
                    "WHERE g.id_user = '%d'", idUser);
            rs = stmt.executeQuery(querry);

            List<Group> list = new ArrayList<>();

//            vyberie vsetky grupy, kde je spravcom
            while(rs.next()) {

                g = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                events = new ArrayList<>();


                querry = String.format("SELECT * FROM organizeit.event e " +
                        "WHERE e.id_group ='%d'", g.getId());
                rs2 = stmt2.executeQuery(querry);

                while(rs2.next()){
                    e = new Event(rs2.getString(3), rs2.getString(4), rs2.getTimestamp(5), rs2.getString(6));
                    events.add(e);
                    System.out.println("\tOUT");
                }

                g.setEvents(events);
                list.add(g);
            }

//            vybera grupy, v ktorych je pripojeny
            querry = String.format("SELECT * from organizeit.part_of p " +
                    "JOIN organizeit.group g ON g.id=p.id_group " +
                    "WHERE p.id_user='%d'", idUser);
            rs = stmt.executeQuery(querry);

            while(rs.next()){

//                    pre grupu vyberie zoznam eventov
                querry = String.format("SELECT * FROM organizeit.event e " +
                        "WHERE e.id_group ='%d'", rs.getInt(4));
                rs2 = stmt2.executeQuery(querry);

                g = new Group(rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7));
                events = new ArrayList<>();

                while(rs2.next()){
                    e = new Event(rs2.getString(3), rs2.getString(4), rs2.getTimestamp(5), rs2.getString(6));
                    events.add(e);
                }
                g.setEvents(events);
                list.add(g);
            }

            c.close();
            return list;
        }

        catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean signUpUser(User user) {
        Connection c = null;
        Statement stmt;
        PreparedStatement pStmt;
        ResultSet rs;

        try {
            c = log(c);

            stmt = c.createStatement();

            String querry = String.format("SELECT count(*) FROM organizeit.user WHERE name ='%s'", user.getLogin());
            rs = stmt.executeQuery(querry);
            rs.next();
            if(rs.getInt(1) > 0)
                return false;


            String insertPreparedStatement = "INSERT INTO organizeit.user(name, password) VALUES (?, ?)";
            c.setAutoCommit(false);
            pStmt = c.prepareStatement(insertPreparedStatement);
            pStmt.setString(1, user.getLogin());
            pStmt.setString(2, user.getPassword());
            pStmt.execute();


//            vyhladanie priradeneho id
            querry = String.format("SELECT * FROM organizeit.user WHERE name = '%s'", user.getLogin());
            rs = stmt.executeQuery(querry);
            rs.next();
            user.setId(rs.getInt(1));

//            vytvorenie osobnej grupy
            insertPreparedStatement = "INSERT INTO organizeit.group(name, id_user, personal) VALUES (?, ?, TRUE)";
            c.setAutoCommit(false);
            pStmt = c.prepareStatement(insertPreparedStatement);
            pStmt.setString(1, user.getLogin());
            pStmt.setInt(2, user.getId());
            pStmt.execute();

            c.commit();
            c.close();
            return true;

        }
        catch (Exception e){
            if (c != null){
                try {
                    System.err.print("Roll back");
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            try {
                c.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Group> refresh(User user, List<Group> list, List<Integer> deleted, List<Integer> created) {
        logout(user, list, deleted, created);
        return getData(user.getId());
    }

    @Override
    public boolean logout(User user, List<Group> list, List<Integer> deleted, List<Integer> created) {
        Connection c = null;
        PreparedStatement pStmt;
        String insertPreparedStatement;

        try {
            c = log(c);

            c.setAutoCommit(false);


//            zmazanie vstekych zmazanych grup
            if (deleted != null)
                for (Integer del : deleted) {
                    insertPreparedStatement = "DELETE FROM organizeit.group WHERE id=?";
                    pStmt = c.prepareStatement(insertPreparedStatement);
                    pStmt.setInt(1, del);
                    pStmt.execute();
                }

//            vytvorit nove grupy
            if (created != null)
                for (Integer add : created) {
                    if(list.get(add).getIdUser() != user.getId())       //ak nieje spravca, nemoze pridat
                        continue;

                    insertPreparedStatement = "INSERT INTO organizeit.group(name, id_user, personal) VALUES (?, ?, FALSE )";

                    pStmt = c.prepareStatement(insertPreparedStatement);
                    pStmt.setString(1, list.get(add).getName());
                    pStmt.setInt(2, user.getId());
                    pStmt.execute();

                }


//            upravit existujuce grupy
            for (Group all : list){
                if(all.getIdUser() != user.getId())       //ak nieje spravca, nemoze pridat
                    continue;

                insertPreparedStatement = "DELETE FROM organizeit.event WHERE id_group=?";
                pStmt = c.prepareStatement(insertPreparedStatement);
                pStmt.setInt(1, all.getId());
                pStmt.execute();

                for(Event event : all.getEvents()){
                    insertPreparedStatement = "INSERT INTO organizeit.event(id_group, name, comment, date, place) " +
                            "VALUES (?, ?, ?, ?, ?)";
                    pStmt = c.prepareStatement(insertPreparedStatement);
                    pStmt.setInt(1, all.getId());
                    pStmt.setString(2, event.getName());
                    pStmt.setString(3, event.getComment());
                    pStmt.setTimestamp(4, event.getDate());
                    pStmt.setString(5, event.getPlace());
                    pStmt.execute();
                }
            }

            c.commit();
//            c.close();
            return true;

        }
        catch (Exception e){
            if (c != null){
                try {
                    System.err.print("Rolll back");
                    c.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }

        finally {
            try{
                c.setAutoCommit(true);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Group addGroup(int idUser, int idGroup){
        Connection c = null;
        Statement stmt;
        ResultSet rs;

        try {
            c = log(c);

            stmt = c.createStatement();

//            najdenie danej grupy
            String querry = String.format("SELECT * FROM organizeit.group WHERE id = '%d'", idGroup);
            rs = stmt.executeQuery(querry);

//            nieje
            if(!rs.next())
                return null;

//            je sukromna alebo je admin
            if (rs.getInt(3) == idUser || rs.getBoolean(4))
                return null;

            Group group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));

//            ci uz nieje prideleny
            querry = String.format("SELECT count(*) FROM organizeit.part_of g " +
                    "WHERE id_user = '%d' AND id_group = '%d'", idUser, idGroup);
            rs = stmt.executeQuery(querry);
            rs.next();

            if(rs.getInt(1) > 0)
                return null;

//            vlozenie prepojenia medzi userom a grupou
            querry = String.format("INSERT INTO organizeit.part_of(id_user, id_group) " +
                    "VALUES ('%d', '%d')", idUser, idGroup);
            stmt.executeUpdate(querry);

            querry = String.format("SELECT * FROM organizeit.event e " +
                    "WHERE e.id_group = '%d'", idGroup);
            rs = stmt.executeQuery(querry);

            List<Event> events = new ArrayList<>();

            while(rs.next()){
                Event e = new Event(rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6));
                events.add(e);
            }
            group.setEvents(events);


            c.close();
            return group;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean leaveGroup(int idUser, int idGroup) {
        Connection c = null;
        Statement stmt;
        ResultSet rs;

        try {
            c = log(c);

            stmt = c.createStatement();

//            najdenie spojenia medzi userom a grupou
            String querry = String.format("SELECT * FROM organizeit.part_of g " +
                    "WHERE id_user = '%d' AND id_group = '%d'", idUser, idGroup);
            rs = stmt.executeQuery(querry);
            if(!rs.next())
                return false;

//            vymazanie daneho spojenia
            int id = rs.getInt(1);

            querry = String.format("DELETE FROM organizeit.part_of WHERE id='%d'", id);
            stmt.executeUpdate(querry);

            return true;

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

    private Connection log(Connection c) throws Exception{
        File file = new File("C:\\DATA\\School\\4. semester\\VAVA\\project_OrganizeIT\\src\\DatabaseConnect.txt");
        Scanner sc = new Scanner(file);
        sc.nextLine();
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection(sc.nextLine(), sc.nextLine(), sc.nextLine());

        return c;
    }


}
