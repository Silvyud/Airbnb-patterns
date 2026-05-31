package Test;

import Bridge.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    @Test
    public void testReservaExitosaDescuentaSaldo() {
        // 1. Configurar cuenta con buen saldo ($500)
        Account cuentaCarlos = new Account("Carlos", 500.0);
        Payment nequi = new Nequi();

        // 2. Crear reserva
        Reservation reserva = new InstantBooking("Cabaña Bosque", cuentaCarlos, nequi);

        // 3. Ejecutar reserva por $150
        String resultado = reserva.makeReservation(150.0);

        // 4. Verificar que fue exitosa y se descontó el dinero (500 - 150 = 350)
        assertTrue(resultado.contains("¡Confirmada de inmediato!"));
        assertTrue(resultado.contains("Saldo restante: $350.0"));
        assertEquals(350.0, cuentaCarlos.getBalance());
    }

    @Test
    public void testReservaFallaPorFondosInsuficientes() {
        // 1. Configurar cuenta con poco saldo ($50)
        Account cuentaAna = new Account("Ana", 50.0);
        Payment paypal = new PayPal();

        // 2. Crear reserva
        Reservation reserva = new PendingApproval("Penthouse", cuentaAna, paypal);

        // 3. Intentar hacer reserva por $200
        String resultado = reserva.makeReservation(200.0);

        // 4. Verificar que fue rechazada y el saldo se mantuvo intacto
        assertTrue(resultado.contains("Reserva Cancelada") || resultado.contains("Solicitud Rechazada"));
        assertTrue(resultado.contains("ERROR: Fondos insuficientes"));
        assertEquals(50.0, cuentaAna.getBalance()); // No se cobró nada
    }

}