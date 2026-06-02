package Visitor;

public class Lodging implements Element {

    private String title;
    private double price;
    private double maxCapacity;

    public Lodging(String title, double price, double maxCapacity) {
        this.title = title;
        this.price = price;
        this.maxCapacity = maxCapacity;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public double getMaxCapacity() { return maxCapacity; }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitLodging(this);
    }

}