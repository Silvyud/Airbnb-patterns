package FactoryMethod;

public class PremiumClient extends Client{
    private double discount;

    public PremiumClient(String nickname, double discount) {
        super(nickname);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
