package com.example.todo_api.mate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Mate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mate_id")
    private Long id;
}

