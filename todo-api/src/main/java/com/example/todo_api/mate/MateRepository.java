package com.example.todo_api.mate;

import com.example.todo_api.todo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MateRepository {
    @PersistenceContext
    private EntityManager em;

    // Create
    public void save(Mate mate) {
        em.persist(mate);
    }

    // Read
    // 1. 단건 조회
    public Mate findById(Long mateId) {
        return em.find(Mate.class, mateId);
    }

    // 2. 다건 조회
    public List<Mate> findAll() {
        return em.createQuery("select e from Mate as e", Mate.class).getResultList();
    }

    // Delete
    public void deleteById(Long mateId) {
        Mate mate = findById(mateId);
        em.remove(mate);
    }

    // only use for TEST
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
