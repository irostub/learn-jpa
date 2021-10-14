package jpa;

import jpa.domain.Member;
import jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class ImplicitJoinQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("standard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();

            Team team = new Team();
            team.setName("TeamA");

            Member member1 = new Member();
            member1.setName("irostub");
            member1.setTeam(team);

            Member member2 = new Member();
            member2.setName("iro");
            member2.setTeam(team);

            em.persist(team);
            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            //상태 필드 경로 표현식
            String stateFieldQuery = "select m.name from Member m where m.id=1L";
            List<String> resultList = em.createQuery(stateFieldQuery, String.class).getResultList();
            for (String memberName : resultList) {
                System.out.println(memberName);
            }

            em.flush();
            em.clear();

            //단일 값 연관 경로 표현식
            String singleValueAssociationQuery = "select m.team.name from Member m where m.id=1L";
            String result = em.createQuery(singleValueAssociationQuery, String.class).getSingleResult();
            System.out.println(result);

            em.flush();
            em.clear();

            //컬렉션 값 연관 경로 표현식
            String collectionValueAssociationQuery = "select t.members from Team t where t.name='TeamA'";
            List<Collection> members = em.createQuery(collectionValueAssociationQuery, Collection.class).getResultList();
            System.out.println(members);

            em.flush();
            em.clear();

            //명시적 조인 경로 표현식
            String query = "select t from Team as t JOIN t.members where t.name='TeamA'";
            List<Member> mlist = em.createQuery(query, Member.class).getResultList();
            for (Member member : mlist) {
                System.out.println(member.getName());
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
