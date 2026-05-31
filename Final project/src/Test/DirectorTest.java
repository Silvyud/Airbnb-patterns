package Test;

import Builder.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorTest {

    @Test
    void setBuilder() {
        HouseBuilder houseBuilder = new HouseBuilder(2);
        Director director = new Director(2, 4, 8, houseBuilder);

        Property firstProperty = director.construct();

        ApartmentBuilder apartmentBuilder = new ApartmentBuilder(3);
        director.setBuilder(apartmentBuilder);

        Property secondProperty = director.construct();

        assertInstanceOf(House.class, firstProperty);
        assertInstanceOf(Apartment.class, secondProperty);
        assertEquals(4, secondProperty.getRooms());
        assertEquals(8, secondProperty.getWindows());
        assertTrue(secondProperty.isPool());
    }

    @Test
    void construct() {
        HouseBuilder houseBuilder = new HouseBuilder(2);
        Director director = new Director(2, 5, 10, houseBuilder);

        Property property = director.construct();

        assertInstanceOf(House.class, property);
        assertEquals(5, property.getRooms());
        assertEquals(10, property.getWindows());
        assertTrue(property.isPool());

        House house = (House) property;
        assertEquals(2, house.getLevels());
        assertTrue(property.isPool());
    }
}