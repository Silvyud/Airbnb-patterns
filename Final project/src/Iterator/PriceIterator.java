package Iterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Iterador para recorrer por precio (de menor a mayor)
public class PriceIterator implements AccommodationIterator {

    private List<Accommodation> sortedList;
    private int currentIndex = 0;

    public PriceIterator(List<Accommodation> accommodations) {
        // Hacemos una copia para no alterar la colección original y la ordenamos
        this.sortedList = new ArrayList<>(accommodations);
        this.sortedList.sort(Comparator.comparingDouble(Accommodation::getPrice));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sortedList.size();
    }

    @Override
    public Accommodation next() {
        if (!hasNext()) {
            return null;
        }
        return sortedList.get(currentIndex++);
    }

}