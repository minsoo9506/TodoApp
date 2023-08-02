package com.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// TodoRepository가 없으면 아래처럼 기능들을 다 구현해야한다.
@Service
public class TodoServiceDeprecated {
    private static List<Todo> todos = new ArrayList<>();
    private static int toDosCount = 0;

    static {
        todos.add(new Todo(++toDosCount, "minsoo", "statistics", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++toDosCount, "minsoo", "python", LocalDate.now().plusMonths(3), false));
    }
    public List<Todo> findByUserName(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void  addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++toDosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo){
        deleteById(todo.getId());
        todos.add(todo);
    }
}
