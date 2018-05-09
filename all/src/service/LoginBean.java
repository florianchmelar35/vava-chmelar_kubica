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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hlavna Stateless beana na kominukovanie medzi serverom a clientom
 */

@Stateless
@Remote (LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote{
    Logger log;

    /**
     * metoda na prihlasenie uzivatela a vratenia jeho id
     * @param user
     * @return
     */
    @Override
    public int checkUser(User user) {
        log = LogClass.getLog();
        log.log(Level.FINER, "Login user = " + user.getLogin());

        Connection c = null;
        Statement stmt;
        ResultSet rs;

        try {
            c = log(c);

            stmt = c.createStatement();

            String querry = String.format("SELECT * FROM organizeit.user WHERE name = '%s'", user.getLogin());
            rs = stmt.executeQuery(querry);

            c.close();

            if(!rs.next()){
                log.log(Level.CONFIG, "User is not in DB ", user.getLogin());
                return -1;
            }
            if (!rs.getString(3).equals(user.getPassword())){
                log.log(Level.CONFIG, "Password incorrect, user", user.getLogin());
                return -2;
            }

            return rs.getInt(1);

        } catch (Exception e) {
            log.log(Level.SEVERE, "checkUser exception ", e);
//            e.printStackTrace();
        }

        return -3;
    }


    /**
     * metoda pre ziskanie vsetkych dostupnych dat pre uzivatela
     * @param idUser
     * @return
     */
    @Override
    public List<Group> getData(int idUser) {
        log = LogClass.getLog();
        log.log(Level.FINER, "get data, idUser: " + idUser);

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
                log.log(Level.FINEST, "get data - group, admin, idGroup: %d ", rs.getInt(1));


                querry = String.format("SELECT * FROM organizeit.event e " +
                        "WHERE e.id_group ='%d'", g.getId());
                rs2 = stmt2.executeQuery(querry);

                while(rs2.next()){
                    e = new Event(rs2.getString(3), rs2.getString(4), rs2.getTimestamp(5), rs2.getString(6));
                    events.add(e);
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
                log.log(Level.FINEST, "get data - group, partOf, idGroup: %d ", rs.getInt(4));

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
            log.log(Level.SEVERE, "get data, exc " + idUser, e1);
//            e1.printStackTrace();
        }

        return null;
    }


    /**
     * metoda pre zaregistrovanie usera v databaze a vytvoreniu jemu osobnej grupy
     * @param user
     * @return
     */
    @Override
    public boolean signUpUser(User user) {
        log = LogClass.getLog();
        log.log(Level.FINER, "get data, idUser: " + user.getId() );
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
            if(rs.getInt(1) > 0){
                log.log(Level.CONFIG, "signUp, user exist = " + user.getLogin() );
                return false;
            }


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
            return true;

        }
        catch (Exception e){
            if (c != null){
                try {
                    log.log(Level.INFO, "signUp, roll back, user = " + user.getLogin() );
                    System.err.print("Roll back");
                    c.rollback();
                } catch (SQLException e1) {
                    log.log(Level.SEVERE, "signUp, rollback SQL, user = " + user.getLogin(), e1);
//                    e1.printStackTrace();
                }
            }
            log.log(Level.SEVERE, "signUp, user = %s " + user.getLogin(), e);
//            e.printStackTrace();
        }
        finally {
            try {
                c.setAutoCommit(true);
                c.close();
            } catch (SQLException e) {
                log.log(Level.WARNING, "signUp, autocommit to true, user = " + user.getLogin(), e);
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * metoda pre aktualizovanie dat a ziskanie najnovsich
     * @param user
     * @param list
     * @param deleted
     * @return
     */
    @Override
    public List<Group> refresh(User user, List<Group> list, List<Integer> deleted) {
        logout(user, list, deleted);
        return getData(user.getId());
    }

    /**
     * metoda, kde sa uzivatelove data aktualizuju v databaze
     * @param user
     * @param list
     * @param deleted
     * @return
     */
    @Override
    public boolean logout(User user, List<Group> list, List<Integer> deleted) {
        log = LogClass.getLog();
        log.log(Level.FINER, "logOut, idUser: %d " + user.getId() );

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
            for(Group all : list) {
                if (all.getId() == -1) {
                    if (all.getIdUser() != user.getId())       //ak nieje spravca, nemoze pridat
                        continue;

                    insertPreparedStatement = "INSERT INTO organizeit.group(name, id_user, personal) VALUES (?, ?, FALSE )";

                    pStmt = c.prepareStatement(insertPreparedStatement);
                    pStmt.setString(1, all.getName());
                    pStmt.setInt(2, user.getId());
                    pStmt.execute();
                }
            }


//            upravit existujuce grupy
            for (Group all : list){
                if(all.getIdUser() != user.getId() || all.getId() == -1)       //ak nieje spravca, nemoze pridat
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
                    log.log(Level.INFO, "logOut, roll back, user = %s " + user.getLogin() );
                    System.err.print("Roll back");
                    c.rollback();
                } catch (SQLException e1) {
                    log.log(Level.SEVERE, "logOut, rollback SQL, user = %s " + user.getLogin(), e1);
//                    e1.printStackTrace();
                }
            }
            log.log(Level.SEVERE, "logOut, user = %s " + user.getLogin(), e);
//            e.printStackTrace();
        }
        finally {
            try {
                c.setAutoCommit(true);
            } catch (SQLException e) {
                log.log(Level.WARNING, "logOut, autocommit to true, user = %s " + user.getLogin(), e);
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * metoda na pridanie grupy pre pouzivatela, cize ju moze prezerat
     * @param idUser
     * @param idGroup
     * @return
     */
    @Override
    public Group addGroup(int idUser, int idGroup){
        log = LogClass.getLog();
        log.log(Level.FINER, "addGroup, idUser: %d " + idUser);

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
            if(!rs.next()){
                log.log(Level.CONFIG, "group doesn't exists, idUser: %d " + idUser);
                return null;
            }

//            je sukromna alebo je admin
            if (rs.getInt(3) == idUser || rs.getBoolean(4)){
                log.log(Level.CONFIG, "private or admin, idUser: %d " + idUser);
                return null;
            }

            Group group = new Group(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));

//            ci uz nieje prideleny
            querry = String.format("SELECT count(*) FROM organizeit.part_of g " +
                    "WHERE id_user = '%d' AND id_group = '%d'", idUser, idGroup);
            rs = stmt.executeQuery(querry);
            rs.next();

            if(rs.getInt(1) > 0){
                log.log(Level.CONFIG, "exist, idUser: %d " + idUser);
                return null;
            }

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
            log.log(Level.SEVERE, "", e);
//            e.printStackTrace();
        }

        return null;
    }

    /**
     * metoda pre zrusenie prehliadania inej grupy nez osobnej
     * @param idUser
     * @param idGroup
     * @return
     */
    @Override
    public boolean leaveGroup(int idUser, int idGroup) {
        log = LogClass.getLog();
        log.log(Level.FINER, "idUser: " + idGroup );
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
            if(!rs.next()){
                log.log(Level.CONFIG, "isnt connected, idUser: " + idUser );
                return false;
            }

//            vymazanie daneho spojenia
            int id = rs.getInt(1);

            querry = String.format("DELETE FROM organizeit.part_of WHERE id='%d'", id);
            stmt.executeUpdate(querry);

            return true;

        }
        catch (Exception e){
            log.log(Level.SEVERE, "", e);
            e.printStackTrace();
        }


        return false;
    }

    /**
     * metoda pre vytvorenie spojenia s databazou
     * @param c
     * @return
     * @throws Exception
     */
    private Connection log(Connection c) throws Exception{
        File file = new File("C:\\DATA\\School\\4. semester\\VAVA\\project_OrganizeIT\\src\\DatabaseConnect.txt");
        Scanner sc = new Scanner(file);
        sc.nextLine();
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection(sc.nextLine(), sc.nextLine(), sc.nextLine());

        return c;
    }


}
