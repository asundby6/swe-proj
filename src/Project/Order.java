package Project;

public class Order {
    private String productStatus;
    private String productID;
    private String productSubName;
    private String customerName;
    private String deliveryHours;
    private String cityField;
    private String stateField;
    private String zipField;
    private String streetAddress;
    private String quantityText;
    private boolean loadingYes;

    public Order() {}

    public Order(String productStatus, String productID, String productSubName, String customerName,
                 String deliveryHours, String cityField, String stateField, String zipField,
                 String streetAddress, String quantityText, boolean loadingYes) {
        this.productStatus = productStatus;
        this.productID = productID;
        this.productSubName = productSubName;
        this.customerName = customerName;
        this.deliveryHours = deliveryHours;
        this.cityField = cityField;
        this.stateField = stateField;
        this.zipField = zipField;
        this.streetAddress = streetAddress;
        this.quantityText = quantityText;
        this.loadingYes = loadingYes;
    }

    // Getters and Setters
    public String getProductStatus() { return productStatus; }
    public void setProductStatus(String productStatus) { this.productStatus = productStatus; }

    public String getProductID() { return productID; }
    public void setProductID(String productID) { this.productID = productID; }

    public String getProductSubName() { return productSubName; }
    public void setProductSubName(String productSubName) { this.productSubName = productSubName; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDeliveryHours() { return deliveryHours; }
    public void setDeliveryHours(String deliveryHours) { this.deliveryHours = deliveryHours; }

    public String getCityField() { return cityField; }
    public void setCityField(String cityField) { this.cityField = cityField; }

    public String getStateField() { return stateField; }
    public void setStateField(String stateField) { this.stateField = stateField; }

    public String getZipField() { return zipField; }
    public void setZipField(String zipField) { this.zipField = zipField; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getQuantityText() { return quantityText; }
    public void setQuantityText(String quantityText) { this.quantityText = quantityText; }

    public boolean isLoadingYes() { return loadingYes; }
    public void setLoadingYes(boolean loadingYes) { this.loadingYes = loadingYes; }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", productID='" + productID + '\'' +
                ", productSubName='" + productSubName + '\'' +
                ", quantity='" + quantityText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (loadingYes != order.loadingYes) return false;
        if (productStatus != null ? !productStatus.equals(order.productStatus) : order.productStatus != null)
            return false;
        if (productID != null ? !productID.equals(order.productID) : order.productID != null) return false;
        if (productSubName != null ? !productSubName.equals(order.productSubName) : order.productSubName != null)
            return false;
        if (customerName != null ? !customerName.equals(order.customerName) : order.customerName != null)
            return false;
        if (deliveryHours != null ? !deliveryHours.equals(order.deliveryHours) : order.deliveryHours != null)
            return false;
        if (cityField != null ? !cityField.equals(order.cityField) : order.cityField != null) return false;
        if (stateField != null ? !stateField.equals(order.stateField) : order.stateField != null) return false;
        if (zipField != null ? !zipField.equals(order.zipField) : order.zipField != null) return false;
        if (streetAddress != null ? !streetAddress.equals(order.streetAddress) : order.streetAddress != null)
            return false;
        return quantityText != null ? quantityText.equals(order.quantityText) : order.quantityText == null;
    }

    @Override
    public int hashCode() {
        int result = productStatus != null ? productStatus.hashCode() : 0;
        result = 31 * result + (productID != null ? productID.hashCode() : 0);
        result = 31 * result + (productSubName != null ? productSubName.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (deliveryHours != null ? deliveryHours.hashCode() : 0);
        result = 31 * result + (cityField != null ? cityField.hashCode() : 0);
        result = 31 * result + (stateField != null ? stateField.hashCode() : 0);
        result = 31 * result + (zipField != null ? zipField.hashCode() : 0);
        result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
        result = 31 * result + (quantityText != null ? quantityText.hashCode() : 0);
        result = 31 * result + (loadingYes ? 1 : 0);
        return result;
    }
}

