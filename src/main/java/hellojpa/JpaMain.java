package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TEAM A");
            em.persist(team);


            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());

            Team findMemberTeam = findMember.getTeam();
            System.out.println(findMemberTeam.getName());

            // 100 번 팀이 있고, 그 팀으로 바꾸고 싶으면
            // 팀 변경하기 (연관관계 수정하기)
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
