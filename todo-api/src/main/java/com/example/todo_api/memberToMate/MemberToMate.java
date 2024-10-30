package com.example.todo_api.memberToMate;

import com.example.todo_api.mate.Mate;
import com.example.todo_api.member.Member;
import jakarta.persistence.*;

@Entity
public class MemberToMate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mate_id")
    private Long id;


    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "mate_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mate mate;
}
