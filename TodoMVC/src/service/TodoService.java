package service;

import model.Todo;

import java.util.List;
import java.util.function.Function;

public interface TodoService {

    void create(Todo todo);
    List<Todo> get(Function<Todo, Boolean> criteria);
    Todo update(Todo todo);
    void delete(int todoId);

}
