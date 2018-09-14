package Invoice;

import Goods.GoodType;
import Orders.Order;

public interface Invoicable {

    void createInvoice(int number, InvoiceType invoiceType, Order order, GoodType goodType);

}
