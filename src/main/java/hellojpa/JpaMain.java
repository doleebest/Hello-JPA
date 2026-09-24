package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setName("hello");

            em.persist(member);
            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println(findMember.getClass());
            System.out.println(findMember);
            System.out.println(findMember.getId());
            System.out.println(findMember.getName());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
