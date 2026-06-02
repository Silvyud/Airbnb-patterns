package Test;

import AbstractFactory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest {

    @Test
    public void testCreacionDeComboPremiumCompleto() {
        // 1. El cliente elige el estilo de viaje Premium
        TravelBundleFactory premiumFactory = new PremiumTravelFactory();

        // 2. Se fabrica el paquete completo usando la misma factoría
        Accommodation hospedaje = premiumFactory.createAccommodation();
        Transport transporte = premiumFactory.createTransport();
        Experience actividad = premiumFactory.createExperience();

        // 3. Verificar que todos los productos correspondan estrictamente a la familia Premium
        assertTrue(hospedaje instanceof PremiumVilla);
        assertTrue(transporte instanceof PremiumDriver);
        assertTrue(actividad instanceof YachtTour);

        // Validar coherencia semántica en los mensajes
        assertTrue(hospedaje.getDetails().contains("piscina infinita"));
        assertTrue(transporte.getMoveMethod().contains("chofer privado"));
        assertTrue(actividad.getActivity().contains("yate"));
    }

    @Test
    public void testCreacionDeComboAventureroCompleto() {
        // 1. El cliente cambia su preferencia a un estilo Aventurero
        TravelBundleFactory adventurerFactory = new AdventurerTravelFactory();

        // 2. Se fabrica el combo correspondiente
        Accommodation hospedaje = adventurerFactory.createAccommodation();
        Transport transporte = adventurerFactory.createTransport();
        Experience actividad = adventurerFactory.createExperience();

        // 3. Verificar que los objetos pertenecen a la familia rústica/aventurera
        assertTrue(hospedaje instanceof CabinInForest);
        assertTrue(transporte instanceof Truck4x4);
        assertTrue(actividad instanceof ClimbingTour);

        // Validar contenido del combo
        assertTrue(hospedaje.getDetails().contains("bosque"));
        assertTrue(transporte.getMoveMethod().contains("4x4"));
        assertTrue(actividad.getActivity().contains("escalada"));
    }

}