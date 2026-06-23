package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static void main (String[]args){
        launch(args);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage primaryStage;

    public Stage getStage() {
        return primaryStage;
    }

    public void changeScene(String fxml) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.getScene().setRoot(viewParent);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setTitle("Login");
//            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
//            primaryStage.setTitle("mainMenu");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
