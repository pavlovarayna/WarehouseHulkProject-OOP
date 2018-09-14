package Individuals;

import Goods.*;

public class Boss extends Individual{


    public Boss(String name, PersonalType personalType, String nationality, String personalInfo) {
        super(name, personalType, nationality, personalInfo);
    }

    // welcome when set the warehouse with income in the case
    public void investWarehouseAndCase(Warehouse warehouse, Case warehouseCase) {
        System.out.printf("New warehouse '%s' is open now by %s.", warehouse.getName(), getName());
        System.out.println();
        System.out.printf("Warehouse case is set - income %.2f, outcome %.2f", warehouseCase.getIncome(), warehouseCase.getOutcome());
        System.out.println();
    }

    // show the boss individual personal information
    @Override
    public String toString(){
        return String.format("[%s] Name: %s, info: %s, nationality: %s.",
                getPersonalType(), getName(), getPersonalInfo(), getNationality());
    }




    // boss fills the different products in the warehouse in the begging; only the boss could add type of products
    public void setUpCatalog(Warehouse warehouse){
        warehouse.addAlcohol("Scotch RedLabel", 19.99, 0, GoodType.ALCOHOL, 40, AlcoholType.WHISKEY, PackageType.BOTTLED);
        warehouse.addAlcohol("vodka Smirnoff", 12.99, 0, GoodType.ALCOHOL, 40, AlcoholType.VODKA, PackageType.BOTTLED);
        warehouse.addAlcohol("beer Shumensko", 1.20, 0, GoodType.ALCOHOL, 4.3, AlcoholType.BEER, PackageType.BOTTLED);
        warehouse.addAlcohol("beer Kamenitza", 1.10, 0, GoodType.ALCOHOL, 4.2, AlcoholType.BEER, PackageType.BOTTLED);
        warehouse.addAlcohol("Rose Katarzina", 7.99, 0, GoodType.ALCOHOL, 40, AlcoholType.WINE, PackageType.POURING);
        warehouse.addAlcohol("Merlo Cherga", 9.99, 0, GoodType.ALCOHOL, 40, AlcoholType.WINE, PackageType.BOTTLED);
        warehouse.addAlcohol("burban Bushmills", 24.99, 0, GoodType.ALCOHOL, 40, AlcoholType.WHISKEY, PackageType.BOTTLED);
        warehouse.addSoftDrink("Pepsi", 1.99, 0, GoodType.SOFTDRINK, SoftDrinkType.FIZZY, PackageType.BOTTLED);
        warehouse.addSoftDrink("CocaCola", 2.10, 0, GoodType.SOFTDRINK, SoftDrinkType.FIZZY, PackageType.BOTTLED);
        warehouse.addSoftDrink("Fanta", 2.00, 0, GoodType.SOFTDRINK, SoftDrinkType.FIZZY, PackageType.BOTTLED);
        warehouse.addSoftDrink("Spring Water", 0.90, 0, GoodType.SOFTDRINK, SoftDrinkType.WATER, PackageType.BOTTLED);
        warehouse.addSoftDrink("Juice", 1.59, 0, GoodType.SOFTDRINK, SoftDrinkType.JUICE, PackageType.POURING);
        warehouse.addDomesticProducts("FloorCleaner Mr.Propoer", 2.69, 0, GoodType.DOMESTIC, DomesticType.CLEANING_PRODUCT);
        warehouse.addDomesticProducts("Bleach ACE", 2.29, 0, GoodType.DOMESTIC, DomesticType.CLEANING_PRODUCT);
        warehouse.addDomesticProducts("Softener Cocolino", 5.10, 0, GoodType.DOMESTIC, DomesticType.CLEANING_PRODUCT);
        warehouse.addDomesticProducts("Table", 222.69, 0, GoodType.DOMESTIC, DomesticType.FURNITURE);
        warehouse.addDomesticProducts("Chair", 22.69, 0, GoodType.DOMESTIC, DomesticType.FURNITURE);
        warehouse.addDomesticProducts("Wardrobe", 422.69, 0, GoodType.DOMESTIC, DomesticType.FURNITURE);
        warehouse.addDomesticProducts("Couch", 522.69, 0, GoodType.DOMESTIC, DomesticType.FURNITURE);
        warehouse.addDomesticProducts("TV Samsung", 1029.69, 0, GoodType.DOMESTIC, DomesticType.ELECTRONIC);
        warehouse.addDomesticProducts("TV Sony", 929.69, 0, GoodType.DOMESTIC, DomesticType.ELECTRONIC);
        warehouse.addDomesticProducts("IPhone", 1329.69, 0, GoodType.DOMESTIC, DomesticType.ELECTRONIC);
        warehouse.addDomesticProducts("InflatablePool", 339.69, 0, GoodType.DOMESTIC, DomesticType.GARDEN);
        warehouse.addDomesticProducts("Umbrella", 49.69, 0, GoodType.DOMESTIC, DomesticType.GARDEN);
        warehouse.addDomesticProducts("Sunbed", 19.69, 0, GoodType.DOMESTIC, DomesticType.GARDEN);
        warehouse.addFood("Chicken Wings", 4.79, 0, GoodType.FOOD, FoodType.MEAT, PackageType.FREEZED);
        warehouse.addFood("BeefSteck", 9.79, 0, GoodType.FOOD, FoodType.MEAT, PackageType.FREEZED);
        warehouse.addFood("PorkSteck", 7.79, 0, GoodType.FOOD, FoodType.MEAT, PackageType.FREEZED);
        warehouse.addFood("Potato", 3.79, 0, GoodType.FOOD, FoodType.VEGETABLE, PackageType.FRESH);
        warehouse.addFood("Tomato", 3.59, 0, GoodType.FOOD, FoodType.VEGETABLE, PackageType.FRESH);
        warehouse.addFood("Carrot", 3.69, 0, GoodType.FOOD, FoodType.VEGETABLE, PackageType.FRESH);
        warehouse.addFood("Cucumber", 1.79, 0, GoodType.FOOD, FoodType.VEGETABLE, PackageType.FRESH);
        warehouse.addFood("Apple", 2.49, 0, GoodType.FOOD, FoodType.FRUIT, PackageType.FRESH);
        warehouse.addFood("Orange", 2.29, 0, GoodType.FOOD, FoodType.FRUIT, PackageType.FRESH);
        warehouse.addFood("Strawberry", 5.49, 0, GoodType.FOOD, FoodType.FRUIT, PackageType.FREEZED);
        warehouse.addFood("Melon", 2.49, 0, GoodType.FOOD, FoodType.FRUIT, PackageType.FRESH);
        warehouse.addFood("Lemon", 3.49, 0, GoodType.FOOD, FoodType.FRUIT, PackageType.FRESH);
    }
}
