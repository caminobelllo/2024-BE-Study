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

    @Column(name = "email", columnDefinition = "varchar(200)", nullable = false)
    private String email;

    @Column(name = "password", columnDefinition = "varchar(200)", nullable = false)
    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
