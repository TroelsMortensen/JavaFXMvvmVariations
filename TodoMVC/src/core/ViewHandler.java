package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.ViewController;

import java.io.IOException;

public class ViewHandler {

    private VMFactory vmFactory;
    private Stage stage;
    public ViewHandler(VMFactory vmFactory) {
        this.vmFactory = vmFactory;
        stage = new Stage();
    }

    public void start() {
        openView("display");
        stage.show();
    }

    public void openView(String viewName){
        openView(viewName, null);
    }

    public void openView(String viewName, Object argForView){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/" + viewName + "/" + viewName + ".fxml"));
            Parent root = loader.load();
            ViewController c = loader.getController();
            c.init(this, vmFactory, argForView);
            Scene s = new Scene(root);
            stage.setScene(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
