package models;

public class Todo {
    private int userId;
    private int todoId;
    private boolean isCompleted;
    private String body;

    public Todo(int userId, String body) {
        this.userId = userId;
        this.body = body;
    }

    public Todo(int userId, String body, boolean isCompleted) {
        this(userId, body);
        this.isCompleted = isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getTodoId() {
        return todoId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getBody() {
        return body;
    }

    public void setTodoId(int id) {
        todoId = id;
    }

    public void setUserId(int userId) {
        this.userId =userId;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", todoId=" + todoId +
                ", isCompleted=" + isCompleted +
                ", body='" + body + '\'' +
                '}';
    }
}
