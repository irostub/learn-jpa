package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class NativeQueryTest {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();

            Member member = new Member();
            member.setName("irostub");
            em.persist(member);

            List resultList = em.createNativeQuery("select * from member where id = 1", Member.class).getResultList();
            for (Object member1 : resultList) {
                Member member11 = (Member) member1;
                System.out.println(member11.getName());
            }
            em.flush();
            em.clear();

            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
