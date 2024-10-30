package com.example.todo_api.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login_id", columnDefinition = "varchar(200)", nullable = false)
    private String login_id;

    @Column(name = "member_password", columnDefinition = "varchar(200)", nullable = false)
    private String password;

    public Member(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }
}
