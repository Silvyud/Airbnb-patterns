package FactoryMethod;

public class PremiumClientFactory extends UserFactory{
    private double discount;

    public PremiumClientFactory(double discount) {
        this.discount = discount;
    }

    public PremiumClientFactory() {
        discount = 0.2;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public User createUser(String nickname) {
        return new PremiumClient(nickname, discount);
    }
}
