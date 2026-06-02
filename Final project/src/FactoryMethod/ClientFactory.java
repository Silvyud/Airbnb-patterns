package FactoryMethod;

public class ClientFactory extends UserFactory{
    @Override
    public User createUser(String nickname) {
        return new Client(nickname);
    }
}
