# Acme Distributing — Order Management System

A JavaFX desktop application built for distribution company sales representatives to manage delivery orders, customers, and product catalog data. Developed as a semester-long Software Engineering team project.

---

## Features

- **Role-based login** — separate workflows for sales reps and admin users
- **Order submission** — submit delivery orders with full address, product, quantity, delivery hours, and loading dock capability
- **Product autocomplete** — ComboBox dynamically filters a 2,400+ SKU XML product catalog as the rep types, and auto-populates the product sub-name on selection
- **Order management** — view and delete submitted orders from a ListView
- **Customer management** — add and remove customers with contact details
- **Admin panel** — admin account manages the sales rep roster
- **XML persistence** — all orders and customers are saved and loaded from XML files on disk

---

## Technologies

- Java 17
- JavaFX (UI framework)
- XML / DOM parsing (`javax.xml`)
- IntelliJ IDEA

---

## Project Structure

```
src/
└── Project/
    ├── Main.java                    # Application entry point
    ├── loginController.java         # Login screen logic
    ├── menuController.java          # Main menu navigation
    ├── add_orderController.java     # Order submission form with product autocomplete
    ├── manageOrders.java            # Order list view and delete
    ├── manageCustomers.java         # Customer management with add/delete
    ├── admin_managerController.java # Admin menu and rep management
    ├── DataService.java             # Centralized XML read/write service
    ├── Order.java                   # Order data model
    ├── Customer.java                # Customer data model
    ├── UIState.java                 # Shared UI state utility
    ├── xmlFiles/
    │   ├── excel_dataParsed.xml     # 2,400+ SKU product catalog
    │   ├── orders.xml               # Persisted order data (generated at runtime)
    │   └── customers.xml            # Persisted customer data (generated at runtime)
    ├── addOrder.fxml
    ├── login.fxml
    ├── mainMenu.fxml
    ├── manageOrders.fxml
    ├── manageCustomers.fxml
    ├── adminMenu.fxml
    └── admin_manageReps.fxml
```

---

## Setup & Running

### Prerequisites

- JDK 17 or higher — [Download Temurin JDK 17](https://adoptium.net)
- JavaFX SDK 17 or higher — [Download JavaFX](https://gluonhq.com/products/javafx/)
- IntelliJ IDEA (Community or Ultimate)

### Steps

1. Clone the repository:
   ```
   git clone https://github.com/asundby6/t8-swe-proj.git
   ```

2. Open the project in IntelliJ IDEA

3. Add the JavaFX SDK as a library:
   - Go to `File > Project Structure > Libraries`
   - Click `+` and point to the `lib/` folder inside your JavaFX SDK

4. Set the VM options for the run configuration:
   ```
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
   ```

5. Run `Main.java`

### Default Login

| Role | Username | Password |
|------|----------|----------|
| Sales Rep | user | test |
| Admin | admin | admin |

---

## Key Implementation Details

### XML Product Catalog Parsing

The application loads a 2,400+ SKU product catalog from `excel_dataParsed.xml` at startup using DOM parsing. Product IDs are indexed into a `LinkedHashMap` that maps each `productID` to its `subName`, enabling both real-time autocomplete filtering and automatic sub-name population when a product is selected.

### Data Persistence

Orders and customers are persisted to `xmlFiles/orders.xml` and `xmlFiles/customers.xml` respectively using `javax.xml.transform`. The `DataService` class centralizes all read/write operations, keeping XML logic out of the controllers.

### Design Patterns

- **Singleton-style shared state** via `UIState` for persistent UI values across scene changes
- **Service layer separation** — `DataService` handles all data operations independently of controller logic
- **MVC structure** — FXML files define the view, controller classes handle logic, model classes (`Order`, `Customer`) hold data

---

## Author

Alec Sundby — B.S. Computer Science, Kennesaw State University (May 2026)

[LinkedIn](https://linkedin.com/in/alec-sundby-23a640260) | [GitHub](https://github.com/asundby6)
