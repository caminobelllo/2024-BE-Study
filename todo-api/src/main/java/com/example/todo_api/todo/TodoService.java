package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    // 할 일 생성
    @Transactional
    public void createTodo(String content, Long memberId) throws Exception{
        // 멤버 객체를 식별할 수 있는 PK만 받아도 구분 가능 : memberId

        Member member = memberRepository.findById(memberId);

        // 예외 처리 : 존재하지 않는 memberId인 경우
        if (member == null) {
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        Todo todo = new Todo(content, member);

        todoRepository.save(todo);
    }

    // 할 일 조회 (특정 멤버의 모든 할 일 조회)

    // 할 일 수정

    // 할 일 삭제
}
