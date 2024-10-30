package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    @PersistenceContext
    private EntityManager em;

    // Create
    public void save(Todo todo) {
        em.persist(todo);
    }

    // Read

    // 1. 단건 조회 (한 개의 데이터 조회)
    public Todo findById(Long todoId) {
        return em.find(Todo.class, todoId);
    }

    // 2. 다건 조회 (여러 개의 데이터 조회)
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo as t", Todo.class).getResultList();
    }

    // 3. 조건 조회 (여러 개의 데이터 중에 조건에 맞는 데이터만 조회)
    public List<Todo> findAllByMember(Member member) {
        return em.createQuery("select t from Todo as t where t.member = :todo_member", Todo.class)
                .setParameter("todo_member", member)        // todo_member에 메서드에서 받아온 member를 넘겨줌
                .getResultList();
    }

    // Update
    // 엔티티 클래스와 필드를 수정하는 메서드를 작성하여 수정 (updateContent)

    // Delete
    public void deleteById(Long todoId) {
        Todo todo = findById(todoId);
        em.remove(todo);
    }

    // only use for TEST
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
