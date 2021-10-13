package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();

            Member member = new Member();
            member.setName("irostub");
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member as m where m.id=1L";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println(member1.getName());
            }

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
