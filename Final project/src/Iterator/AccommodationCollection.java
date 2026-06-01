package Iterator;

public interface AccommodationCollection {
    AccommodationIterator createPriceIterator();
    AccommodationIterator createTagIterator(String targetTag);
}
