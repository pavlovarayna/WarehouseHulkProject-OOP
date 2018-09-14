package Goods;

public class Food extends Good{

    private FoodType foodType;

    private PackageType packageType;      

    public Food(String name, double priceWarehouse, int totalQuantity, GoodType goodType, FoodType foodType, PackageType packageType) {
        super(name, priceWarehouse, totalQuantity, goodType);
        this.foodType = foodType;
        this.packageType = packageType;
    }


    private FoodType getFoodType() {
        return foodType;
    }

    private PackageType getPackageType() { return packageType;  }

    @Override
    public void showProduct() {
        System.out.printf("[%s][%s] [\"%s\"], price: %.2f, quantity: %d, package: %s.\n",
                getGoodType(), getFoodType(), getName(), getPriceWarehouse(), getTotalQuantity(), getPackageType());
        System.out.println();
    }
}
