package com.example.todo_api.member;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login_id", columnDefinition = "varchar(200)", nullable = false)
    private String login_id;

    @Column(name = "member_password", columnDefinition = "varchar(200)", nullable = false)
    private String password;
}
