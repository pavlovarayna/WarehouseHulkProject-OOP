package Goods;

public class SoftDrink extends Drinks {

    private SoftDrinkType softDrinkType;
    private PackageType packageType;         

    public SoftDrink(String name, double priceWarehouse, int totalQuantity, GoodType goodType, SoftDrinkType softDrinkType, PackageType packageType) {
        super(name, priceWarehouse, totalQuantity, goodType);
        this.softDrinkType = softDrinkType;
        this.packageType = packageType;
    }

    private SoftDrinkType getSoftDrinkType() {
        return softDrinkType;
    }

    private PackageType getPackageType() { return packageType;  }


    @Override
    public void showProduct() {
        System.out.printf("[%s][%s] [\"%s\"], price: %.2f, quantity: %d, package: %s.\n",
                getGoodType(), getSoftDrinkType(), getName(), getPriceWarehouse(), getTotalQuantity(), getPackageType() );
        System.out.println(); 
    }
}

