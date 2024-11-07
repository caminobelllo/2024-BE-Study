package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(readOnly = true)
    public List<Todo> getAllTodo(Long memberId) throws Exception{
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        return todoRepository.findAllByMember(member);
    }

    // 할 일 수정
    @Transactional
    public void updateTodo(Long todoId, String newContent, Long memberId) throws Exception {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if (todo == null) {
            throw new Exception("존재하지 않는 할 일 입니다.");
        }

        if (member == null) {
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        if (todo.getMember() != member) {
            throw new Exception("다른 유저의 할 일을 수정할 수 없습니다.");
        }

        todo.updateContent(newContent);
    }

    // 할 일 삭제
    @Transactional
    public void deleteContent(Long todoId, Long memberId) throws Exception {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if (todo == null) {
            throw new Exception("존재하지 않는 할 일 입니다.");
        }

        if (member == null) {
            throw new Exception("존재하지 않는 멤버입니다.");
        }

        if (todo.getMember() != member) {
            throw new Exception("다른 유저의 할 일을 삭제할 수 없습니다.");
        }
        
        todoRepository.deleteById(todoId);
        
    }
}
