package core;

import javafx.application.Application;
import javafx.stage.Stage;
import services.InMemoryTodoService;
import services.TodoService;

public class TodoMVCApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TodoService todoService = new InMemoryTodoService();
        VMFactory vmFactory = new VMFactory(todoService);
        ViewHandler viewHandler = new ViewHandler(vmFactory);
        viewHandler.start();
    }
}
