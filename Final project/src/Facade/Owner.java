package Facade;

public class Owner {
    private String name;
    private String id;
    private double balance;

    public Owner(String name, String id) {
        this.name = name;
        this.id = id;
        balance = 0;
    }

    public void addToBalnce(double amount){
        balance += amount;
    }
}
