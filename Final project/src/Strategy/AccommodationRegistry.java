package Strategy;

import java.util.UUID;

public class AccommodationRegistry implements RegistryStrategy {

    @Override
    public PublicationResult register(PublicationForm form) {
        // Step 1: Validación específica
        String coords = form.getMetadata("coordinates");
        if (coords == null || coords.isEmpty()) {
            return new PublicationResult(false, "N/A", 0, "RECHAZADO: Faltan las coordenadas GPS obligatorias.");
        }

        // Step 2: Cálculo financiero complejo (Impuesto de hospedaje local + IVA = 19%)
        double finalPrice = form.getBasePrice() * 1.19;

        // Step 3: Respuesta exitosa
        String logs = "ALOJAMIENTO aprobado en coordenadas [" + coords + "]. Aplicado 19% de impuestos de hospedaje.";
        return new PublicationResult(true, "ACC-" + UUID.randomUUID().toString().substring(0, 8), finalPrice, logs);
    }

}