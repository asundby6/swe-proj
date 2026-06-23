package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    //    public AnchorPane loginScene;
    @FXML
    private TextField username_textField;
    @FXML
    private PasswordField password_textField;
    @FXML
    private Button loginButton;
    @FXML
    private menuController MenuController;
    @FXML
    private AnchorPane loginScene;
    @FXML Label printResult;

    private String password1;
    private String username1;

    @FXML
    private void login(ActionEvent event) throws IOException {
        Main ms = new Main();
        String password1 = "test";
        String username1 = "user";
        username_textField.getText();
        password_textField.getText();
        System.out.println(username_textField.getText() + password_textField.getText());

        if (username_textField.getText().equals(username1) && password_textField.getText().equals(password1)) {
//            ms.changeScene("mainMenu.fxml");
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (username_textField.getText().isEmpty() || password_textField.getText().isEmpty()) {
            printResult.setText("Empty submission detected");
        } else {
            printResult.setText("Invalid submission");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
