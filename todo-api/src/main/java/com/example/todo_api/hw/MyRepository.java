package com.example.todo_api.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Getter
public class MyRepository {
    public void repositoryMethod() {
        System.out.println("repository");
    }
}
