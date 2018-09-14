package Individuals;

import Goods.GoodType;
import Orders.Order;
import Orders.OrderActivity;
import Orders.Orderable;
import Orders.PayType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Customer extends Individual implements Orderable, Searchable, Payable {

    private CustomerType customerType;
    private double budget;
    private ArrayList<Order> customerOrders;

    private static int counterOrderNumberCustomer = 1;           //every customer's requests start from 1
    private static final double DISCOUNT_10 = 0.1;     //discount up to 10 quality
    private static final double DISCOUNT_25 = 0.25;     // discount up to 25 quality
    private static final double DELIVERY_PRICE_10 = 0.05;       // price up to 10 quality
    private static final double DELIVERY_PRICE_25 = 0.1;      // price up to 10 quality


    public Customer(String name, double budget, PersonalType personalType, String nationality, String personalInfo, CustomerType customerType) {
        super(name, personalType, nationality, personalInfo);
        this.customerType = customerType;
        this.customerOrders = new ArrayList<>();
        this.budget = budget;
    }

    public static int getCounterOrderNumberCustomer() {
        return counterOrderNumberCustomer;
    }

    static void setCounterOrderNumberCustomer(int counterOrderNumberCustomer) {
        Customer.counterOrderNumberCustomer = counterOrderNumberCustomer;
    }


    public double getBudget() {
        return budget;
    }

    void setBudget(double budget) {
        this.budget = budget;
    }

    CustomerType getCustomerType() {
        return customerType;
    }

    ArrayList<Order> getCustomerOrders() {
        return customerOrders;
    }


    @Override
    public void searchProduct(String productName, int quantity, GoodType goodType) {
        System.out.println();
        System.out.printf("Customer is searching for %s [%s], quantity: %d.\n", productName, goodType, quantity);
    }

    @Override
    public void order(GoodType goodType, int quantity, double orderValue, String productName) {
        if(quantity > 10){                                         // count the price for up to 10 products
            orderValue = orderValue - (DISCOUNT_10*orderValue);
            orderValue = orderValue + (DELIVERY_PRICE_10*orderValue);
        }else if(quantity > 25){                                   // count the price for up to 25 products
            orderValue -= DISCOUNT_25*orderValue;
            orderValue += DELIVERY_PRICE_25*orderValue;
        }
        Order order = new Order(goodType,productName, orderValue, counterOrderNumberCustomer, quantity, PersonalType.CUSTOMER);
        customerOrders.add(order);
        System.out.printf("Request is added to customer orders. Product: %s - [%s], quantity: %d. Order type: %s.\n",
                productName, goodType, quantity, order.getOrderActivity());
        counterOrderNumberCustomer++;
    }


    void checkRequests(){                     // customer could check his orders
        if(!customerOrders.isEmpty()){
            for (Order order:customerOrders) {
                order.showOrder();
            }
        }else{
            System.out.println("No active requests.");
        }
    }

    private void checkDigit(int digit)throws MyCustomException {   // check exeption for valid integer choice
        if(digit < 0){
            throw new MyCustomException();
        }
    }
    //"You must enter a valid number from list below"

    public void cancelRequest(){    // customer could cancel orders before signing contract
        Scanner scanner = new Scanner(System.in);
        if(!getCustomerOrders().isEmpty()){
            System.out.println("Which request you want to remove from orders list?");
            int requestNumber = 0;
            try{
                requestNumber = scanner.nextInt();
                checkDigit(requestNumber);
            }catch (MyCustomException mce){
                System.out.println("Enter a positive number!");
            }catch (InputMismatchException ime){
                System.out.println("Enter a number!");
            }
            boolean hasNumber = false;
            for (Order order:customerOrders) {
                if(order.getNumber()==requestNumber){
                    order.setOrderActivity(OrderActivity.CANCELLED);      // set the order activity to be cancelled
                    counterOrderNumberCustomer--;
                    hasNumber = true;
                }
            }
            if(hasNumber==false){
                System.out.println("No such number!");
                cancelRequest();
            }else{
                System.out.printf("Request number [%d] cancelled.\n", requestNumber);
            }
        }
    }

    @Override // custormer wants to pay his orders
    public void pay(PayType payType, double amount, PersonalType personalType) {
        System.out.printf("-[%s] Thank you for the good service!\n" +
                "I want to pay the bill [%.2f] by %s.\n", personalType, amount, payType);

    }

    @Override //show the customer individual personal information
    public String toString(){
        return String.format("[%s] Name: %s, budget: %.2f, info: %s, nationality: %s.",
                getPersonalType(), getName(), getBudget(), getPersonalInfo(), getNationality());
    }
}
