package Test;

import Strategy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    @Test
    public void testRegistroAlojamiento_ExitoConImpuestos() {
        PublicationContext context = new PublicationContext();
        context.setRegistry(new AccommodationRegistry());

        PublicationForm form = new PublicationForm("Apartamento Minimalista", 100.0);
        form.addMetadata("coordinates", "4.6097,-74.0817");

        PublicationResult result = context.createPublication(form);

        assertTrue(result.isSuccess());
        assertTrue(result.getTrackingId().startsWith("ACC-"));
        // 100.0 + 19% de impuesto = 119.0
        assertEquals(119.0, result.getFinalPriceWithFees());
        assertTrue(result.getLogs().contains("impuestos de hospedaje"));
    }

    @Test
    public void testRegistroAlojamiento_FallaPorFaltaDeCoordenadas() {
        PublicationContext context = new PublicationContext();
        context.setRegistry(new AccommodationRegistry());

        // Se omite intencionalmente añadir las coordenadas en el formulario
        PublicationForm form = new PublicationForm("Cabaña Perdida", 150.0);

        PublicationResult result = context.createPublication(form);

        assertFalse(result.isSuccess());
        assertEquals("N/A", result.getTrackingId());
        assertTrue(result.getLogs().contains("Faltan las coordenadas GPS"));
    }

    @Test
    public void testFiltroGlobal_RechazaPrecioInsuficiente() {
        PublicationContext context = new PublicationContext();
        context.setRegistry(new ExperienceRegistry()); // No importa la estrategia si el filtro global salta primero

        // Precio de $5.0 viola la regla global de mínimo $10.0
        PublicationForm form = new PublicationForm("Tour Gratis por la Ciudad", 5.0);

        PublicationResult result = context.createPublication(form);

        assertFalse(result.isSuccess());
        assertTrue(result.getLogs().contains("RECHAZADO POR POLÍTICA GLOBAL"));
    }

    @Test
    public void testRegistroExperiencia_ExitoConComision() {
        PublicationContext context = new PublicationContext();
        context.setRegistry(new ExperienceRegistry());

        PublicationForm form = new PublicationForm("Paracaidismo Extremo", 200.0);
        form.addMetadata("itinerary", "09:00 Charla, 10:00 Vuelo, 11:00 Salto");
        form.addMetadata("insurance_policy", "SURAMERICANA-9923");

        PublicationResult result = context.createPublication(form);

        assertTrue(result.isSuccess());
        assertTrue(result.getTrackingId().startsWith("EXP-"));
        // 200.0 + 20% de comisión de Airbnb = 240.0
        assertEquals(240.0, result.getFinalPriceWithFees());
    }

}