package com.example.todo_api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 생성
    @Transactional
    public void registerMember(String email, String password) {
    
    }

    // 멤버 조회

    // 멤버 삭제
}
