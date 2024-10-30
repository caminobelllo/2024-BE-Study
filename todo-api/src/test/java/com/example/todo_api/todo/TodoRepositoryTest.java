package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void todoSaveTest() {

        // given
        Todo todo = new Todo("todo_content", false, null);

        // when
        todoRepository.save(todo);

        // then
        assertThat(todo.getId()).isNotNull();

    }

    @Test
    @Transactional
    void todoFindOneByIdTest() {
        // given
        Todo todo = new Todo("todo_content", false, null);
        todoRepository.save(todo);

        todoRepository.flushAndClear();

        // when
        Todo findTodo = todoRepository.findById(todo.getId());

        // then
        assertThat(findTodo.getId()).isEqualTo(todo.getId());
    }

    @Test
    @Transactional
    void todoFindAllTest() {
        Todo todo1 = new Todo("todo_content1", false, null);
        Todo todo2 = new Todo("todo_content2", false, null);
        Todo todo3 = new Todo("todo_content3", false, null);

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> todoList = todoRepository.findAll();

        assertThat(todoList).hasSize(3);
    }


    @Test
    @Transactional
    void todoFindAllByMemberTest() {
        Member member1 = new Member("test_id_1", "test_password_1");
        Member member2 = new Member("test_id_2", "test_password_2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Todo todo1 = new Todo("todo_content1", false, member1);
        Todo todo2 = new Todo("todo_content2", false, member1);
        Todo todo3 = new Todo("todo_content3", false, member2);

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> member1TodoList = todoRepository.findAllByMember(member1);
        List<Todo> member2TodoList = todoRepository.findAllByMember(member2);

        assertThat(member1TodoList).hasSize(2);
        assertThat(member2TodoList).hasSize(1);

    }

    @Test
    @Transactional
    @Rollback(false)
    void todoUpdateTest() {
        Todo todo1 = new Todo("todo_content1", false, null);
        todoRepository.save(todo1);

        todoRepository.flushAndClear();

        Todo findTodo1 = todoRepository.findById(todo1.getId());
        findTodo1.updateContent("new_content");

    }


    @Test
    @Transactional
    @Rollback(false)
    void todoDeleteTest() {
        Todo todo1 = new Todo("todo_content_1", false, null);
        Todo todo2 = new Todo("todo_content2", false, null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        todoRepository.flushAndClear();

        todoRepository.deleteById(todo1.getId());

    }


    // in memory database
    @AfterAll
    public static void testFinished() {
        System.out.println("test finished");
        while (true) {}
    }
}
