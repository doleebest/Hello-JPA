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

            // 값 타입 수정: 갈아끼우기
            findMember.setHomeAddress(new Address("newCity", "street","10000"));

            // 컬렉션 타입 수정: string을 없애고 새로 추가
            findMember.getFavoriteFoods().remove("chicken");
            findMember.getFavoriteFoods().remove("koreanFood");

            // 컬렉션 타입 수정: equals, hashcode 가 중요해지는 시점
            findMember.getAddressHistory().remove(new Address("old1","street","10000"));
            findMember.getAddressHistory().add(new Address("newnew1","street","10000"));

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
