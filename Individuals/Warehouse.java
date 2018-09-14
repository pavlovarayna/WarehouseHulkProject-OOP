package Individuals;

import Goods.*;
import Orders.Order;
import Orders.OrderActivity;
import Orders.PayType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Warehouse {
    private Scanner scanner = new Scanner(System.in);

    private String name;
    private ArrayList<Good> inventory;
    private ArrayList<Alcohol> alcoholInventory;
    private ArrayList<SoftDrink> softDrinkInventory;
    private ArrayList<Food> foodsInventory;
    private ArrayList<Domestic> domesticProductsInventory;
    private ArrayList<Order> warehouseOrders;
    private ArrayList<Good> toBeReload;               // list to be reloaded from the suppliers


    public Warehouse(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.alcoholInventory = new ArrayList<>();
        this.softDrinkInventory = new ArrayList<>();
        this.foodsInventory = new ArrayList<>();
        this.domesticProductsInventory = new ArrayList<>();
        this.warehouseOrders = new ArrayList<>();
        this.toBeReload = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    // method to show actual goods, quantity and info
    public void showCatalog() {
        System.out.println("-----------------------------");
        System.out.println("WAREHOUSE PRODUCT CATALOGUE: ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
        for (Good good : alcoholInventory) {
            good.showProduct();
        }
        for (Good good : softDrinkInventory) {
            good.showProduct();
        }
        for (Good good : foodsInventory) {
            good.showProduct();
        }
        for (Good good : domesticProductsInventory) {
            good.showProduct();
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void toBeReload() {                 // check the list for reloading from supplier
        for (Good good : toBeReload) {
            System.out.printf("Product to reload: %s - %s, quantity: %d.\n", good.getName(), good.getGoodType(), good.getTotalQuantity());
        }
    }

    public void showOrders() {                   // shows all warehouse orders from customers and to suppliers
        for (Order order : warehouseOrders) {
            order.showOrder();

        }
    }

    // add products in the warehouse and put it to be reloading for the first time by boss
    void addAlcohol(String name, double priceWarehouse, int totalQuantity, GoodType goodType,
                    double alcoholContaining, AlcoholType alcoholType, PackageType packageType) {
        Alcohol alcohol = new Alcohol(name, priceWarehouse, totalQuantity, goodType, alcoholContaining, alcoholType, packageType);
        inventory.add(alcohol);
        alcoholInventory.add(alcohol);
    }

    // add products in the warehouse and put it to be reloading for the first time by boss
    void addSoftDrink(String name, double priceWarehouse, int totalQuantity, GoodType goodType, SoftDrinkType softDrinkType,
                      PackageType packageType) {
        SoftDrink softDrink = new SoftDrink(name, priceWarehouse, totalQuantity, goodType, softDrinkType, packageType);
        inventory.add(softDrink);
        softDrinkInventory.add(softDrink);
    }

    // add products in the warehouse and put it to be reloading for the first time by boss
    void addFood(String name, double priceWarehouse, int totalQuantity, GoodType goodType, FoodType foodType, PackageType packageType) {
        Food food = new Food(name, priceWarehouse, totalQuantity, goodType, foodType, packageType);
        inventory.add(food);
        foodsInventory.add(food);
    }

    // add products in the warehouse and put it to be reloading for the first time by boss
    void addDomesticProducts(String name, double priceWarehouse, int totalQuantity, GoodType goodType, DomesticType domesticType) {
        Domestic domesticP = new Domestic(name, priceWarehouse, totalQuantity, goodType, domesticType);
        inventory.add(domesticP);
        domesticProductsInventory.add(domesticP);
    }


    private void checkDigit(int digit) throws MyCustomException { // check exeption for the customer input number
        if (digit <= 0) {
            throw new MyCustomException();
        }
    }


    public void searchInWarehouse(Customer customer, Administrator admin, Case casse) throws MyCustomException {
        //throws MyCustomException for not positive number in input
        System.out.printf("Hello, %s!\n", customer.getName());
        while (true) {
            GoodType goodType = GoodType.valueOf(choosingGoodType());
            String productName = "";
            switch (goodType) {
                case ALCOHOL:
                    productName = choiceOfAlcoholInventory();
                    break;
                case FOOD:
                    productName = choiceOfFoodInventory();
                    break;
                case DOMESTIC:
                    productName = choiceOfDomesticInventory();
                    break;
                case SOFTDRINK:
                    productName = choiceOfSoftDrinkInventory();
                    break;
                case OTHER:
                    productName = "OTHER";
                    System.out.printf("No goods in part %s.", productName);
                    System.out.println();
                    break;
                default:
                    break;
            }

            if (productName.equals("OTHER") || productName.equals("")) {
                System.out.println("We will load soon. Try with the listed goods!");
                continue;
            }

            scanner.nextLine();
            int quantity = 0;

            while (true) {               // works until customer inputs valid number
                try {                          //MyCustomException
                    System.out.println("Now please enter the needed quantity:");
                    quantity = scanner.nextInt();
                    checkDigit(quantity);
                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("You must enter a valid number!");
                    scanner.nextLine();
                } catch (MyCustomException mce) {             // custom exception for input not possitiv quantity
                    System.out.println("You must enter a number bigger than 0!");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            customer.searchProduct(productName, quantity, goodType);  //customera input the searching good
            // check if good is available in the warehouse
            boolean contains = false;
            for (Good good : inventory) {
                if (good.getTotalQuantity() >= quantity && good.getName().equalsIgnoreCase(productName) && good.getGoodType() == goodType) {
                    contains = true;
                    System.out.printf("We have \"%s\" %s available.\n", productName, goodType);
                    System.out.println("Do you want to put this product as a request? (Yes/No)");
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        constructCustomerRequest(customer, productName, goodType, quantity);
                        break;
                    }
                } else if (good.getName().equalsIgnoreCase(productName) && good.getGoodType() == goodType && quantity > good.getTotalQuantity()) {  //promeneno
                    contains = true;
                    System.out.printf("Sorry, available quantity %s of %s (%s).\n" +
                                    "Do you want to order less quantity, which is under %d, and then put it as a request? (Yes/No)\n"
                            , good.getTotalQuantity(), productName, goodType, good.getTotalQuantity());
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.printf("Please enter new quantity under %d.\n", good.getTotalQuantity());

                        int newQuantity = 0;

                        while (true) {               // works until customer inputs valid number
                            try {                          //MyCustomException
                                System.out.println("Now please enter the needed quantity:");
                                newQuantity = scanner.nextInt();
                                checkDigit(newQuantity);
                                break;
                            } catch (InputMismatchException ime) {
                                System.out.println("You must enter a number!");
                                scanner.nextLine();
                            } catch (MyCustomException mce) {              // catch exeption for not positive input number
                                System.out.println("You must enter a integer bigger than 0!!!");
                                scanner.nextLine();
                            }
                        }
                        System.out.printf("Customer updated needed quantity: %d.\n", newQuantity);
                        constructCustomerRequest(customer, productName, goodType, newQuantity);
                        scanner.nextLine();
                        break;
                    }
                }
            }
            if (contains == false) {
                System.out.println("The product is not available in our catalog!");
            }// output when there's no such product in the catalogue
            System.out.println("Would you like to continue? (Yes/No)");
            String answerCustomer = scanner.nextLine();
            if (answerCustomer.equalsIgnoreCase("y")
                    || answerCustomer.equalsIgnoreCase("yes")) {
                continue;
            } else {
                break;
            }
        }


        int numberOfCustomerRequests = customer.getCustomerOrders().size();  //counter for all customer requests
        do {
            System.out.println("Do you want to check your requests? (Yes/No)");
            String answerCustomer = scanner.nextLine();
            if (answerCustomer.equalsIgnoreCase("y")
                    || answerCustomer.equalsIgnoreCase("yes")) {
                customer.checkRequests();
            } else {
                break;
            }

            if (!customer.getCustomerOrders().isEmpty()) {
                if (numberOfCustomerRequests > 0) {
                    System.out.println("Do you want to cancel any request? (Yes/No)");    //appears only when have request
                    String answerCancel = scanner.nextLine();
                    if (answerCancel.equalsIgnoreCase("yes") || answerCancel.equalsIgnoreCase("y")) {
                        customer.cancelRequest();
                        numberOfCustomerRequests--;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        } while (true);


        if (!customer.getCustomerOrders().isEmpty() && numberOfCustomerRequests != 0) {  //when have ACTIVE requests
            boolean canSign = false;
            for (Order order : customer.getCustomerOrders()) {                // check not to sign contract, if all requests are canceled
                if (order.getOrderActivity() == OrderActivity.ACTIVE) {
                    canSign = true;
                }
            }
            if (canSign == true) {
                System.out.println("Do you want to sign contract now? (Yes/No)");
                String answerSign = scanner.nextLine();
                if (answerSign.equalsIgnoreCase("yes")) {
                    finalizeOrder(customer, admin, casse);
                } else {
                    System.out.println("Customer refused to sign and left.");
                }
            } else {
                System.out.println("You have no requests to sign a contract. Have a nice day!");
            }
        } else {
            System.out.println("Customer has no requests to order.");
        }
    }


    private String choosingGoodType() {
        String choice = "";
        boolean isCorrectNumber = false;
        int i = 1;
        System.out.println("Which is the good type of your needed product?");
        for (GoodType type :
                GoodType.values()) {
            System.out.println(i + " - " + type);
            i++;
        }

        System.out.println("The system expects your choice..");
        //catch MyCustomException when input is not number
        int n = 0;
        while (!isCorrectNumber) {
            try {
                n = scanner.nextInt();
                if (n == 0 || n > 5) {                                 //when input number is not between 1-5
                    System.out.println("Enter a number from 1-5!");
                }
                checkDigit(n);
                // choice of good type
                switch (n) {
                    case 1:
                        choice = "ALCOHOL";
                        isCorrectNumber = true;
                        break;
                    case 2:
                        choice = "FOOD";
                        isCorrectNumber = true;
                        break;
                    case 3:
                        choice = "DOMESTIC";
                        isCorrectNumber = true;
                        break;
                    case 4:
                        choice = "SOFTDRINK";
                        isCorrectNumber = true;
                        break;
                    case 5:
                        choice = "OTHER";
                        isCorrectNumber = true;
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
            } catch (MyCustomException mce) {             // custom exception for not positive quantity
                System.out.println("You must enter a positive number!");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private String choiceOfAlcoholInventory() { // shows users choice for good trademarks
        System.out.println();
        System.out.println("Please enter product name:");
        String choice = "";
        int i = 1;
        for (Alcohol alcohol :
                alcoholInventory) {
            System.out.println(i + " - " + alcohol.getName());
            i++;
        }

        System.out.println("The system expects your choice..");
        int n = 0;

        do { //exception for not in the good collection
            try {
                n = scanner.nextInt();
                checkDigit(n);
                if ((0 < n) && (n < i)) {
                    break;
                } else {
                    System.out.println();
                    System.out.printf("Enter a number from 1- %d!!\n", i - 1);
                }
            } catch (InputMismatchException ime) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
            } catch (MyCustomException mce) {             // checks for not positive number
                System.out.println("You must enter a positive number!!!");
                scanner.nextLine();
            }
        } while (true);

        int nameNumber = 1;
        for (Alcohol alcohol :
                alcoholInventory) {
            if (nameNumber == n) {
                choice = alcohol.getName();
                break;
            }
            nameNumber++;
        }
        return choice;
    }


    private String choiceOfFoodInventory() { // shows users choice for good trademarks
        System.out.println();
        System.out.println("Please enter product name:");
        String choice = "";
        int i = 1;
        for (Food food :
                foodsInventory) {
            System.out.println(i + " - " + food.getName());
            i++;
        }

        System.out.println("The system expects your choice..");
        int n = 0; // exeption if the input choice is not a number

        do { //exception for not in the good collection
            try {
                n = scanner.nextInt();
                checkDigit(n);
                if ((0 < n) && (n < i)) {
                    break;
                } else {
                    System.out.println();
                    System.out.printf("Enter a number from 1- %d!!", i - 1);
                }
            } catch (InputMismatchException ime) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
            } catch (MyCustomException mce) {             // checks for not positive number
                System.out.println("You must enter a positive number!");
                scanner.nextLine();
            }
        } while (true);

        int nameNumber = 1;
        for (Food food :
                foodsInventory) {
            if (nameNumber == n) {
                choice = food.getName();
                break;
            }
            nameNumber++;
        }
        return choice;
    }

    private String choiceOfSoftDrinkInventory() { // shows the choice for softdrinks marks
        System.out.println();
        System.out.println("Please enter product name:");
        String choice = "";
        int i = 1;
        for (SoftDrink drink :
                softDrinkInventory) {
            System.out.println(i + " - " + drink.getName());
            i++;
        }

        System.out.println("The system expects your choice..");
        int n = 0; // exception catch if the input is not a number

        do { //check if the input is in the range of collection
            try {
                n = scanner.nextInt();
                checkDigit(n);
                if ((0 < n) && (n < i)) {
                    break;
                } else {
                    System.out.println();
                    System.out.printf("Enter a number from 1- %d!!", i - 1);
                }
            } catch (InputMismatchException ime) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
            } catch (MyCustomException mce) {             // if input is not positive number
                System.out.println("You must enter a positive number!");
                scanner.nextLine();
            }
        } while (true);

        int nameNumber = 1;
        for (SoftDrink drink :
                softDrinkInventory) {
            if (nameNumber == n) {
                choice = drink.getName();
                break;
            }
            nameNumber++;
        }
        return choice;
    }

    private String choiceOfDomesticInventory() { // // shows the choice for domestics marks
        System.out.println();
        System.out.println("Please enter product name:");
        String choice = "";
        int i = 1;
        for (Domestic domestic :
                domesticProductsInventory) {
            System.out.println(i + " - " + domestic.getName());
            i++;
        }

        System.out.println("The system expects your choice..");
        int n = 0; // catch if the input is not a number

        do { //check if the number is not in the range of the collection
            try {
                n = scanner.nextInt();
                checkDigit(n);
                if ((0 < n) && (n < i)) {
                    break;
                } else {
                    System.out.println();
                    System.out.printf("Enter a number from 1- %d!!", i - 1);
                }
            } catch (InputMismatchException ime) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
            } catch (MyCustomException mce) {             // if the number is not positive
                System.out.println("You must enter a positive number!");
                scanner.nextLine();
            }
        } while (true);

        int nameNumber = 1;
        for (Domestic domestic :
                domesticProductsInventory) {
            if (nameNumber == n) {
                choice = domestic.getName();
                break;
            }
            nameNumber++;
        }
        return choice;
    }


    // prepare the requested order when it's made.
    private void constructCustomerRequest(Customer customer, String productName, GoodType goodType, int quantity) {
        double orderValue = 0;
        for (Good good : inventory) {
            if (good.getName().equalsIgnoreCase(productName) && good.getTotalQuantity() >= quantity) {
                orderValue = good.getPriceWarehouse() * quantity;
                customer.order(goodType, quantity, orderValue, productName);
                break;
            }
        }
    }


    private void finalizeOrder(Customer customer, Administrator admin, Case casse) {
        System.out.printf("- %s wants to proceed and sign a Order contract for the requests!\n", customer.getName());
        // put the customer order in the warehouse orders
        double contractAmount = 0;       //the start value of the contract is 0
        for (int i = 0; i < customer.getCustomerOrders().size(); i++) {   // -- good quantity in the warehouse after the customer order
            for (Good good : inventory) {
                if (customer.getCustomerOrders().get(i).getOrderActivity() == OrderActivity.ACTIVE
                        && customer.getCustomerOrders().get(i).getProductName().equalsIgnoreCase(good.getName())) {
                    contractAmount += customer.getCustomerOrders().get(i).getTotalAmount();   // counts the sum of all orders of the customer
                    customer.getCustomerOrders().get(i).setOrderActivity(OrderActivity.FINISHED);
                    good.setTotalQuantity(good.getTotalQuantity() -
                            customer.getCustomerOrders().get(i).getQuantity());    // -- quantity of the ordered product
                    warehouseOrders.add(customer.getCustomerOrders().get(i));        // put the customer order in all warehouse orders, if not canceled
                }
            }
        }// check the budget of the customer before the order
        if (customer.getBudget() >= contractAmount) { //budget is enough
            System.out.printf("Order made from [%s] customer: %s, %s is in process!\n" +
                            "The contract is signed, the invoice was made.\n" + "Total amount (%.2f) lv. Warehouse is waiting for paying.\n", customer.getCustomerType(),
                    customer.getName(), customer.getPersonalType(), contractAmount);
            System.out.println("How do you prefer to pay?");
            int i = 1;
            for (PayType type :
                    PayType.values()) {
                System.out.println(i + " - " + type);
                i++;
            }
            System.out.println("The system expects your choice..");
            int choiceOfPayType = 0;
            PayType payType = null;
            boolean inputCorrect = false;
            while (!inputCorrect) {               // continue until the input is INTEGER
                try {            // my custom exception
                    choiceOfPayType = scanner.nextInt();
                    if (choiceOfPayType > 3 || choiceOfPayType == 0) {
                        System.out.println("Enter a correct number!");
                    }
                    checkDigit(choiceOfPayType);
                    switch (choiceOfPayType) {
                        case 1:
                            payType = PayType.CREDIT;
                            inputCorrect = true;
                            break;
                        case 2:
                            payType = PayType.DEBITCARD;
                            inputCorrect = true;
                            break;
                        case 3:
                            payType = PayType.CASH;
                            inputCorrect = true;
                            break;
                        default:
                            break;
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("You must enter a number!");
                    scanner.nextLine();
                } catch (MyCustomException mce) {
                    System.out.println("Enter a positive number!");
                    scanner.nextLine();
                }
            }
            customer.pay(payType, contractAmount, customer.getPersonalType());
            customer.setBudget(customer.getBudget() - contractAmount);       // set down the customer budget
            admin.sell(casse, contractAmount);  //administratora sells and ++ warehouse income
        } else {   // budjet is not enough
            System.out.printf("- The order is processed! Sorry, but You don't have enough budget to finish the order! Deficit [%.2f]\n",
                    contractAmount - customer.getBudget());
        }
        customer.getCustomerOrders().clear();  // clears the request list of the customer when the process is finished.
        customer.setCounterOrderNumberCustomer(1);
    }


    public void checkProductsQuantity(Administrator admin, Supplier supplier, Case casse) {     //check goods for available quantity and find for reloading
        boolean isFull = true;
        for (Good good : inventory) {     // if it could be reloaded
            if (good.getTotalQuantity() < 50) {  // when it's under 50, add in toBeReloaded list
                if (!toBeReload.contains(good)) {
                    isFull = false;
                    toBeReload.add(good);
                    System.out.printf("Product to reload: %s [%s], quantity: %d.\n", good.getName(), good.getGoodType(), good.getTotalQuantity());
                }
            }
        }
        if (isFull) {   // if no need to reload
            System.out.println("All products have the normal amount of quantity!");
        } else {
            System.out.println("Do you want to proceed to reloading? (Yes/No)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                contactProducers(admin, supplier, casse);
            } else {
                System.out.println("No reload");
            }
        }
    }

    private void contactProducers(Administrator admin, Supplier supplier, Case casse) {
        double total = 0;
        System.out.printf("Admin %s processing contact with producers..\n", admin.getName());
        for (Good good : toBeReload) {
            admin.searchProduct(good.getName(), 100, good.getGoodType());  //fix the reloading quantity 100
            double orderValue = good.getPriceProducer() * 100;
            total += orderValue;  // counts the total sum of the reloading
            admin.order(good.getGoodType(), 100, orderValue, good.getName()); // administrator orders one by one, because of different suppliars
            admin.pay(PayType.CREDIT, orderValue, admin.getPersonalType());                  // administrator pays the order
            supplier.sell(casse, orderValue);                          // suplier sells to warehouse and ++ outcome in the case

            if (inventory.contains(good)) {                         // upload the quantity of the products in the warehouse
                good.setTotalQuantity(good.getTotalQuantity() + 100);
            }
        }
        toBeReload.clear(); //clear the product from the list after reloading
        for (Order order : admin.getAdminOrders()) {               // add the administrator orders to suppliers in the list of warehouse orders
            order.setOrderActivity(OrderActivity.FINISHED);
            warehouseOrders.add(order);
        }
        System.out.println();
        System.out.printf("Total sum of reloading: [%.2f]\n", total);
    }
}

