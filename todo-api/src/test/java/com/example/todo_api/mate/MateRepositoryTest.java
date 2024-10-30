package com.example.todo_api.mate;

import com.example.todo_api.member.Member;
import com.example.todo_api.todo.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MateRepositoryTest {

    @Autowired
    private MateRepository mateRepository;

    @Test
    @Transactional
    @Rollback(false)
    void mateSaveTest() {
        // given
        Mate mate = new Mate();

        // when
        mateRepository.save(mate);

        // then
        assertThat(mate.getId()).isNotNull();
    }

    @Test
    @Transactional
    void mateFindOneByIdTest() {
        // given
        Mate mate = new Mate();
        mateRepository.save(mate);

        mateRepository.flushAndClear();

        // when
        Mate findMate = mateRepository.findById(mate.getId());

        // then
        assertThat(findMate.getId()).isEqualTo(mate.getId());
    }

    @Test
    @Transactional
    void mateFindAllTest() {
        // given
        Mate mate1 = new Mate();
        Mate mate2 = new Mate();
        Mate mate3 = new Mate();

        mateRepository.save(mate1);
        mateRepository.save(mate2);
        mateRepository.save(mate3);

        // when
        List<Mate> mateList = mateRepository.findAll();

        // then
        assertThat(mateList).hasSize(3);
    }

    @Test
    @Transactional
    @Rollback(false)
    void memberDeleteTest() {
        Mate mate1 = new Mate();
        Mate mate2 = new Mate();
        mateRepository.save(mate1);
        mateRepository.save(mate2);

        mateRepository.flushAndClear();

        mateRepository.deleteById(mate1.getId());

    }

    // in memory database
    @AfterAll
    public static void testFinished() {
        System.out.println("test finished");
        while (true) {}
    }
}
