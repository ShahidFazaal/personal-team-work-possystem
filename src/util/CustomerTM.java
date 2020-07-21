package util;

public class CustomerTM {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private int customerContact;

    public CustomerTM() {
    }

    public CustomerTM(int customerId, String customerName, String customerAddress, int customerContact) {
        this.setCustomerId(customerId);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCustomerContact(customerContact);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(int customerContact) {
        this.customerContact = customerContact;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContact=" + customerContact +
                '}';
    }
}
