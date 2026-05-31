package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {

    @Test
    void cloneTest() {
        Prototype.Publication pub1 = new Prototype.Publication("Title1", "Description1", 100.0, 101);
        Prototype.Prototype pub2 = pub1.clone();

        assertNotSame(pub1, pub2); // Nos aseguramos de que sean objetos diferentes
        assertEquals("Title1", ((Prototype.Publication)pub2).getTitle());
        assertEquals("Description1", ((Prototype.Publication)pub2).getDescription());
        assertEquals(100.0, ((Prototype.Publication)pub2).getPrice());
        assertEquals(101, ((Prototype.Publication)pub2).getRoomNumber());
    }

    @Test
    void cloneAndChangeTest() {
        Prototype.Publication pub1 = new Prototype.Publication("Title1", "Description1", 100.0, 101);
        Prototype.Prototype pub2 = pub1.clone();

        assertNotSame(pub1, pub2); // Nos aseguramos de que sean objetos diferentes
        assertEquals(101, ((Prototype.Publication)pub2).getRoomNumber());
        // Como se plantea en la situación problema, simulamos el único cambio entre habitaciones del anfitrión
        ((Prototype.Publication)pub2).setRoomNumber(102);
        assertEquals("Title1", ((Prototype.Publication)pub2).getTitle());
        assertEquals("Description1", ((Prototype.Publication)pub2).getDescription());
        assertEquals(100.0, ((Prototype.Publication)pub2).getPrice());
        assertNotEquals(101, ((Prototype.Publication)pub2).getRoomNumber());
    }

}