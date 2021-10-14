package jpa;

import jpa.domain.Book;
import jpa.domain.Item;
import jpa.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PolymorphismQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            Item item1 = new Book();
            item1.setName("book item");
            Item item2 = new Movie();
            item2.setName("movie item");

            em.persist(item1);
            em.persist(item2);

            em.flush();
            em.clear();

            Item singleResult = em.createQuery("select i from Item i where type(i) in (Book)", Item.class).getSingleResult();

            System.out.println(singleResult.getName());
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}
