package Test;

import Facade.*;
import Utilities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFacadeTest {

    @Test
    void reservateLodgingAllowsUserToReserveIt() {
        Owner owner = new Owner("Owner 1", "O001");
        User user = new User("User 1", "U001", 500.0);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true, owner);
        ReservationFacade reservationFacade = new ReservationFacade(user);

        boolean reserved = reservationFacade.reservateLodging(lodging, 1);

        assertTrue(reserved);
        assertEquals(400.0, user.getBalance());
    }

    @Test
    void reservateLodgingReturnsFalseWhenLodgingIsNotAvailable() {
        Owner owner = new Owner("Owner 1", "O001");
        User user = new User("User 1", "U001", 500.0);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false, owner);
        ReservationFacade reservationFacade = new ReservationFacade(user);

        boolean reserved = reservationFacade.reservateLodging(lodging, 3);

        assertFalse(reserved);
        assertFalse(lodging.isReserved());
        assertEquals(500.0, user.getBalance());
    }

    @Test
    void reservateLodgingReturnsFalseWhenUserHasInsufficientBalance() {
        Owner owner = new Owner("Owner 1", "O001");
        User user = new User("User 1", "U001", 50.0);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true, owner);
        ReservationFacade reservationFacade = new ReservationFacade(user);

        boolean reserved = reservationFacade.reservateLodging(lodging, 2);

        assertFalse(reserved);
        assertTrue(lodging.isReserved());
        assertEquals(50.0, user.getBalance());
    }

    @Test
    void reservateLodgingDiscountsTotalCostAccordingToNumberOfNights() {
        Owner owner = new Owner("Owner 1", "O001");
        User user = new User("User 1", "U001", 500.0);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true, owner);
        ReservationFacade reservationFacade = new ReservationFacade(user);

        boolean reserved = reservationFacade.reservateLodging(lodging, 2);

        assertTrue(reserved);
        assertEquals(300.0, user.getBalance());
    }

    @Test
    void reservateLodgingCanBeUsedThroughFacadeWithoutAccessingOwnerDirectly() {
        Owner owner = new Owner("Owner 1", "O001");
        User user = new User("User 1", "U001", 500.0);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true, owner);
        ReservationFacade reservationFacade = new ReservationFacade(user);

        boolean reserved = reservationFacade.reservateLodging(lodging, 1);

        assertTrue(reserved);
        assertEquals(400.0, user.getBalance());
        assertEquals(owner, lodging.getOwner());
    }
}