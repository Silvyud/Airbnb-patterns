package Visitor;

public interface Visitor {
    String visitLodging(Lodging lodging);
    String visitClient(Client client);
    String visitOwner(Owner owner);
}