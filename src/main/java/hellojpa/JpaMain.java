package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Address address = new Address("homeCity", "STREET" ,"ZIPCODE");
            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);

            member.getFavoriteFoods().add("chicken");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1","street","10000"));
            member.getAddressHistory().add(new Address("old2","street","10000"));
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("-----");
            Member findMember = em.find(Member.class, member.getId());


            List<Address> addresses = findMember.getAddressHistory();
            for (Address a : addresses){
                System.out.println(a.getCity());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
