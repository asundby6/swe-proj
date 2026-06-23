package Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manageOrders implements Initializable {

    @FXML private Button main_menuButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private ListView<String> ordersListView;
    @FXML private javafx.scene.control.TextField titleField;

    private ObservableList<String> ordersList;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("manageOrders.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void save_buttonClicked(ActionEvent event) throws IOException{

    }

    public void delete_buttonClicked(ActionEvent event) throws IOException{
        String selectedOrder = ordersListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            ordersList.remove(selectedOrder);
        }
    }

    public void main_menuClicked(ActionEvent event) throws IOException{
        Parent viewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    private void refreshOrdersList() {
        try {
            if (ordersList == null) {
                ordersList = FXCollections.observableArrayList();
            }
            ordersList.clear();
            File file = new File("xmlFiles/orders.xml");
            if (!file.exists()) return;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList orderNodes = document.getElementsByTagName("order");

            for (int i = 0; i < orderNodes.getLength(); i++) {
                Element orderElement = (Element) orderNodes.item(i);
                String customerName = getTextContent(orderElement, "customerName");
                String productID    = getTextContent(orderElement, "productID");
                String quantity     = getTextContent(orderElement, "quantityText");
                ordersList.add(customerName + " | " + productID + " x" + quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordersList = FXCollections.observableArrayList();
        if (ordersListView != null) {
            ordersListView.setItems(ordersList);
            refreshOrdersList();
        }
        // wire titleField to shared UI state so title edits persist during runtime

    }

}
