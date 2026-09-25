package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            em.clear();

            Address address = new Address("CITY", "STREET" ,"ZIPCODE");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address address1 = new Address(address.getCity(), address.getCity(), address.getZipcode());
            Member member2 = new Member();
            member.setName("member2");
            member.setHomeAddress(address1);
            em.persist(member2);

//            member.getHomeAddress().setCity("new new");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
