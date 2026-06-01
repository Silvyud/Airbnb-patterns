package Strategy;

import java.util.HashMap;
import java.util.Map;

// Formulario robusto de entrada
public class PublicationForm {

    private String title;
    private double basePrice;
    private Map<String, String> metadata = new HashMap<>(); // Datos específicos de cada tipo

    public PublicationForm(String title, double basePrice) {
        this.title = title;
        this.basePrice = basePrice;
    }

    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }

    public String getMetadata(String key) {
        return this.metadata.get(key);
    }

    public String getTitle() { return title; }
    public double getBasePrice() { return basePrice; }

}