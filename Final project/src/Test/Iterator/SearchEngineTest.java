package Test.Iterator;

import Iterator.Accommodation;
import Iterator.AirbnbCatalog;
import Iterator.AirbnbSearchEngine;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {

    @Test
    public void testBusquedaPorEtiquetaDevuelveListaFiltrada() {
        // 1. Configuramos nuestra "Base de datos"
        AirbnbCatalog catalog = new AirbnbCatalog();
        catalog.addAccommodation(new Accommodation("Cabaña Bosque", 120.0, Arrays.asList("Montaña", "Mascotas")));
        catalog.addAccommodation(new Accommodation("Villa Playa", 250.0, Arrays.asList("Playa", "Piscina", "Wifi")));
        catalog.addAccommodation(new Accommodation("Casa de Campo", 80.0, Arrays.asList("Mascotas", "Piscina")));

        // 2. Instanciamos el motor de búsqueda
        AirbnbSearchEngine searchEngine = new AirbnbSearchEngine(catalog);

        // 3. El usuario busca la etiqueta "Piscina"
        List<Accommodation> resultados = searchEngine.searchByTag("Piscina");

        // 4. Evidenciamos que la lista contiene exactamente lo que esperábamos
        assertEquals(2, resultados.size());
        assertEquals("Villa Playa", resultados.get(0).getName());
        assertEquals("Casa de Campo", resultados.get(1).getName());

        System.out.println("--- Resultados de Búsqueda para 'Piscina' ---");
        for (Accommodation acc : resultados) {
            System.out.println("- " + acc.getName() + " ($" + acc.getPrice() + ")");
        }
    }

}
