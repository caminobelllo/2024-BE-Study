package com.example.todo_api.mate;

import jakarta.persistence.*;

@Entity
public class Mate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mate_id")
    private Long id;
}

