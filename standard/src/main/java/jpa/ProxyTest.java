package jpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            Member m1 = new Member();
            m1.setName("iro");
            Member m2 = new Member();
            m1.setName("irostub");

            em.persist(m1);
            em.persist(m2);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, 1L);
            Member member = em.find(Member.class, 2L);
            System.out.println(member.getClass()+"\n "+reference.getClass());

            memberRefLoadCheck(reference);

            Hibernate.initialize(reference);

            memberRefLoadCheck(reference);

            em.flush();
            em.clear();

            reference = em.getReference(Member.class, 1L);
            member = em.find(Member.class, 2L);

            if(reference.getClass() == member.getClass()){
                System.out.println("equal class");
            }else{
                System.out.println("not equal class");
            }

            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }

    private static void memberRefLoadCheck(Member reference) {
        if(Persistence.getPersistenceUtil().isLoaded(reference)){
            System.out.println("isLoaded");
        }else{
            System.out.println("isNotLoaded");
        }
    }
}
