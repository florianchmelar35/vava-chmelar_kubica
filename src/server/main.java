package server;


import org.hibernate.jpa.HibernatePersistenceProvider;
import server.model.UserEntity;

import javax.persistence.*;
import javax.persistence.spi.PersistenceProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class main {

    public static final String SELECT_QUERY = "from UserEntity";

    public static void main(String[] args) {

        PersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("persistenceUnit", new HashMap());
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<UserEntity> users = entityManager.createQuery(SELECT_QUERY, UserEntity.class).getResultList();

        System.out.println(users);

//        UserEntity user = entityManager.find(UserEntity.class, 1);
//        List<UserEntity> list = new ArrayList<>();
//        list.add(entityManager.find(UserEntity.class, 1));

//        System.out.println(user.getId() + user.getName());


        try {
            entityManager.getTransaction().begin();
            for (int i=0; i<4; i++) {
                UserEntity temp = new UserEntity();

                temp.setName("Filip Bako");
                temp.setPassword("filipko");
                entityManager.persist(temp);

                    System.out.println("HEJ");
            }

            entityManager.getTransaction().commit();
        }
        catch (EntityExistsException e){
            System.out.println("Uz existuje");
        }

//        @Entity
//        public class Project {
//            @Id
//            @GeneratedValue long id; // still set automatically
//
//        }

        Query q1 = entityManager.createQuery("SELECT COUNT(p) FROM UserEntity p");
        System.out.println("Total Points: " + q1.getSingleResult());

        UserEntity user = entityManager.find(UserEntity.class, 2);

        System.out.println(user.getId() + user.getName());

        System.out.println("Zbehlo");
    }


}

