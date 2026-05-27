package Test;

import Mediator.*;
import org.junit.jupiter.api.Assertions;
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

        boolean reserved = user.reserveLodging("L001", 1);

        assertTrue(reserved);
        Assertions.assertTrue(lodging.isReserved());
        Assertions.assertEquals(400.0, user.getBalance());
        Assertions.assertEquals(80.0, owner.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenLodgingDoesNotExist() {
        Mediator mediator = new Mediator();
        User user = new User("User 1", "U001", 500.0, mediator);

        boolean reserved = user.reserveLodging("UNKNOWN", 1);

        assertFalse(reserved);
        Assertions.assertEquals(500.0, user.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenLodgingIsAlreadyReserved() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User user = new User("User 1", "U001", 500.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, true);

        owner.publishLodging(lodging);

        boolean reserved = user.reserveLodging("L001", 3);

        assertFalse(reserved);
        Assertions.assertTrue(lodging.isReserved());
        Assertions.assertEquals(500.0, user.getBalance());
        Assertions.assertEquals(0.0, owner.getBalance());
    }

    @Test
    void reserveLodgingReturnsFalseWhenUserHasInsufficientBalance() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User user = new User("User 1", "U001", 50.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        owner.publishLodging(lodging);

        boolean reserved = user.reserveLodging("L001", 2);

        assertFalse(reserved);
        Assertions.assertFalse(lodging.isReserved());
        Assertions.assertEquals(50.0, user.getBalance());
        Assertions.assertEquals(0.0, owner.getBalance());
    }

    @Test
    void reserveLodgingCanOnlyBeDoneOnce() {
        Mediator mediator = new Mediator();
        Owner owner = new Owner("Owner 1", 0.0, mediator);
        User firstUser = new User("User 1", "U001", 500.0, mediator);
        User secondUser = new User("User 2", "U002", 500.0, mediator);
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        owner.publishLodging(lodging);

        boolean firstReservation = firstUser.reserveLodging("L001", 2);
        boolean secondReservation = secondUser.reserveLodging("L001", 3);

        assertTrue(firstReservation);
        assertFalse(secondReservation);
        Assertions.assertTrue(lodging.isReserved());
        Assertions.assertEquals(300.0, firstUser.getBalance());
        Assertions.assertEquals(500.0, secondUser.getBalance());
        Assertions.assertEquals(80.0, owner.getBalance());
    }
}