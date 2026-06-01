package Test.Iterator;

import Iterator.Accommodation;
import Iterator.AccommodationIterator;
import Iterator.AirbnbCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class IteratorTest {

    private AirbnbCatalog catalog;

    @BeforeEach
    public void setUp() {
        catalog = new AirbnbCatalog();
        catalog.addAccommodation(new Accommodation("Cabaña Bosque", 120.0, Arrays.asList("Montaña", "Mascotas")));
        catalog.addAccommodation(new Accommodation("Penthouse Centro", 300.0, Arrays.asList("Lujo", "Wifi")));
        catalog.addAccommodation(new Accommodation("Villa Playa", 250.0, Arrays.asList("Playa", "Piscina", "Wifi")));
        catalog.addAccommodation(new Accommodation("Hostal Económico", 30.0, Arrays.asList("Ciudad", "Wifi")));
        catalog.addAccommodation(new Accommodation("Casa de Campo", 80.0, Arrays.asList("Mascotas", "Piscina")));
    }

    @Test
    public void testRecorridoPorPrecioAscendente() {
        // Solicitamos el iterador de precio
        AccommodationIterator priceIterator = catalog.createPriceIterator();

        // El primer elemento debe ser el más barato (30.0)
        assertTrue(priceIterator.hasNext());
        assertEquals("Hostal Económico", priceIterator.next().getName());

        // El siguiente debe ser 80.0
        assertTrue(priceIterator.hasNext());
        assertEquals("Casa de Campo", priceIterator.next().getName());

        // El último (después de recorrer los intermedios) debe ser el Penthouse (300.0)
        priceIterator.next(); // Salta 120.0
        priceIterator.next(); // Salta 250.0

        assertTrue(priceIterator.hasNext());
        assertEquals("Penthouse Centro", priceIterator.next().getName());

        // Ya no deben quedar elementos
        assertFalse(priceIterator.hasNext());
    }

    @Test
    public void testRecorridoPorEtiquetaPiscina() {
        // Solicitamos el iterador filtrando por "Piscina"
        AccommodationIterator tagIterator = catalog.createTagIterator("Piscina");

        // El iterador debe saltarse la Cabaña y el Penthouse, entregando directo la Villa Playa
        assertTrue(tagIterator.hasNext());
        assertEquals("Villa Playa", tagIterator.next().getName());

        // El siguiente con piscina es la Casa de Campo
        assertTrue(tagIterator.hasNext());
        assertEquals("Casa de Campo", tagIterator.next().getName());

        // Ya no deben haber más alojamientos con Piscina
        assertFalse(tagIterator.hasNext());
    }

}