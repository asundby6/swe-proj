package Project;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    @FXML private Button add_orderButton;
    @FXML private Button manage_ordersButton;
    @FXML private Button manage_customersButton;
    @FXML private Button routingButton;
    @FXML private Button sign_outButton;
    @FXML private javafx.scene.control.TextField titleField;


    public void sceneChange_addOrder(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("addOrder.fxml"));
            Scene viewScene = new Scene(viewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void sceneChange_manageOrders(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("manageOrders.fxml"));
            Scene viewScene = new Scene(viewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void sceneChange_manageCustomers(ActionEvent event) {
        try {
            Parent viewParent = FXMLLoader.load(getClass().getResource("manageCustomers.fxml"));
            Scene viewScene = new Scene(viewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize title from UIState and persist edits

    }

}
