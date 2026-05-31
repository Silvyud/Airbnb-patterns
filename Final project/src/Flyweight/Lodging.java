package Flyweight;

import java.awt.*;

public class Lodging {
    private final FlyweightPin pin;
    private boolean reserved;
    private int x;
    private int y;
    private static final PinFactory pinFactory = new PinFactory();

    public Lodging(String category, int x, int y) {
        this.x = x;
        this.y = y;
        pin = pinFactory.getPin(category);
        reserved = false;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void draw(Graphics g) {
        if (!reserved) {
            pin.draw(g, x, y);
        }
    }

    public boolean isReserved() {
        return reserved;
    }
}
