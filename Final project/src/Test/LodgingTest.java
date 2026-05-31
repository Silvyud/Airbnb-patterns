package Test;

import Flyweight.FlyweightPin;
import Flyweight.Lodging;
import Flyweight.PinFactory;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LodgingTest {

    @Test
    void testLodging() {
        Lodging lodging = new Lodging("hotel", 100, 100);

        assertFalse(lodging.isReserved());
    }

    @Test
    void testSetReserved() {
        Lodging lodging = new Lodging("hotel", 100, 100);

        lodging.setReserved(true);

        assertTrue(lodging.isReserved());
    }

    @Test
    void testFlyweightFactoryReturnsSamePinForSameCategory() {
        PinFactory pinFactory = new PinFactory();

        FlyweightPin firstHotelPin = pinFactory.getPin("hotel");
        FlyweightPin secondHotelPin = pinFactory.getPin("hotel");

        assertSame(firstHotelPin, secondHotelPin);
    }

    @Test
    void testFlyweightFactoryReturnsDifferentPinsForDifferentCategories() {
        PinFactory pinFactory = new PinFactory();

        FlyweightPin hotelPin = pinFactory.getPin("hotel");
        FlyweightPin cabinPin = pinFactory.getPin("cabin");

        assertNotSame(hotelPin, cabinPin);
    }

    @Test
    void testPinColorByCategory() {
        PinFactory pinFactory = new PinFactory();

        assertEquals(Color.RED, pinFactory.getPin("hotel").getColor());
        assertEquals(Color.BLUE, pinFactory.getPin("cabin").getColor());
        assertEquals(Color.GREEN, pinFactory.getPin("apartment").getColor());
        assertEquals(Color.YELLOW, pinFactory.getPin("house").getColor());
        assertEquals(Color.WHITE, pinFactory.getPin("unknown").getColor());
    }

    @Test
    void testShowPinInGraphic() {
        FlyweightPin pin = new FlyweightPin(Color.RED);
        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        pin.draw(graphics, 15, 15);

        Color paintedColor = new Color(image.getRGB(15, 15), true);

        assertEquals(Color.RED, paintedColor);
    }

    @Test
    void testLodgingDrawsPinWhenNotReserved() {
        Lodging lodging = new Lodging("hotel", 15, 15);
        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        lodging.draw(graphics);

        Color paintedColor = new Color(image.getRGB(15, 15), true);

        assertEquals(Color.RED, paintedColor);
    }

    @Test
    void testLodgingDoesNotDrawPinWhenReserved() {
        Lodging lodging = new Lodging("hotel", 15, 15);
        lodging.setReserved(true);

        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        lodging.draw(graphics);

        Color paintedColor = new Color(image.getRGB(15, 15), true);

        assertEquals(new Color(0, 0, 0, 0), paintedColor);
    }

    @Test
    void testLodgingShowPinDrawsEvenWhenReserved() {
        Lodging lodging = new Lodging("hotel", 15, 15);

        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();

        lodging.draw(graphics);

        Color paintedColor = new Color(image.getRGB(15, 15), true);

        assertEquals(Color.RED, paintedColor);
    }
}