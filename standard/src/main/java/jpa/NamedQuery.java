package jpa;

import jpa.domain.Member;
import jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class NamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            Team team = new Team();
            team.setName("teamA");

            Member member = new Member();
            member.setName("irostub");
            member.setTeam(team);

            em.persist(team);
            em.persist(member);

            em.flush();
            em.clear();

            List<Member> resultList = em.createNamedQuery("Member.myNamedQuery", Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println(member1.getName());
                System.out.println(member1.getTeam().getName());
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
}
