package service;

import model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryTodoService implements TodoService{

    private List<Todo> todos;

    public InMemoryTodoService() {
        if(todos == null) {
            todos = new ArrayList<>();
            seed();
        }
    }

    private void seed() {
        create(new Todo(1, "Walk dog"));
        create(new Todo(1, "Vacuum living room"));
        create(new Todo(2, "Do dishes", true));
        create(new Todo(3, "Buy groceries"));
        create(new Todo(3, "Mow lawn", true));
        create(new Todo(4, "Pet dog"));
        create(new Todo(5, "Feed hamster"));
    }

    @Override
    public void create(Todo newTodo) {
        int nextId = 0;

        for (Todo todo : todos) {
            if(todo.getTodoId() > nextId)
                nextId = todo.getTodoId();
        }
        nextId++;
        newTodo.setTodoId(nextId);
        todos.add(newTodo);
    }

    @Override
    public List<Todo> get(Function<Todo, Boolean> criteria) {
        if(criteria == null)
            return new ArrayList<>(todos);

        List<Todo> result = new ArrayList<>();
        for (Todo todo : todos) {
            if (criteria.apply(todo)) {
                result.add(todo);
            }
        }
        return result;
    }

    @Override
    public Todo update(Todo todo) {
        Optional<Todo> first = todos.stream().filter(t -> t.getTodoId() == todo.getTodoId()).findFirst();
        if(first.isEmpty())
            throw new RuntimeException("Todo not found");
        Todo updated = first.get();
        updated.setCompleted(todo.isCompleted());
        updated.setBody(todo.getBody());
        return updated;
    }

    @Override
    public void delete(int todoId) {
        todos.removeIf(todo -> todo.getTodoId() == todoId);
    }
}
