package Visitor;

import java.util.Locale;

public class JsonExportVisitor implements Visitor {

    @Override
    public String visitLodging(Lodging lodging) {
        // Locale.US obliga a usar el punto (.) para los decimales independientemente de la PC
        return String.format(Locale.US, "{\"type\":\"Lodging\", \"title\":\"%s\", \"price\":%.2f, \"maxCapacity\":%.1f}",
                lodging.getTitle(), lodging.getPrice(), lodging.getMaxCapacity());
    }

    @Override
    public String visitClient(Client client) {
        return String.format("{\"type\":\"Client\", \"name\":\"%s\", \"email\":\"%s\"}",
                client.getName(), client.getEmail());
    }

    @Override
    public String visitOwner(Owner owner) {
        return String.format("{\"type\":\"Owner\", \"name\":\"%s\", \"verified\":%b}",
                owner.getName(), owner.isVerified());
    }

}