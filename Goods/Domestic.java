package Goods;

public class Domestic extends Good {

    private DomesticType domesticType;

    public Domestic(String name, double priceWarehouse, int totalQuantity,GoodType goodType, DomesticType domesticType) {
        super(name, priceWarehouse, totalQuantity,goodType);
        this.domesticType = domesticType;
    }


    private DomesticType getDomesticType() {
        return domesticType;
    }



    @Override
    public void showProduct() {
        System.out.printf("[%s][%s] [\"%s\"], price: %.2f, quantity: %d.\n",
                getGoodType(), getDomesticType(), getName(), getPriceWarehouse(), getTotalQuantity());
        System.out.println();
    }
}
