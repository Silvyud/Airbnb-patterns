package Test;

import Bridge.InstantBooking;
import Bridge.PendingApproval;
import Bridge.Nequi;
import Bridge.PayPal;
import Bridge.Payment;
import Bridge.Reservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    @Test
    public void testInstantBookingNequi() {
        // 1. Configurar el escenario: Alojamiento, Metodo de pago y Tipo de Reserva
        String alojamiento = "Apartamento Vista al Mar";
        Payment metodoPago = new Nequi();
        Reservation reserva = new InstantBooking(alojamiento, metodoPago);

        // 2. Ejecutar la acción
        String resultado = reserva.makeReservation(120.0);

        // 3. Verificar que se comporte como Reserva Instantánea Y use Nequi
        assertTrue(resultado.contains("Reserva Instantánea"));
        assertTrue(resultado.contains("Apartamento Vista al Mar"));
        assertTrue(resultado.contains("Pago procesado a través de Nequi."));
        assertTrue(resultado.contains("¡Confirmada de inmediato!"));
    }

    @Test
    public void testPendingApprovalWithPayPal() {
        // 1. Configurar un escenario diferente combinando al vuelo
        String alojamiento = "Cabaña en el Bosque";
        Payment metodoPago = new PayPal();
        Reservation reserva = new PendingApproval(alojamiento, metodoPago);

        // 2. Ejecutar la acción
        String resultado = reserva.makeReservation(250.0);

        // 3. Verificar que se comporte como Pendiente Y use PayPal
        assertTrue(resultado.contains("Reserva con Aprobación Pendiente"));
        assertTrue(resultado.contains("Cabaña en el Bosque"));
        assertTrue(resultado.contains("Pago procesado a través de PayPal."));
        assertTrue(resultado.contains("Esperando respuesta del anfitrión"));
    }

    @Test
    public void testPendingApprovalWithNequi() {
        // 1. Configurar escenario: Aprobación pendiente con pago por Nequi
        String alojamiento = "Glamping de Lujo";
        Payment metodoPago = new Nequi();
        Reservation reserva = new PendingApproval(alojamiento, metodoPago);

        // 2. Ejecutar la acción
        String resultado = reserva.makeReservation(180.0);

        // 3. Verificar que combine la lógica de aprobación con la plataforma Nequi
        assertTrue(resultado.contains("Reserva con Aprobación Pendiente"));
        assertTrue(resultado.contains("Glamping de Lujo"));
        assertTrue(resultado.contains("Pago procesado a través de Nequi."));
        assertTrue(resultado.contains("Esperando respuesta del anfitrión"));
        assertFalse(resultado.contains("¡Confirmada de inmediato!")); // Asegurar que no actúe como instantánea
    }

}
