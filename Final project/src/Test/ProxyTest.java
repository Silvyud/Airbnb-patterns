package Test;

import Proxy.Proxy;
import Proxy.Accommodation;
import Proxy.Appartment;
import Proxy.House;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProxyTest {

    @Test
    public void testCheckLocationCommonUserShouldLimitCompleteAccess() {
        // 1. Configurar un apartamento real con dueño "Ana" y huésped "Carlos"
        Accommodation apartamento = new Appartment("Carlos", "Bogotá", "Chapinero", "Calle 60 #9-12", 402, "Torre A");

        // El usuario "Pedro" (un extraño) intenta acceder a través del Proxy
        Proxy proxy = new Proxy(apartamento, "Ana", "Pedro");

        // 2. Ejecutar la acción
        String resultado = proxy.checkLocation();

        // 3. Verificar que se oculte la dirección exacta pero se muestren datos generales
        assertTrue(resultado.contains("Chapinero"));
        assertTrue(resultado.contains("Bogotá"));
        assertFalse(resultado.contains("Calle 60 #9-12")); // No debe mostrar la calle
        assertFalse(resultado.contains("402")); // No debe mostrar el apartamento
    }

    @Test
    public void testCheckLocationHostWithReservationShouldGiveAccess() {
        // 1. Configurar una casa real con dueño "Ana" y huésped "Carlos"
        Accommodation casa = new House("Carlos", "Medellín", "Poblado", "Carrera 43A #11-50", 15);

        // El usuario "Carlos" (el huésped legítimo) intenta acceder
        Proxy proxy = new Proxy(casa, "Ana", "Carlos");

        // 2. Ejecutar la acción
        String resultado = proxy.checkLocation();

        // 3. Verificar que el Proxy delegue correctamente al objeto real sin restricciones
        assertTrue(resultado.contains("Dirección Exacta"));
        assertTrue(resultado.contains("Carrera 43A #11-50"));
        assertTrue(resultado.contains("Casa Número: 15"));
        assertFalse(resultado.contains("Acceso Denegado"));
    }

    @Test
    public void testCheckLocationAccommodationOwnerShouldGiveAccess() {
        // 1. Configurar el alojamiento
        Accommodation apartamento = new Appartment("Carlos", "Bogotá", "Chapinero", "Calle 60 #9-12", 402, "Torre A");

        // La usuaria "Ana" (la dueña) intenta acceder a sus propios datos
        Proxy proxy = new Proxy(apartamento, "Ana", "Ana");

        // 2. Ejecutar la acción
        String resultado = proxy.checkLocation();

        // 3. Verificar acceso completo
        assertTrue(resultado.contains("Dirección Exacta"));
        assertTrue(resultado.contains("Calle 60 #9-12"));
        assertTrue(resultado.contains("Apto: 402"));
    }
}