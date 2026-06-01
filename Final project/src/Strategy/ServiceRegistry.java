package Strategy;

import java.util.UUID;

public class ServiceRegistry implements RegistryStrategy {

    @Override
    public PublicationResult register(PublicationForm form) {
        // Step 1: Validación específica
        String license = form.getMetadata("provider_license");
        if (license == null || license.isEmpty()) {
            return new PublicationResult(false, "N/A", 0, "RECHAZADO: Se requiere una licencia de proveedor válida para servicios.");
        }

        // Step 2: Tarifa plana de plataforma fija (+$15 USD por gestión de terceros)
        double finalPrice = form.getBasePrice() + 15.0;

        String logs = "SERVICIO verificado con licencia [" + license + "]. Tarifa fija de intermediación añadida.";
        return new PublicationResult(true, "SRV-" + UUID.randomUUID().toString().substring(0, 8), finalPrice, logs);
    }

}