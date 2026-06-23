package Project;

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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataService {
    private static final String ORDERS_FILE_PATH = "xmlFiles/orders.xml";
    private static final String CUSTOMERS_FILE_PATH = "xmlFiles/customers.xml";
    private static final String PRODUCTS_FILE_PATH = "/Project/xmlFiles/excel_dataParsed.xml";

    public static void saveOrder(Order order) {
        try {
            List<Order> orders = loadAllOrders();
            orders.add(order);
            saveAllOrders(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Order> loadAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            File file = new File(ORDERS_FILE_PATH);
            if (!file.exists()) {
                createEmptyOrdersFile();
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList orderNodes = document.getElementsByTagName("order");

            for (int i = 0; i < orderNodes.getLength(); i++) {
                Element orderElement = (Element) orderNodes.item(i);
                Order order = new Order(
                        getTextContent(orderElement, "productStatus"),
                        getTextContent(orderElement, "productID"),
                        getTextContent(orderElement, "productSubName"),
                        getTextContent(orderElement, "customerName"),
                        getTextContent(orderElement, "deliveryHours"),
                        getTextContent(orderElement, "cityField"),
                        getTextContent(orderElement, "stateField"),
                        getTextContent(orderElement, "zipField"),
                        getTextContent(orderElement, "streetAddress"),
                        getTextContent(orderElement, "quantityText"),
                        Boolean.parseBoolean(getTextContent(orderElement, "loadingYes"))
                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void deleteOrder(Order order) {
        try {
            List<Order> orders = loadAllOrders();
            orders.remove(order);
            saveAllOrders(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveAllOrders(List<Order> orders) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("orders");
            document.appendChild(rootElement);

            for (Order order : orders) {
                Element orderElement = document.createElement("order");
                rootElement.appendChild(orderElement);

                addElement(document, orderElement, "productStatus", order.getProductStatus());
                addElement(document, orderElement, "productID", order.getProductID());
                addElement(document, orderElement, "productSubName", order.getProductSubName());
                addElement(document, orderElement, "customerName", order.getCustomerName());
                addElement(document, orderElement, "deliveryHours", order.getDeliveryHours());
                addElement(document, orderElement, "cityField", order.getCityField());
                addElement(document, orderElement, "stateField", order.getStateField());
                addElement(document, orderElement, "zipField", order.getZipField());
                addElement(document, orderElement, "streetAddress", order.getStreetAddress());
                addElement(document, orderElement, "quantityText", order.getQuantityText());
                addElement(document, orderElement, "loadingYes", String.valueOf(order.isLoadingYes()));
            }

            writeToFile(document, ORDERS_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveCustomer(Customer customer) {
        try {
            List<Customer> customers = loadAllCustomers();
            customers.add(customer);
            saveAllCustomers(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> loadAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            File file = new File(CUSTOMERS_FILE_PATH);
            if (!file.exists()) {
                createEmptyCustomersFile();
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList customerNodes = document.getElementsByTagName("customer");

            for (int i = 0; i < customerNodes.getLength(); i++) {
                Element customerElement = (Element) customerNodes.item(i);
                Customer customer = new Customer(
                        getTextContent(customerElement, "customerName"),
                        getTextContent(customerElement, "emailAddress"),
                        getTextContent(customerElement, "phoneNumber")
                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void deleteCustomer(Customer customer) {
        try {
            List<Customer> customers = loadAllCustomers();
            customers.remove(customer);
            saveAllCustomers(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LinkedHashMap<String, String> loadAllProductSubNames() {
        LinkedHashMap<String, String> productSubNames = new LinkedHashMap<>();
        try {
            InputStream inputStream = DataService.class.getResourceAsStream(PRODUCTS_FILE_PATH);
            if (inputStream == null) {
                inputStream = DataService.class.getResourceAsStream("xmlFiles/excel_dataParsed.xml");
            }
            if (inputStream == null) {
                return productSubNames;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            NodeList productNodes = document.getElementsByTagName("row");

            for (int i = 0; i < productNodes.getLength(); i++) {
                Element productElement = (Element) productNodes.item(i);
                String productID = getTextContent(productElement, "productID");
                String subName = getTextContent(productElement, "subName");

                if (!productID.isEmpty()) {
                    productSubNames.put(productID, subName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSubNames;
    }

    private static void saveAllCustomers(List<Customer> customers) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("customers");
            document.appendChild(rootElement);

            for (Customer customer : customers) {
                Element customerElement = document.createElement("customer");
                rootElement.appendChild(customerElement);

                addElement(document, customerElement, "customerName", customer.getCustomerName());
                addElement(document, customerElement, "emailAddress", customer.getEmailAddress());
                addElement(document, customerElement, "phoneNumber", customer.getPhoneNumber());
            }

            writeToFile(document, CUSTOMERS_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            String content = nodeList.item(0).getTextContent();
            return (content != null && !content.isEmpty()) ? content : "";
        }
        return "";
    }

    private static void addElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value != null ? value : "");
        parent.appendChild(element);
    }

    private static void writeToFile(Document document, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEmptyOrdersFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.appendChild(document.createElement("orders"));
            writeToFile(document, ORDERS_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEmptyCustomersFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.appendChild(document.createElement("customers"));
            writeToFile(document, CUSTOMERS_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
