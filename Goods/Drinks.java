package Goods;

public class Drinks extends Good {



    Drinks(String name, double priceWarehouse, int totalQuantity, GoodType goodType) {
        super(name, priceWarehouse, totalQuantity, goodType);
    }


    @Override
    public void showProduct() {
        System.out.printf("[%s] %s, price: %.2f, total amount of quantity: %d .\n",
                getGoodType(), getName(), getPriceWarehouse(), getTotalQuantity());
    }
}
