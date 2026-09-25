package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Address address = new Address("CITY", "STREET" ,"ZIPCODE");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            member.setHomeAddress(address2); // 통으로 다 reset

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
