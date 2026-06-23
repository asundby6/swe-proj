package Project;

import java.util.stream.Collectors;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class add_orderController implements Initializable {

    private String productID;
    private String product_subName;
    private String customerName;
    private String deliveryHours;
    private String cityField;
    private String stateField;
    private String zipField;
    private String streetAddress;
    private String quantityText;
    private boolean loadingYes;

    @FXML
    AnchorPane add_orderScene;
    @FXML
    TextField customer_nameField;
    @FXML
    TextField delivery_hoursField;
    @FXML
    TextField city_field;
    @FXML
    TextField state_field;
    @FXML
    TextField zip_field;
    @FXML
    TextField street_addressField;
    @FXML
    TextField quantity_textField;
    @FXML
    TextField autofill_subName;
    @FXML
    javafx.scene.control.ComboBox<String> autofill_productID;
    @FXML
    CheckBox loading_yesCapability;
    @FXML
    TextField titleField;
    @FXML
    Button main_menuButton;
    @FXML
    Button submit_listButton;
    @FXML
    Button adding_listButton;
    @FXML
    Button show_listButton;

    public add_orderController() throws IOException {

    }


    public boolean compatibilityChecker(ActionEvent event) throws IOException {
        return loading_yesCapability.isSelected();
    }

    public void sceneChange_submit(ActionEvent event) throws IOException {

        Parent viewParent = FXMLLoader.load(getClass().getResource("addOrder.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    public void sceneChange_list(ActionEvent event) throws IOException {
        // Show the Manage Orders view instead of the empty listView placeholder
        Parent viewParent = FXMLLoader.load(getClass().getResource("manageOrders.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    public void sceneChange_mainMenu(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    public TextField getAutofill_subName() {
        return autofill_subName;
    }

    public void setAdding_listButton(ActionEvent event) throws IOException {
        try {
            String customerName = customer_nameField.getText();
            String productID = "";
            if (autofill_productID != null) {
                // prefer editor text (typed), fall back to selected value
                String editorText = autofill_productID.getEditor() != null ? autofill_productID.getEditor().getText() : null;
                String selected = autofill_productID.getValue();
                if (editorText != null && !editorText.trim().isEmpty()) productID = editorText.trim();
                else if (selected != null) productID = selected;
            }
            String productSubName = autofill_subName.getText();
            String quantity = quantity_textField.getText();
            String streetAddress = street_addressField.getText();
            String zip = zip_field.getText();
            String state = state_field.getText();
            String city = city_field.getText();
            String hours = delivery_hoursField.getText();
            boolean hasLoading = loading_yesCapability.isSelected();

            if (!customerName.isEmpty() && !productID.isEmpty() && !quantity.isEmpty()) {
                Order newOrder = new Order(
                        "pending",
                        productID,
                        productSubName,
                        customerName,
                        hours,
                        city,
                        state,
                        zip,
                        streetAddress,
                        quantity,
                        hasLoading
                );
                DataService.saveOrder(newOrder);

                customer_nameField.clear();
                if (autofill_productID != null) {
                    if (autofill_productID.getEditor() != null) autofill_productID.getEditor().setText("");
                    autofill_productID.setValue(null);
                }
                if (autofill_subName != null) autofill_subName.setText("");
                quantity_textField.clear();
                street_addressField.clear();
                zip_field.clear();
                state_field.clear();
                city_field.clear();
                delivery_hoursField.clear();
                loading_yesCapability.setSelected(false);

                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Order Saved");
                success.setHeaderText(null);
                success.setContentText("Order saved successfully.");
                success.showAndWait();
            } else {
                Alert warn = new Alert(Alert.AlertType.WARNING);
                warn.setTitle("Missing Information");
                warn.setHeaderText(null);
                warn.setContentText("Please provide Customer Name, Product ID and Quantity.");
                warn.showAndWait();
            }
        } catch (Exception e) {
            // Print stack trace to console so developer can see any runtime errors
            e.printStackTrace();
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error Saving Order");
            err.setHeaderText(null);
            err.setContentText(e.getMessage());
            err.showAndWait();
        }
    }

    private String getTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            return null;
        }
    }

    // Store full product list for autocomplete filtering
    private javafx.collections.ObservableList<String> allProductIds;
    private LinkedHashMap<String, String> productSubNames = new LinkedHashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // bind title field to shared UI state so title edits persist during runtime
        try {
            if (titleField != null) {
                titleField.setText(UIState.getTitle("addOrder"));
                titleField.textProperty().addListener((obs, oldV, newV) -> UIState.setTitle("addOrder", newV));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // populate product id suggestions: load all from excel_dataParsed.xml with autocomplete filtering.
        try {
            if (autofill_productID != null) {
                productSubNames = DataService.loadAllProductSubNames();
                List<String> productIds = new ArrayList<>(productSubNames.keySet());
                System.out.println("add_orderController: loaded product IDs=" + productIds.size());

                // Store full list for filtering
                allProductIds = javafx.collections.FXCollections.observableArrayList(productIds);
                autofill_productID.setItems(allProductIds);
                autofill_productID.setEditable(true);

                // Attach autocomplete filter listener to the editor text property
                if (autofill_productID.getEditor() != null) {
                    autofill_productID.getEditor().textProperty().addListener((obs, oldV, newV) -> {
                        filterProductList(newV);
                        setProductSubName(newV);
                    });
                }
                autofill_productID.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
                    setProductSubName(newV);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProductSubName(String productID) {
        try {
            if (autofill_subName != null && productID != null) {
                String subName = productSubNames.get(productID.trim());
                if (subName != null) {
                    autofill_subName.setText(subName);
                }
                else if (productID.trim().isEmpty()) {
                    autofill_subName.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Filter the product list based on the user's typed query (case-insensitive substring match).
     * Updates the ComboBox items in real-time.
     */
    private void filterProductList(String query) {
        if (allProductIds == null) return;

        if (query == null || query.trim().isEmpty()) {
            // Empty query: show all products
            autofill_productID.setItems(allProductIds);
        } else {
            // Filter: show products containing the query (case-insensitive)
            String lowerQuery = query.toLowerCase();
            List<String> filtered = allProductIds.stream()
                    .filter(id -> id.toLowerCase().contains(lowerQuery))
                    .collect(java.util.stream.Collectors.toList());
            javafx.collections.ObservableList<String> filteredItems = javafx.collections.FXCollections.observableArrayList(filtered);
            autofill_productID.setItems(filteredItems);
            // Show dropdown to display filtered results
            autofill_productID.show();
        }
    }

    /**
     * Stream-scan the project's excel_dataParsed.xml for <productID> values.
     * Returns an ordered, deduplicated list of ALL product IDs found (no cap).
     */
    private List<String> loadProductIDs() {
        List<String> ids = new ArrayList<>();
        InputStream is = null;
        try {
            // resource path inside JAR/classpath
            is = getClass().getResourceAsStream("/Project/xmlFiles/excel_dataParsed.xml");
            if (is == null) {
                // try relative path as fallback
                is = getClass().getResourceAsStream("xmlFiles/excel_dataParsed.xml");
            }
            if (is == null) return ids;

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            Pattern p = Pattern.compile("<productID\\s*>\\s*(.*?)\\s*</productID>", Pattern.CASE_INSENSITIVE);
            while ((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    String val = m.group(1);
                    if (val != null && !val.trim().isEmpty()) ids.add(val.trim());
                }
            }
            // deduplicate while preserving insertion order
            LinkedHashSet<String> dedup = new LinkedHashSet<>(ids);
            ids.clear();
            ids.addAll(dedup);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (is != null) is.close(); } catch (Exception ignored) {}
        }
        return ids;
    }

}
