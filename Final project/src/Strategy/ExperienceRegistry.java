package Strategy;

import java.util.UUID;

public class ExperienceRegistry implements RegistryStrategy {

    @Override
    public PublicationResult register(PublicationForm form) {
        // Step 1: Validación múltiple (Itinerario y Seguro)
        String itinerary = form.getMetadata("itinerary");
        String insurance = form.getMetadata("insurance_policy");

        if (itinerary == null || insurance == null) {
            return new PublicationResult(false, "N/A", 0, "RECHAZADO: Las experiencias requieren itinerario y póliza de seguro.");
        }

        // Step 2: Comisión porcentual de Airbnb para experiencias (20%)
        double finalPrice = form.getBasePrice() * 1.20;

        String logs = "EXPERIENCIA aprobada. Póliza registrada: " + insurance + ". Comisión de comunidad del 20% calculada.";
        return new PublicationResult(true, "EXP-" + UUID.randomUUID().toString().substring(0, 8), finalPrice, logs);
    }

}