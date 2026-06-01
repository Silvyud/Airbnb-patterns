package Iterator;

import java.util.List;

// Iterador para recorrer filtrando por una etiqueta (Ej. "Piscina")
public class TagIterator implements AccommodationIterator {

    private List<Accommodation> accommodations;
    private String targetTag;
    private int currentIndex = 0;

    public TagIterator(List<Accommodation> accommodations, String targetTag) {
        this.accommodations = accommodations;
        this.targetTag = targetTag;
    }

    @Override
    public boolean hasNext() {
        // Buscamos hacia adelante si hay algún alojamiento que cumpla la condición
        while (currentIndex < accommodations.size()) {
            if (accommodations.get(currentIndex).hasTag(targetTag)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Accommodation next() {
        if (!hasNext()) {
            return null;
        }
        return accommodations.get(currentIndex++);
    }

}
