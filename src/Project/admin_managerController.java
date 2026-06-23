package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class admin_managerController implements Initializable {
    @FXML private Button sign_outButton;
    @FXML private Button admin_toolsButton;

    public void handleSignOutButtonClick(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene viewScene = new Scene(viewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void handleAdminToolsButtonClick(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("admin_manageReps.fxml"));
            Scene viewScene = new Scene(viewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Admin menu initialization
    }

}
