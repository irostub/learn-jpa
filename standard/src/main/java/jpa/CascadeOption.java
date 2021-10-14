package jpa;

import jpa.domain.Child;
import jpa.domain.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CascadeOption {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent p1 = em.find(Parent.class, 1L);

            p1.getChildList().remove(0);

            em.remove(p1);

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
