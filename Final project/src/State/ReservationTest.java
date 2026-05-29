package State;

import State.*;

public class ReservationTest {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("         AIRBNB - RESERVATION MANAGEMENT        ");
        System.out.println("═══════════════════════════════════════════════\n");

        Reservation reservation = new Reservation("Silvana Martínez",
                                                   "Cabaña en Villa de Leyva",
                                                   850.0);

        System.out.println(reservation.print());

        // ── FLUJO EJEMPLO ──────────────────────────────────────────────────
        System.out.println("\n--- EXAMPLE PATH ---");

        System.out.println("\n[1] Confirming reservation...");
        System.out.println(reservation.confirm());
        System.out.println("Current state: " + reservation.getStateName());

        System.out.println("\n[2] Making payment...");
        System.out.println(reservation.pay());
        System.out.println("Current state: " + reservation.getStateName());

        System.out.println("\n[3] Guest checks in...");
        System.out.println(reservation.checkIn());
        System.out.println("Current state: " + reservation.getStateName());
        System.out.println("Check-in date: " + reservation.getCheckIn());

        System.out.println("\n[4] Guest checks out...");
        System.out.println(reservation.checkOut());
        System.out.println("Current state: " + reservation.getStateName());
        System.out.println("Check-out date: " + reservation.getCheckOut());

        System.out.println("\n[5] Attempting actions on a completed reservation...");
        System.out.println(reservation.confirm());
        System.out.println(reservation.pay());
        System.out.println(reservation.cancel());

        // ── FLUJO CON CANCELACIÓN EN CADA ESTADO ────────────────────────
        System.out.println("\n\n--- CANCELLATION SCENARIOS ---");

        System.out.println("\n[Scenario 1] Cancel while PENDING (no penalty)");
        Reservation r1 = new Reservation("Juan Castillo", "Glamping en Guatavita", 500.0);
        System.out.println("State: " + r1.getStateName());
        System.out.println(r1.cancel());
        System.out.println("State: " + r1.getStateName());
        System.out.println(r1.cancel()); // intento sobre cancelada

        System.out.println("\n[Scenario 2] Cancel while CONFIRMED (30% penalty)");
        Reservation r2 = new Reservation("Grecia Castillo", "Villa en Tunja", 1000.0);
        System.out.println(r2.confirm());
        System.out.println("State: " + r2.getStateName());
        System.out.println(r2.cancel());
        System.out.println("State: " + r2.getStateName());

        System.out.println("\n[Scenario 3] Cancel while PAYED (50% penalty)");
        Reservation r3 = new Reservation("Pedro Mora", "Finca en Antioquia", 900.0);
        System.out.println(r3.confirm());
        System.out.println(r3.pay());
        System.out.println("State: " + r3.getStateName());
        System.out.println(r3.cancel());
        System.out.println("State: " + r3.getStateName());

        System.out.println("\n[Scenario 4] Cancel while IN PROGRESS (70% penalty)");
        Reservation r4 = new Reservation("Laura Díaz", "Loft en Medellín", 600.0);
        System.out.println(r4.confirm());
        System.out.println(r4.pay());
        System.out.println(r4.checkIn());
        System.out.println("State: " + r4.getStateName());
        System.out.println(r4.cancel());
        System.out.println("State: " + r4.getStateName());

        // ── FLUJO CON REEMBOLSOS ─────────────────────────────────────────
        System.out.println("\n\n--- REFUND SCENARIOS ---");

        System.out.println("\n[Scenario 1] Refund while CONFIRMED (full refund)");
        Reservation r5 = new Reservation("Sofía Torres", "Casa en Bogotá", 750.0);
        System.out.println(r5.confirm());
        System.out.println(r5.requestRefund());

        System.out.println("\n[Scenario 2] Refund while PAYED (50% refund)");
        Reservation r6 = new Reservation("Miguel Reyes", "Penthouse en Cali", 2000.0);
        System.out.println(r6.confirm());
        System.out.println(r6.pay());
        System.out.println(r6.requestRefund());

        System.out.println("\n[Scenario 3] Refund after COMPLETED (Airbnb policy)");
        Reservation r7 = new Reservation("Valentina Cruz", "Cabaña en Guatapé", 1100.0);
        System.out.println(r7.confirm());
        System.out.println(r7.pay());
        System.out.println(r7.checkIn());
        System.out.println(r7.checkOut());
        System.out.println(r7.requestRefund());

        System.out.println("\n[Scenario 4] Refund while PENDING (not allowed)");
        Reservation r8 = new Reservation("Julián Gómez", "Hostal en Cartagena", 300.0);
        System.out.println(r8.requestRefund());

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("                  TEST COMPLETE                 ");
        System.out.println("═══════════════════════════════════════════════");
    }
}
