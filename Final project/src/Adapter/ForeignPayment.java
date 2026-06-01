package Adapter;

import Utilities.User;

public class ForeignPayment implements Payment {
    private double changeRate;
    private User user;

    public ForeignPayment(double changeRate, User user) {
        this.changeRate = changeRate;
        this.user = user;
    }

    @Override
    public double pay(double amount) {
        double amountInForeignCurrency = amount * changeRate;
        if (user.pay(amountInForeignCurrency)) {
            return amountInForeignCurrency;
        }
        return 0; // Payment failed
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
