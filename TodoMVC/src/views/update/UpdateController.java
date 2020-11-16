package views.update;

import core.VMFactory;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Todo;
import views.ViewController;

public class UpdateController implements ViewController {


    public TextField userIdField;
    public TextField bodyField;
    public Label validationLabel;
    private ViewHandler vh;
    private UpdateVM updateVM;

    @Override
    public void init(ViewHandler viewHandler, VMFactory vmFactory, Object argForView) {
        this.vh = viewHandler;
        this.updateVM = vmFactory.getUpdateVM();
        updateVM.setTodo((Todo)argForView);
        userIdField.textProperty().bindBidirectional(updateVM.userIdProperty());
        bodyField.textProperty().bindBidirectional(updateVM.bodyProperty());
        validationLabel.textProperty().bindBidirectional(updateVM.validationProperty());
    }

    public void onUpdate() {
        updateVM.update();
    }

    public void onBack() {
        updateVM.clear();
        vh.openView("display");
    }
}
