package views.display;

import core.VMFactory;
import core.ViewHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Todo;
import views.ViewController;

public class DisplayController implements ViewController {
    public TableView<Todo> table;
    public TableColumn<Todo, Integer> todoIdColumn;
    public TableColumn<Todo, Integer> userIdColumn;
    public TableColumn<Todo, String> bodyColumn;
    public TableColumn<Todo, Boolean> doneColumn;
    public TextField userIdSearchField;
    public ComboBox<String> isCompletedDropDown;

    private DisplayVM vm;
    private ViewHandler vh;
    @Override
    public void init(ViewHandler viewHandler, VMFactory vmFactory, Object argForView) {
        vm = vmFactory.getDisplayVM();
        vh = viewHandler;
        addComboBoxOptions();
        setupTable();
        userIdSearchField.textProperty().bindBidirectional(vm.userIdSearchProperty());
        isCompletedDropDown.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldValue, newValue) ->
                        vm.setSearchByCompleted(newValue));
    }

    private void setupTable() {
        table.setItems(vm.getTodos());
        table.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldValue, newValue) ->
                        vm.setSelected(newValue));

        todoIdColumn.setCellValueFactory(new PropertyValueFactory<>("todoId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));

        doneColumn.setCellValueFactory(c -> {
            Todo todo = c.getValue();
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().setValue(todo.isCompleted());
            checkBox.selectedProperty().
                    addListener((ov, old_val, new_val) -> {
                        todo.setCompleted(new_val);
                        vm.updateTodo(todo);
                    });
            return new SimpleObjectProperty(checkBox);
        });
    }

    private void addComboBoxOptions() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("true");
        items.add("false");
        items.add("both");
        isCompletedDropDown.setItems(items);
    }

    public void onAdd() {
        vh.openView("add");
    }

    public void onDelete() {
        vm.delete();
    }

    public void onUpdate() {
        Todo selectedTodo = vm.getSelectedTodo();
        if(selectedTodo != null)
            vh.openView("update", selectedTodo);
    }

    public void onSearch() {
        vm.search();
    }
}
