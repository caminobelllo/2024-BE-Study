package com.example.todo_api.member;

import com.example.todo_api.todo.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void memberSaveTest() {
        // given
        Member member = new Member("test_id", "test_password");

        // when
        memberRepository.save(member);

        // then
        assertThat(member.getId()).isNotNull();
    }

    @Test
    @Transactional
    void memberFindOneByIdTest() {
        // given
        Member member1 = new Member("test_id", "test_password");

        memberRepository.save(member1);

        memberRepository.flushAndClear();

        // when
        Member findMember = memberRepository.findById(member1.getId());

        // then
        assertThat(findMember.getId()).isEqualTo(member1.getId());
    }

    @Test
    @Transactional
    void memberFindAllTest() {
        // given
        Member member1 = new Member("test_id_1", "test_password_1");
        Member member2 = new Member("test_id_2", "test_password_2");
        Member member3 = new Member("test_id_3", "test_password_3");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList).hasSize(3);
    }

    @Test
    @Transactional
    @Rollback(false)
    void memberDeleteTest() {
        Member member1 = new Member("test_id_1", "test_password_1");
        Member member2 = new Member("test_id_2", "test_password_2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        memberRepository.flushAndClear();

        memberRepository.deleteById(member1.getId());

    }

    // in memory database
    @AfterAll
    public static void testFinished() {
        System.out.println("test finished");
        while (true) {}
    }
}
