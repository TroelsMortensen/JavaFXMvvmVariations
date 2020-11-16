package views.display;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Todo;
import services.TodoService;

import java.util.List;

public class DisplayVM {

    private final TodoService todoService;
    private ObservableList<Todo> todos;

    private Todo selectedTodo;
    private StringProperty userIdSearchProperty = new SimpleStringProperty();
    private Boolean searchByIsCompleted;

    public DisplayVM(TodoService todoService) {
        this.todoService = todoService;
        todos = FXCollections.observableArrayList();
    }

    public ObservableList<Todo> getTodos() {
        return todos;
    }

    public void updateTodo(Todo todo) {
        todoService.update(todo);
    }

    public void setSelected(Todo newValue) {
        selectedTodo = newValue;
    }

    public void search() {
        List<Todo> result = todoService.get(
                todo -> {
                    String value = userIdSearchProperty.getValue();
                    boolean b = (value != null && !"".equals(value) && Integer.parseInt(value) == todo.getUserId() || (value == null || "".equals(value))) &&
                            (searchByIsCompleted != null && searchByIsCompleted == todo.isCompleted() || searchByIsCompleted == null);
                    return b;
                });
        todos.clear();
        todos.addAll(result);
    }

    public void delete() {
        if (selectedTodo != null) {
            todoService.delete(selectedTodo.getTodoId());
            todos.remove(selectedTodo);
        }
    }

    public StringProperty userIdSearchProperty() {
        return userIdSearchProperty;
    }

    public void setSearchByCompleted(String newValue) {
        if("both".equalsIgnoreCase(newValue)) {
            searchByIsCompleted = null;
            return;
        }
        try {
            searchByIsCompleted = Boolean.parseBoolean(newValue);
        } catch (Exception e) {
            searchByIsCompleted = null;
        }
    }
}
