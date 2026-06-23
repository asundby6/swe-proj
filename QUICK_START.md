# Quick Start Guide - Acme Distributing Order Management System

## Running the Application

### Option 1: Double-Click RUN.bat (Easiest)
Simply double-click `RUN.bat` in the project folder. The application will launch automatically.

### Option 2: Use Maven
```bash
cd "C:\Users\fishe\Desktop\swe_project\swe_project"
mvn javafx:run
```

### Option 3: IntelliJ IDE
- Open the project in IntelliJ
- Navigate to `src/Project/Main.java`
- Click the Green Play button or press Shift+F10

---

## First Time Using the Application

### Step 1: Login
- **Username**: `user`
- **Password**: `test`
- Click "Login"

### Step 2: Main Menu
You'll see options for:
- **Add Order** - Create new orders
- **Manage Orders** - View and manage existing orders
- **Manage Customers** - View and manage customer records
- **Sign Out** - Return to login

---

## How to Add an Order

1. Click **"Add Order"** from Main Menu
2. Fill in the form:
   - **Customer Name**: Enter customer name
   - **Product Sub Name**: Enter product name
   - **Product ID** (auto-complete available)
   - **Quantity**: Enter quantity
   - **Street Address**, **City**, **State**, **Zip**: Delivery address
   - **Delivery Hours**: Expected delivery time
   - **Loading Dock Capability**: Check if yes
3. Click **"Add"** to save the order
4. Form clears automatically after successful submission
5. Click **"Show List"** to view all submitted orders
6. Click **"Main Menu"** to return

---

## How to Manage Orders

1. Click **"Manage Orders"** from Main Menu
2. You'll see a list of all orders
3. **Select an order** from the list
4. **Save** - Click to persist any changes
5. **Delete** - Click to remove the order
6. **Main Menu** - Return to main menu

---

## How to Manage Customers

1. Click **"Manage Customers"** from Main Menu
2. You'll see a list of all customers
3. **Select a customer** from the list
4. **Save** - Click to persist any changes
5. **Delete** - Click to remove the customer
6. **Main Menu** - Return to main menu

---

## Data Storage

All orders and customers are automatically saved in:
- **Orders**: `xmlFiles/orders.xml`
- **Customers**: `xmlFiles/customers.xml`

These files are created automatically when you first save data.

---

## Troubleshooting

### Issue: Application won't start
**Solution**: Make sure Java 17 or higher is installed
```bash
java -version
```

### Issue: "FXML file not found" error
**Solution**: This is already fixed. The project has been properly configured.

### Issue: Data not saving
**Solution**: Make sure the `xmlFiles` folder exists in the project root. It's created automatically.

### Issue: Can't login
**Solution**: Use the exact credentials:
- Username: `user` (lowercase)
- Password: `test` (lowercase)

---

## Project Structure

```
swe_project/
├── src/Project/                    # Source code
│   ├── *.java                      # Controllers and models
│   ├── *.fxml                      # UI layouts
│   └── xmlFiles/                   # Data storage
├── target/                         # Compiled classes
├── pom.xml                         # Maven configuration
├── RUN.bat                         # Quick start script
├── PROJECT_COMPLETION_REPORT.md    # Detailed report
└── IMPLEMENTATION_DETAILS.md       # Code details
```

---

## Key Features

**Order Management**
- Create, view, and manage orders
- Auto-complete for product information
- Full delivery details tracking

**Customer Management**
- Store customer information
- Track email and phone
- Easy add/remove functionality

**Data Persistence**
- All data saved in XML format
- Data survives application restart
- Easy to backup and recover

**User Authentication**
- Login system prevents unauthorized access
- Session management

**Admin Dashboard**
- Administrative menu
- Framework for managing sales representatives

---

## What's New (Completed)

1. **Order Model** - Data class for orders
2. **Customer Model** - Data class for customers
3. **Data Service** - Complete save/load functionality
4. **Order Management Screen** - Full CRUD operations
5. **Customer Management Screen** - Full CRUD operations
6. **Order Submission** - Creates and saves orders
7. **Admin Dashboard** - Navigation and logout
8. **All Button Handlers** - Scene navigation working

---

## Development Notes

- **Language**: Java 17+
- **UI Framework**: JavaFX 20.0.1
- **Build Tool**: Apache Maven 3.9.4
- **Data Format**: XML
- **Database**: File-based (XML files)

---

## Next Steps

To extend the application:

1. **Add Database Support** - Replace XML with MySQL/PostgreSQL
2. **Enhance Authentication** - Add user management system
3. **Add Reporting** - Generate PDF/Excel reports
4. **Complete Admin Tools** - Full sales rep management
5. **Add Search/Filter** - Find orders by customer, date, etc.
6. **Multi-user Support** - Network-based data sync

---

## Support

For issues or questions:
1. Check the `PROJECT_COMPLETION_REPORT.md` for detailed architecture
2. Review `IMPLEMENTATION_DETAILS.md` for code structure
3. Examine the `.java` files for implementation details

---


