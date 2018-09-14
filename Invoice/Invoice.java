package Invoice;

import Goods.GoodType;

public class Invoice {

    private String customerName;
    private int invoiceNumber;
    private double invoicePrice;
    private GoodType goodType;
    private int quantity;
    private InvoiceType invoiceType;

    public Invoice(String customerName, int invoiceNumber, double invoicePrice, GoodType goodType, int quantity, InvoiceType invoiceType) {
        this.customerName = customerName;
        this.invoiceNumber = invoiceNumber;
        this.invoicePrice = invoicePrice;
        this.goodType = goodType;
        this.quantity = quantity;
        this.invoiceType = invoiceType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public void showInvoice(){   // pokazva ni info za fakturata
        System.out.printf("Invoice №:%d, amount: %f, created for %s, product: %s with quantity: %d.",
                getInvoiceNumber(), getInvoicePrice(), getCustomerName(), getGoodType(), getQuantity());
    }
}
