package jpa;

import jpa.domain.Member;

import javax.persistence.*;
import java.util.List;

public class FindTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();

            List<Member> resultList = em.createQuery("select m from Member as m where m.id=1", Member.class)
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getName());
                System.out.println("member.getId() = " + member.getId());
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
