package com.rest.Payment;

public class CardPayment implements PaymentMethod{
    
    @Override
    public void pay (double amount) {
        System.out.printf("card payment of Rs. %2f Successful.%n ", amount);
    }

    @Override
    public String getMethodName() {
        return "Card";
    }
    
}
