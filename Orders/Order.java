package Orders;

import Goods.GoodType;
import Individuals.PersonalType;

public class Order {

    private GoodType goodType;
    private String productName;
    private double totalAmount;
    private int number;
    private int quantity;
    private OrderActivity orderActivity;
    private PersonalType personalType;


    public Order(GoodType goodType, String productName, double totalAmount, int number, int quantity, PersonalType personalType) {
        this.productName = productName;
        this.goodType = goodType;
        this.totalAmount = totalAmount;
        this.number = number;
        this.quantity = quantity;
        this.orderActivity = OrderActivity.ACTIVE;
        this.personalType = personalType;
    }

    public void showOrder(){      // for checking customer and warehouse orders
        System.out.printf("Order number[%d][%s]: %s-\"%s\", quantity: %d. Total amount: %.2f. Made from: [%s]\n",
                getNumber(), orderActivity, getGoodType(), getProductName(), getQuantity(), getTotalAmount(), getPersonalType());
    }


    public GoodType getGoodType() {
        return goodType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    public int getNumber() {
        return number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderActivity getOrderActivity() {
        return orderActivity;
    }

    public void setOrderActivity(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
    }

    public String getProductName() {
        return productName;
    }

    private PersonalType getPersonalType() {
        return personalType;
    }
}
