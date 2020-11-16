package views.update;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Todo;
import services.TodoService;

public class UpdateVM {
    private final StringProperty userIdProperty = new SimpleStringProperty();
    private final StringProperty bodyProperty = new SimpleStringProperty();
    private final StringProperty validationProperty = new SimpleStringProperty();
    private final TodoService todoService;
    private Todo todoToUpdate;

    public UpdateVM(TodoService todoService) {
        this.todoService = todoService;
    }

    public void update() {
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
        todoToUpdate.setBody(body);
        todoToUpdate.setUserId(userId);
        todoService.update(todoToUpdate);
        clear();
        validationProperty.set("Updated");
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

    public void setTodo(Todo todoToUpdate) {
        this.todoToUpdate = todoToUpdate;
        userIdProperty.set(""+todoToUpdate.getUserId());
        bodyProperty.set(todoToUpdate.getBody());
    }
}
