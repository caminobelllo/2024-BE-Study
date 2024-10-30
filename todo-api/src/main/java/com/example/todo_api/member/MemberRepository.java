package com.example.todo_api.member;

import com.example.todo_api.todo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }

    // only use for TEST
    public void flushAndClear() {
        em.flush();
        em.clear();
    }

}
