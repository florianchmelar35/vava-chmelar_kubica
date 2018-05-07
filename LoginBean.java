package service;

import model.Group;
import model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


@Stateless
@Remote (LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote{
//    Gson gson = new Gson();
    public LoginBean() {
    }

    public Group checkUser(Group group) {
//        return true;

        return group;

    }

    public int checkUser(User user) {

        Connection c = null;
        Statement stmt = null;
        File file;
        ResultSet rs;
        String temp = "org.postgresql.Driver";

        try {
            System.out.println("TAHA SUBOR");
            file = new File("C:\\DATA\\School\\4. semester\\VAVA\\project_OrganizeIT\\src\\DatabaseConnect.txt");
            System.out.println("NASLO SUBOR");
            Scanner sc = new Scanner(file);
            System.out.println("NASLO SUBOR");
            sc.nextLine();

            System.out.println("KOLKO SUBOR");
            Class.forName(temp).newInstance();
            System.out.println("POTOM SUBOR");
            c = DriverManager.getConnection(sc.nextLine(), sc.nextLine(), sc.nextLine());

            System.out.println("VTEDY SUBOR");
            stmt = c.createStatement();


            String querry = String.format("SELECT * FROM organizeit.user WHERE name = '%s'", user.getLogin());
            rs = stmt.executeQuery(querry);

            if(!rs.next())
                return -1;

            if (!rs.getString(2).equals(user.getPassword()))
                return -2;

            return rs.getInt(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -3;
    }

    @Override
    public List<Group> getData(int idUser) {
        return null;
    }

    @Override
    public boolean signUpUser(User user) {
        return false;
    }

    @Override
    public List<Group> refresh(User user, List<Group> list) {
        return null;
    }

    @Override
    public boolean logout(User user, List<Group> list) {
        return false;
    }


    public void init(){

    }


}
