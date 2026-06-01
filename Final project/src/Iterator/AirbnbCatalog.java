package Iterator;

import java.util.ArrayList;
import java.util.List;

public class AirbnbCatalog implements AccommodationCollection {

    private List<Accommodation> internalList;

    public AirbnbCatalog() {
        this.internalList = new ArrayList<>();
    }

    public void addAccommodation(Accommodation acc) {
        this.internalList.add(acc);
    }

    @Override
    public AccommodationIterator createPriceIterator() {
        return new PriceIterator(internalList);
    }

    @Override
    public AccommodationIterator createTagIterator(String targetTag) {
        return new TagIterator(internalList, targetTag);
    }

}