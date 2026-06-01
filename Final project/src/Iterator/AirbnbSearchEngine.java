package Iterator;

import java.util.ArrayList;
import java.util.List;

public class AirbnbSearchEngine {

    private AccommodationCollection catalog;

    public AirbnbSearchEngine(AccommodationCollection catalog) {
        this.catalog = catalog;
    }

    // Este metodo simula lo que ocurre cuando el usuario da clic en "Buscar"
    // filtrando por una etiqueta específica (ej. "Piscina")
    public List<Accommodation> searchByTag(String tag) {
        List<Accommodation> searchResults = new ArrayList<>();

        // 1. Le pedimos al catálogo el iterador configurado para esa etiqueta
        AccommodationIterator iterator = catalog.createTagIterator(tag);

        // 2. El motor de búsqueda (el cliente) usa el iterador para recolectar los datos
        // sin importarle si están guardados en un Array, un Grafo o un HashMap.
        while (iterator.hasNext()) {
            searchResults.add(iterator.next());
        }

        // 3. Devolvemos la lista final que alimentará la interfaz gráfica (UI)
        return searchResults;
    }

}