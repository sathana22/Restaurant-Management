package com.rest.Payment;

public class CashPayment implements PaymentMethod{
    
    @Override
    public void pay (double amount) {
        System.out.printf("cash payment of Rs. %2f Successful.%n ", amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
    
}
