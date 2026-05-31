package Flyweight;

import java.util.HashMap;
import java.awt.*;

public class PinFactory {
    public final HashMap<String, FlyweightPin> pins;

    public PinFactory() {
        pins = new HashMap<>();
    }

    public FlyweightPin getPin(String category) {
        if(pins.containsKey(category)) {
            return pins.get(category);
        } else {
            Color c = switch (category) {
                case "cabin" -> Color.BLUE;
                case "hotel" -> Color.RED;
                case "apartment" -> Color.GREEN;
                case "house" -> Color.YELLOW;
                default -> Color.WHITE;
            };
            FlyweightPin pin = new FlyweightPin(c);
            pins.put(category, pin);
            return pin;
        }
    }
}
