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

        reservation.print();

        // ── FLUJO EJEMPLO ──────────────────────────────────────────────────
        System.out.println("\n--- EXAMPLE PATH ---");

        System.out.println("\n[1] Confirming reservation...");
        reservation.confirm();
        System.out.println("Current state: " + reservation.getStateName());

        System.out.println("\n[2] Making payment...");
        reservation.pay();
        System.out.println("Current state: " + reservation.getStateName());

        System.out.println("\n[3] Guest checks in...");
        reservation.checkIn();
        System.out.println("Current state: " + reservation.getStateName());
        System.out.println("Check-in date: " + reservation.getCheckIn());

        System.out.println("\n[4] Guest checks out...");
        reservation.checkOut();
        System.out.println("Current state: " + reservation.getStateName());
        System.out.println("Check-out date: " + reservation.getCheckOut());

        System.out.println("\n[5] Attempting actions on a completed reservation...");
        reservation.confirm();
        reservation.pay();
        reservation.cancel();

        // ── FLUJO CON CANCELACIÓN EN CADA ESTADO ────────────────────────
        System.out.println("\n\n--- CANCELLATION SCENARIOS ---");

        System.out.println("\n[Scenario 1] Cancel while PENDING (no penalty)");
        Reservation r1 = new Reservation("Juan Castillo", "Glamping en Guatavita", 500.0);
        System.out.println("State: " + r1.getStateName());
        r1.cancel();
        System.out.println("State: " + r1.getStateName());
        r1.cancel(); // intento sobre cancelada

        System.out.println("\n[Scenario 2] Cancel while CONFIRMED (30% penalty)");
        Reservation r2 = new Reservation("Grecia Castillo", "Villa en Tunja", 1000.0);
        r2.confirm();
        System.out.println("State: " + r2.getStateName());
        r2.cancel();
        System.out.println("State: " + r2.getStateName());

        System.out.println("\n[Scenario 3] Cancel while PAYED (50% penalty)");
        Reservation r3 = new Reservation("Pedro Mora", "Finca en Antioquia", 900.0);
        r3.confirm();
        r3.pay();
        System.out.println("State: " + r3.getStateName());
        r3.cancel();
        System.out.println("State: " + r3.getStateName());

        System.out.println("\n[Scenario 4] Cancel while IN PROGRESS (70% penalty)");
        Reservation r4 = new Reservation("Laura Díaz", "Loft en Medellín", 600.0);
        r4.confirm();
        r4.pay();
        r4.checkIn();
        System.out.println("State: " + r4.getStateName());
        r4.cancel();
        System.out.println("State: " + r4.getStateName());

        // ── FLUJO CON REEMBOLSOS ─────────────────────────────────────────
        System.out.println("\n\n--- REFUND SCENARIOS ---");

        System.out.println("\n[Scenario 1] Refund while CONFIRMED (full refund)");
        Reservation r5 = new Reservation("Sofía Torres", "Casa en Bogotá", 750.0);
        r5.confirm();
        r5.requestRefund();

        System.out.println("\n[Scenario 2] Refund while PAYED (50% refund)");
        Reservation r6 = new Reservation("Miguel Reyes", "Penthouse en Cali", 2000.0);
        r6.confirm();
        r6.pay();
        r6.requestRefund();

        System.out.println("\n[Scenario 3] Refund after COMPLETED (Airbnb policy)");
        Reservation r7 = new Reservation("Valentina Cruz", "Cabaña en Guatapé", 1100.0);
        r7.confirm();
        r7.pay();
        r7.checkIn();
        r7.checkOut();
        r7.requestRefund();

        System.out.println("\n[Scenario 4] Refund while PENDING (not allowed)");
        Reservation r8 = new Reservation("Julián Gómez", "Hostal en Cartagena", 300.0);
        r8.requestRefund();

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("                  TEST COMPLETE                 ");
        System.out.println("═══════════════════════════════════════════════");
    }
}
