package Strategy;

public class PublicationContext {

    private RegistryStrategy registry;

    public void setRegistry(RegistryStrategy registry) {
        this.registry = registry;
    }

    public PublicationResult createPublication(PublicationForm form) {
        if (registry == null) {
            return new PublicationResult(false, "ERR-00", 0, "Falta definir el tipo de estrategia de registro.");
        }

        // --- FILTRO GLOBAL ---
        // Ningún artículo en la plataforma puede tener un título vacío o costar menos de $10 USD
        if (form.getTitle() == null || form.getTitle().trim().isEmpty() || form.getBasePrice() < 10.0) {
            return new PublicationResult(false, "ERR-01", 0, "RECHAZADO POR POLÍTICA GLOBAL: Título inválido o precio menor al mínimo.");
        }

        // Delegación del flujo complejo a la estrategia correspondiente
        return registry.register(form);
    }

}
