package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // 비영속
//            Member member = new Member() ;
//            member.setId(101L);
//            member.setName("Hellojpa");

            // 영속
//            System.out.println("before");
//            em.persist(member);
//            System.out.println("after");

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            System.out.println(findMember1 == findMember2);

//            System.out.println(findMember1.getId());
//            System.out.println(findMember2.getId());
//            System.out.println(findMember.getName());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
