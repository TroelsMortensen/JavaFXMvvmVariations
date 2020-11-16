package views.add;

import core.VMFactory;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import views.ViewController;

public class AddController implements ViewController {

    public TextField userIdField;
    public TextField bodyField;
    public Label validationLabel;

    private ViewHandler vh;
    private AddVM addVM;

    @Override
    public void init(ViewHandler viewHandler, VMFactory vmFactory, Object argForView) {
        vh = viewHandler;
        addVM = vmFactory.getAddVM();
        userIdField.textProperty().bindBidirectional(addVM.userIdProperty());
        bodyField.textProperty().bindBidirectional(addVM.bodyProperty());
        validationLabel.textProperty().bind(addVM.validationProperty());
    }

    public void onCreate() {
        addVM.create();
    }

    public void onBack() {
        addVM.clear();
        vh.openView("display");
    }
}
