package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Decorator.*;

class DecoratorTest {

    @Test
    void testBasicReservationWithoutDecorators() {
        Reservation reservation = new BasicReservation(100.0);
        
        assertEquals("Normal reservation", reservation.getDescription());
        assertEquals(100.0, reservation.getPrice());
    }

    @Test
    void testAddSingleDecorator() {
        Reservation reservation = new BasicReservation(100.0);
        reservation = new FoodService(reservation);
        
        assertEquals("Normal reservation\nIncludes food service.", reservation.getDescription());
        assertEquals(155.0, reservation.getPrice()); // 100 + 55
    }

    @Test
    void testAddMultipleDecorators() {
        Reservation reservation = new BasicReservation(100.0);
        
        reservation = new FoodService(reservation);
        assertEquals("Normal reservation\nIncludes food service.", reservation.getDescription());
        assertEquals(155.0, reservation.getPrice());
        
        reservation = new CleaningService(reservation);
        assertEquals("Normal reservation\nIncludes food service.\nIncludes a cleaning service.", 
                     reservation.getDescription());
        assertEquals(205.0, reservation.getPrice()); // 155 + 50
        
        reservation = new RecreationActivities(reservation);
        assertEquals("Normal reservation\nIncludes food service.\nIncludes a cleaning service.\nIncludes a recreation activities.", 
                     reservation.getDescription());
        assertEquals(275.0, reservation.getPrice()); // 205 + 70
    }

    @Test
    void testDecoratorOrderMatters() {
        // Orden 1: Food -> Cleaning
        Reservation reservation1 = new BasicReservation(100.0);
        reservation1 = new FoodService(reservation1);
        reservation1 = new CleaningService(reservation1);
        
        // Orden 2: Cleaning -> Food
        Reservation reservation2 = new BasicReservation(100.0);
        reservation2 = new CleaningService(reservation2);
        reservation2 = new FoodService(reservation2);
        
        // El precio es igual (suma conmutativa)
        assertEquals(205.0, reservation1.getPrice());
        assertEquals(205.0, reservation2.getPrice());
        
        // Pero la descripción es diferente (orden de ejecución)
        assertTrue(reservation1.getDescription().contains("food service") && 
                   reservation1.getDescription().contains("cleaning service"));
        assertTrue(reservation2.getDescription().contains("cleaning service") && 
                   reservation2.getDescription().contains("food service"));
        assertNotEquals(reservation1.getDescription(), reservation2.getDescription());
    }

    @Test
    void testAddSameDecoratorMultipleTimes() {
        Reservation reservation = new BasicReservation(100.0);
        
        // Agregar Food Service dos veces
        reservation = new FoodService(reservation);
        reservation = new FoodService(reservation);
        
        // El precio debe reflejar ambas adiciones
        assertEquals(210.0, reservation.getPrice()); // 100 + 55 + 55
        
        // La descripción debe contener ambas adiciones
        String description = reservation.getDescription();
        assertEquals(2, countOccurrences(description, "food service"));
    }

    @Test
    void testDynamicDecoratorAdditionBasedOnConditions() {
        Reservation reservation = new BasicReservation(100.0);
        boolean includeFood = true;
        boolean includeCleaning = false;
        boolean includeRecreation = true;
        
        if (includeFood) {
            reservation = new FoodService(reservation);
        }
        if (includeCleaning) {
            reservation = new CleaningService(reservation);
        }
        if (includeRecreation) {
            reservation = new RecreationActivities(reservation);
        }
        
        assertEquals(225.0, reservation.getPrice()); // 100 + 55 + 70
        assertTrue(reservation.getDescription().contains("food service"));
        assertFalse(reservation.getDescription().contains("cleaning service"));
        assertTrue(reservation.getDescription().contains("recreation activities"));
    }

    private int countOccurrences(String text, String word) {
        return text.split(word, -1).length - 1;
    }
}