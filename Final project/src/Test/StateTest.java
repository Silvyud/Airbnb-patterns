package Test;

import static org.junit.jupiter.api.Assertions.*;
import State.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StateTest {

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation("John Doe", "Beach House", 1000.0);
    }

    // ═══════════════════════════════════════════════════════════
    // STATE TRANSITIONS - Normal flow
    // ═══════════════════════════════════════════════════════════

    @Test
    void testInitialStateIsPending() {
        assertEquals("Pending", reservation.getStateName());
    }

    @Test
    void testTransitionPendingToConfirmed() {
        String result = reservation.confirm();
        assertEquals("Confirmed", reservation.getStateName());
        assertTrue(result.contains("confirmada"));
    }

    @Test
    void testTransitionConfirmedToPayed() {
        reservation.confirm();
        String result = reservation.pay();
        assertEquals("Payed", reservation.getStateName());
        assertTrue(result.contains("exitosamente"));
    }

    @Test
    void testTransitionPayedToInProgress() {
        reservation.confirm();
        reservation.pay();
        String result = reservation.checkIn();
        assertEquals("InProgress", reservation.getStateName());
        assertTrue(result.contains("Bienvenido"));
        assertNotNull(reservation.getCheckIn());
    }

    @Test
    void testTransitionInProgressToCompleted() {
        reservation.confirm();
        reservation.pay();
        reservation.checkIn();
        String result = reservation.checkOut();
        assertEquals("Completed", reservation.getStateName());
        assertTrue(result.contains("Check-out"));
        assertNotNull(reservation.getCheckOut());
    }

    // ═══════════════════════════════════════════════════════════
    // INVALID ACTIONS - Action not allowed in current state
    // ═══════════════════════════════════════════════════════════

    @Test
    void testCannotPayWhilePending() {
        String result = reservation.pay();
        assertEquals("Pending", reservation.getStateName());
        assertTrue(result.contains("no permitida"));
    }

    @Test
    void testCannotCheckInWhilePending() {
        String result = reservation.checkIn();
        assertEquals("Pending", reservation.getStateName());
        assertTrue(result.contains("no permitida"));
    }

    @Test
    void testCannotCheckInWhileConfirmed() {
        reservation.confirm();
        String result = reservation.checkIn();
        assertEquals("Confirmed", reservation.getStateName());
        assertTrue(result.contains("no permitida"));
    }

    @Test
    void testCannotConfirmWhenAlreadyCompleted() {
        reservation.confirm();
        reservation.pay();
        reservation.checkIn();
        reservation.checkOut();
        String result = reservation.confirm();
        assertEquals("Completed", reservation.getStateName());
        assertTrue(result.contains("no permitida"));
    }

    // ═══════════════════════════════════════════════════════════
    // CANCELLATION - Different penalties per state
    // ═══════════════════════════════════════════════════════════

    @Test
    void testCancelWhilePendingNoPenalty() {
        String result = reservation.cancel();
        assertEquals("Canceled", reservation.getStateName());
        assertTrue(result.contains("Sin penalización"));
    }

    @Test
    void testCancelWhileConfirmed30PercentPenalty() {
        reservation.confirm();
        String result = reservation.cancel();
        assertEquals("Canceled", reservation.getStateName());
        assertTrue(result.contains("30%"));
        assertTrue(result.contains("300")); // 30% of 1000
    }

    @Test
    void testCancelWhilePayed50PercentPenalty() {
        reservation.confirm();
        reservation.pay();
        String result = reservation.cancel();
        assertEquals("Canceled", reservation.getStateName());
        assertTrue(result.contains("50%"));
        assertTrue(result.contains("500")); // 50% of 1000
    }

    @Test
    void testCancelWhileInProgress70PercentPenalty() {
        reservation.confirm();
        reservation.pay();
        reservation.checkIn();
        String result = reservation.cancel();
        assertEquals("Canceled", reservation.getStateName());
        assertTrue(result.contains("70%"));
        assertTrue(result.contains("700")); // 70% of 1000
    }

    // ═══════════════════════════════════════════════════════════
    // REFUND POLICY - Different refund amounts per state
    // ═══════════════════════════════════════════════════════════

    @Test
    void testRefundWhileConfirmedFullRefund() {
        reservation.confirm();
        String result = reservation.requestRefund();
        assertTrue(result.contains("Reembolso total"));
        assertTrue(result.contains("1000")); // Full refund
    }

    @Test
    void testRefundWhilePayedPartialRefund() {
        reservation.confirm();
        reservation.pay();
        String result = reservation.requestRefund();
        assertTrue(result.contains("Reembolso parcial"));
        assertTrue(result.contains("500")); // 50% refund
    }

    @Test
    void testRefundAfterCompletedAirbnbPolicy() {
        reservation.confirm();
        reservation.pay();
        reservation.checkIn();
        reservation.checkOut();
        String result = reservation.requestRefund();
        assertTrue(result.contains("política de Airbnb"));
        assertTrue(result.contains("Beach House"));
    }

    @Test
    void testRefundWhilePendingNotAllowed() {
        String result = reservation.requestRefund();
        assertTrue(result.contains("no permitida"));
    }

    // ═══════════════════════════════════════════════════════════
    // STATE CONTEXT - Verify data persistence across states
    // ═══════════════════════════════════════════════════════════

    @Test
    void testReservationDataPersistsAcrossStates() {
        assertEquals("John Doe", reservation.getGuestName());
        assertEquals("Beach House", reservation.getPropertyName());
        assertEquals(1000.0, reservation.getTotalPrice());

        reservation.confirm();
        assertEquals("John Doe", reservation.getGuestName());
        assertEquals(1000.0, reservation.getTotalPrice());

        reservation.pay();
        assertEquals("John Doe", reservation.getGuestName());
        assertEquals(1000.0, reservation.getTotalPrice());
    }
}