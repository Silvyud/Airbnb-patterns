import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest {

    @Test
    void publishLodgingAllowsUserToReserveIt() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User user = new User("User 1", "U001", 500.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        owner.publishLodging(lodging);

        boolean reserved = user.reserveLodging("L001");

        assertTrue(reserved);
        assertTrue(lodging.isReserved());
        assertEquals(400.0, user.getBalance());
        assertEquals(80.0, owner.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenLodgingDoesNotExist() {
        Mediator mediator = new Mediator();
        User user = new User("User 1", "U001", 500.0, mediator);

        boolean reserved = user.reserveLodging("UNKNOWN");

        assertFalse(reserved);
        assertEquals(500.0, user.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenLodgingIsAlreadyReserved() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User user = new User("User 1", "U001", 500.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true);

        owner.publishLodging(lodging);

        boolean reserved = user.reserveLodging("L001");

        assertFalse(reserved);
        assertTrue(lodging.isReserved());
        assertEquals(500.0, user.getBalance());
        assertEquals(0.0, owner.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenUserHasInsufficientBalance() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User user = new User("User 1", "U001", 50.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        owner.publishLodging(lodging);

        boolean reserved = user.reserveLodging("L001");

        assertFalse(reserved);
        assertFalse(lodging.isReserved());
        assertEquals(50.0, user.getBalance());
        assertEquals(0.0, owner.getBalance());
    }

    @Test
    void reserveLodgingCanOnlyBeDoneOnce() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User firstUser = new User("User 1", "U001", 500.0, mediator);
        User secondUser = new User("User 2", "U002", 500.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        owner.publishLodging(lodging);

        boolean firstReservation = firstUser.reserveLodging("L001");
        boolean secondReservation = secondUser.reserveLodging("L001");

        assertTrue(firstReservation);
        assertFalse(secondReservation);
        assertTrue(lodging.isReserved());
        assertEquals(400.0, firstUser.getBalance());
        assertEquals(500.0, secondUser.getBalance());
        assertEquals(80.0, owner.getBalance());
    }
}