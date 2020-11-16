package views.add;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Todo;
import services.TodoService;

public class AddVM {
    private final TodoService todoService;
    private final StringProperty userIdProperty = new SimpleStringProperty();
    private final StringProperty bodyProperty = new SimpleStringProperty();
    private final StringProperty validationProperty = new SimpleStringProperty();

    public AddVM(TodoService todoService) {
        this.todoService = todoService;
    }

    public void create() {
        Integer userId = null;
        try {
            userId = Integer.parseInt(userIdProperty.get());
        } catch (NumberFormatException e) {
            validationProperty.set("User id must be number, larger than 0");
            return;
        }
        String body = bodyProperty.get();
        if (body == null || "".equals(body)) {
            validationProperty.set("Body cannot be empty");
            return;
        }
        Todo todo = new Todo(userId, body);
        todoService.create(todo);
        clear();
        validationProperty.set("Created");
    }

    public void clear() {
        userIdProperty.set("");
        bodyProperty.set("");
        validationProperty.set("");
    }

    public StringProperty userIdProperty() {
        return userIdProperty;
    }

    public StringProperty bodyProperty() {
        return bodyProperty;
    }

    public StringProperty validationProperty() {
        return validationProperty;
    }
}
