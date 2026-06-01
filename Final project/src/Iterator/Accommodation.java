package Iterator;

import java.util.List;

public class Accommodation {
    
    private String name;
    private double price;
    private List<String> tags;

    public Accommodation(String name, double price, List<String> tags) {
        this.name = name;
        this.price = price;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

}
