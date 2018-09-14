package Orders;

import Goods.GoodType;


public interface Orderable {

    void order(GoodType goodType, int quantity, double orderValue, String productName);

}
