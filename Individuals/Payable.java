package Individuals;

import Orders.PayType;

public interface Payable {

    void pay(PayType payType, double amount, PersonalType personalType);
}
