
import Individuals.*;

public class Application {

    public static void main(String[] args) throws MyCustomException {


        // we need to set up the warehouse, the warehouse case, the boss, customers, administrators, suppliers

        Boss boss = new Boss("Ivan Ivanov", PersonalType.BOSS, "BG", "address: Sofia, EGN: 0000000000");
        Administrator admin = new Administrator("Stefcho", 2500, PersonalType.ADMINISTRATOR, "BG", "info");
        Customer customer = new Customer("Pencho Tenev", 600, PersonalType.CUSTOMER, "BG", "address Sofia, EGN: 0000000000", CustomerType.LONGTIME);
        Customer customer2 = new Customer("PUB 'Drunken Sailor'", 6000, PersonalType.CUSTOMER, "BG", "address Pernik, EIK: 0000000000", CustomerType.PARTTIME);
        Supplier supplier = new Supplier(PersonalType.SUPPLIER);

        Warehouse warehouse = new Warehouse("TheHulk");
        Case warehouseCase = new Case(2500000,0);

        // the boss sets up the warehouse and the case
        boss.investWarehouseAndCase(warehouse,warehouseCase);


        // we need to check the starting balance of the warehouse
        warehouseCase.checkBalance();

        // the boss setting up the warehouse catalog and showing it to the world
        boss.setUpCatalog(warehouse);
        warehouse.showCatalog();


        // we need to check the products quantity and if any we need to reload them
        warehouse.checkProductsQuantity(admin, supplier, warehouseCase);
        System.out.println();



        // starting the procedure of searching products in the warehouse with acting the two customers
        warehouse.searchInWarehouse(customer, admin, warehouseCase);
        System.out.println();
        warehouse.searchInWarehouse(customer2, admin, warehouseCase);
        System.out.println();



        // after the customers are ready with their contracts we can check their budgets and the warehouse income/outcome balance
        System.out.println(customer.getBudget());
        System.out.println(customer2.getBudget());
        warehouseCase.checkBalance();

        //and finally we can see all the orders that have been made in the warehouse and the diff in the products quantity
        warehouse.showOrders();
        warehouse.showCatalog();

        // after check
        warehouse.checkProductsQuantity(admin, supplier, warehouseCase);
        warehouse.showOrders();
        warehouse.showCatalog();


    }
}

