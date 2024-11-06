package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks    // Mock 객체 주입
    private TodoService todoService;

    @Test
    public void createTodoTest() throws Exception{
        // given (테스트를 실행할 때 필요한 기본 환경 세팅)

        // memberRepository.findById(1L)이 호출 되는 순간, new Member()을 반환하도록 mocking 객체의 동작을 지정
        BDDMockito.given(memberRepository.findById(1L)).willReturn(new Member("id", "pw"));

        // when (테스트하려는 동작을 실행)
        todoService.createTodo("content", 1L);

        // then (해당 동작이 실행되었을 때, 우리가 기대하는 결과가 올바르게 나오는지 확인)

        // verify로 특정 메서드의 호출 여부 및 호출 횟수 검증 가능
        verify(todoRepository, times(1)).save(any(Todo.class));

    }
}
