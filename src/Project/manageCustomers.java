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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manageCustomers implements Initializable {

    @FXML private Button main_menuButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private ListView<String> customersListView;
    @FXML private javafx.scene.control.TextField newNameField;
    @FXML private javafx.scene.control.TextField newEmailField;
    @FXML private javafx.scene.control.TextField newPhoneField;
    @FXML private javafx.scene.control.Button addButton;

    private ObservableList<String> customersList;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("manageCustomers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void save_buttonClicked(ActionEvent event) throws IOException {

    }

    public void add_buttonClicked(ActionEvent event) throws IOException {
        try {
            String name  = (newNameField  != null) ? newNameField.getText().trim()  : "";
            String email = (newEmailField != null) ? newEmailField.getText().trim() : "";
            String phone = (newPhoneField != null) ? newPhoneField.getText().trim() : "";
            if (!name.isEmpty()) {
                File file = new File("xmlFiles/customers.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document;
                Element rootElement;

                if (file.exists()) {
                    document = builder.parse(file);
                    rootElement = document.getDocumentElement();
                } else {
                    document = builder.newDocument();
                    rootElement = document.createElement("customers");
                    document.appendChild(rootElement);
                }

                Element customerElement = document.createElement("customer");
                rootElement.appendChild(customerElement);
                addElement(document, customerElement, "customerName", name);
                addElement(document, customerElement, "emailAddress", email);
                addElement(document, customerElement, "phoneNumber", phone);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                file.getParentFile().mkdirs();
                transformer.transform(new DOMSource(document), new StreamResult(file));

                newNameField.clear();
                newEmailField.clear();
                newPhoneField.clear();
                refreshCustomersList();
            } else {
                // no name provided; ignore or show a warning in future
            }
        } catch (Exception e) {
            // Log error to console to help diagnose runtime issues when switching to this scene
            e.printStackTrace();
        }
    }

    public void delete_buttonClicked(ActionEvent event) throws IOException{
        String selectedCustomer = customersListView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customersList.remove(selectedCustomer);
        }
    }

    public void main_menuClicked(ActionEvent event) throws IOException{
        Parent viewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    private void addElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value != null ? value : "");
        parent.appendChild(element);
    }

    private void refreshCustomersList() {
        try {
            if (customersList == null) {
                customersList = FXCollections.observableArrayList();
            }
            customersList.clear();
            File file = new File("xmlFiles/customers.xml");
            if (!file.exists()) return;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList customerNodes = document.getElementsByTagName("customer");

            for (int i = 0; i < customerNodes.getLength(); i++) {
                Element customerElement = (Element) customerNodes.item(i);
                String name  = getTextContent(customerElement, "customerName");
                String email = getTextContent(customerElement, "emailAddress");
                String phone = getTextContent(customerElement, "phoneNumber");
                customersList.add(name + " | " + email + " | " + phone);
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
        customersList = FXCollections.observableArrayList();
        if (customersListView != null) {
            customersListView.setItems(customersList);
            refreshCustomersList();
        }
    }

}
