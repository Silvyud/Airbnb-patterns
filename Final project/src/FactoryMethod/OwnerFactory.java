package FactoryMethod;

public class OwnerFactory extends UserFactory{
    @Override
    public User createUser(String nickname) {
        return new Owner(nickname);
    }
}
