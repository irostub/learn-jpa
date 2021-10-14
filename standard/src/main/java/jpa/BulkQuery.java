package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BulkQuery {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();

            Team team1 = new Team();
            team1.setName("teamA");

            Team team2 = new Team();
            team2.setName("teamB");

            Member member1 = new Member();
            member1.setName("irostub");
            member1.setTeam(team1);

            Member member2 = new Member();
            member2.setName("iro");
            member2.setTeam(team1);

            Member member3 = new Member();
            member3.setName("stub");
            member3.setTeam(team2);

            em.persist(team1);
            em.persist(team2);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "update Member m set m.age=20";
            int i = em.createQuery(query).executeUpdate();
            System.out.println(i);

            //벌크 연산은 영속성 컨텍스트를 무시하므로 반드시 엔티티 매니저의 1차 캐시를 비운다.
            //비움으로써 벌크연산이 반영된 데이터를 DB로 부터 새로 받아서 사용
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
