package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaTest {
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = em.getCriteriaBuilder().createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("id"), 1L));
            List<Member> resultList = em.createQuery(cq).getResultList();

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
