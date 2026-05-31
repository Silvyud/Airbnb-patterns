package Flyweight;

import java.awt.*;

public class FlyweightPin {
    private final Color color;

    public FlyweightPin(Color color) {
        this.color = color;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public Color getColor() {
        return color;
    }
}
