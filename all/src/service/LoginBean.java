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
        Statement stmt;
        ResultSet rs, rs2;

        try {
            c = log(c);

            stmt = c.createStatement();

            String querry = String.format("SELECT * FROM organizeit.group g " +
                    "WHERE g.id_user='%d'", idUser);
            rs = stmt.executeQuery(querry);
            if(!rs.next())
                return null;

            List<Group> list = new ArrayList<>();

            Group g = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
            List<Event> events = new ArrayList<>();

            querry = String.format("SELECT * FROM organizeit.event e " +
                    "WHERE e.id_group ='%s'", g.getId());
            rs = stmt.executeQuery(querry);

            while(rs.next()){
                Event e = new Event(rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6));
                events.add(e);
            }
            g.setEvents(events);
            list.add(g);

//            vyberie najskor osobnu grupu a potom ostatne
            querry = String.format("SELECT * from organizeit.part_of p " +
                    "JOIN organizeit.group g ON g.id=p.id_group " +
                    "WHERE p.id_user='%d'", idUser);
            rs = stmt.executeQuery(querry);

            while(rs.next()){

//                    pre grupu vyberie zoznam eventov
                querry = String.format("SELECT * FROM organizeit.event e " +
                        "WHERE e.id_group ='%s'", rs.getInt(4));
                rs2 = stmt.executeQuery(querry);

                g = new Group(rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7));
                events = new ArrayList<>();

                while(rs2.next()){
                    Event e = new Event(rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6));
                    events.add(e);
                }
                g.setEvents(events);
                list.add(g);
            }

            c.close();
            return list;
        }

        catch (Exception e) {
            e.printStackTrace();
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
            c.commit();
            c.setAutoCommit(true);

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
            c.setAutoCommit(true);

            c.close();
            return true;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Group> refresh(User user, List<Group> list, List<Integer> deleted, List<Integer> created) {
        return null;
    }

    @Override
    public boolean logout(User user, List<Group> list, List<Integer> deleted, List<Integer> created) {
        return false;
    }

    Connection log(Connection c) throws Exception{
        File file = new File("C:\\DATA\\School\\4. semester\\VAVA\\project_OrganizeIT\\src\\DatabaseConnect.txt");
        Scanner sc = new Scanner(file);
        sc.nextLine();
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection(sc.nextLine(), sc.nextLine(), sc.nextLine());

        return c;
    }


}
