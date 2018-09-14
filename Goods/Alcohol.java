package Goods;

public class Alcohol extends Drinks{

    private double alcoholContaining;
    private PackageType packageType;      
    private AlcoholType alcoholType;


    public Alcohol(String name, double priceWarehouse, int totalQuantity, GoodType goodType,
                   double alcoholContaining, AlcoholType alcoholType , PackageType packageType) {
        super(name, priceWarehouse, totalQuantity, goodType);
        this.alcoholContaining = alcoholContaining;
        this.alcoholType = alcoholType;
        this.packageType = packageType;
    }

    private double getAlcoholContaining() {
        return alcoholContaining;
    }

    private AlcoholType getAlcoholType() {
        return alcoholType;
    }

    private PackageType getPackageType() { return packageType;  }

    @Override
    public void showProduct() {
        System.out.printf("[%s][%s] [\"%s\"], price: %.2f, quantity: %d, package: %s, alcohol: %.2f procent.\n", 
                getGoodType(), getAlcoholType(), getName(), getPriceWarehouse(), getTotalQuantity(), getPackageType(), getAlcoholContaining());
        System.out.println();
    }
}
