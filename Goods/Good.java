package Goods;

public abstract class Good {
    private String name;
    private double priceProducer;
    private double priceWarehouse;
    private GoodType goodType;
    private int totalQuantity;

    Good(String name, double priceWarehouse, int totalQuantity, GoodType goodType) {
        this.name = name;
        this.priceProducer = priceWarehouse/2;
        this.priceWarehouse = priceWarehouse;
        this.goodType = goodType;
        this.totalQuantity = totalQuantity;
    }



    public String getName() {
        return name;
    }

    public double getPriceProducer() {
        return priceProducer;
    }

    public void setPriceProducer(double priceProducer) {
        this.priceProducer = priceProducer;
    }

    public double getPriceWarehouse() {
        return priceWarehouse;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public abstract void showProduct();
}
