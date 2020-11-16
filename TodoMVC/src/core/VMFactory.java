package core;

import models.Todo;
import services.TodoService;
import views.add.AddVM;
import views.display.DisplayVM;
import views.update.UpdateVM;

public class VMFactory {

    private TodoService todoService;

    private AddVM addVM;
    private DisplayVM displayVM;
    private UpdateVM updateVM;

    public VMFactory(TodoService todoService) {
        this.todoService = todoService;
    }

    public AddVM getAddVM() {
        if(addVM == null) addVM = new AddVM(todoService);
        return addVM;
    }

    public DisplayVM getDisplayVM() {
        if(displayVM == null) displayVM = new DisplayVM(todoService);
        return displayVM;
    }

    public UpdateVM getUpdateVM() {
        if(updateVM == null) updateVM = new UpdateVM(todoService);
        return updateVM;
    }

    public void updateTodo(Todo todo) {

    }
}
