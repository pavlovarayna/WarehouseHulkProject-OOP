package Individuals;

import Goods.GoodType;
import Orders.Order;
import Orders.Orderable;
import Orders.PayType;

import java.util.ArrayList;

public class Administrator extends Individual implements Orderable, Sellable, Searchable, Payable {

    private double salary;
    private static int counterOrderNumberWarehouse = 1;           // number of next order
    private ArrayList<Order> adminOrders;

    public Administrator(String name, double salary, PersonalType personalType, String nationality, String personalInfo) {
        super(name, personalType, nationality, personalInfo);
        adminOrders = new ArrayList<>();
        setSalary(salary);
    }

    public Administrator(PersonalType personalType){
        super(personalType);

    }

    ArrayList<Order> getAdminOrders() {
        return adminOrders;
    }

    private double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }




    @Override
    public void searchProduct(String productName, int quantity, GoodType goodType) {
        System.out.println();
        System.out.printf("Product to reload - %s, quantity: %d. Contact with %s suppliers.\n",
                productName, quantity, goodType);
    }

    // administrator add to his personal array list with ordered products and numerates them
    @Override
    public void order(GoodType goodType, int quantity, double orderValue, String productName) {
        Order order = new Order(goodType, productName, orderValue, counterOrderNumberWarehouse, quantity, PersonalType.ADMINISTRATOR);
        adminOrders.add(order);
        System.out.printf("Order add to admin daily orders. Product: %s - \"%s\", quantity: %d. Order type: %s. Total amount: %.2f lv.\n",
                productName, goodType, quantity, order.getOrderActivity(), orderValue);
        counterOrderNumberWarehouse++;
    }

    // administrator pays to supplier when order new product quantity
    @Override
    public void pay(PayType payType, double amount, PersonalType personalType) {
        System.out.printf("[%s] Paying the supply order amount of %.2f by %s.\n", personalType, amount, payType);
    }

    // administrator sell to customer the order and ++case income
    @Override
    public void sell(Case casse, double contractAmount) {
        casse.setIncome(casse.getIncome() + contractAmount);             // ++ the income of the warehouse
        System.out.println("Payment accept! Welcome again!");
    }

    // show the administrator individual personal information
    @Override
    public String toString(){
        return String.format("[%s] Name: %s, salary: %.2f, info: %s, nationality: %s.",
                getPersonalType(), getName(), getSalary(), getPersonalInfo(), getNationality());
    }
}
