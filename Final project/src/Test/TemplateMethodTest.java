package Test;

import TemplateMethod.CancellationContext;
import TemplateMethod.CancellationEngine;
import TemplateMethod.ForceMajorCancellation;
import TemplateMethod.StrictCancellation;
import Utilities.Lodging;
import Utilities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemplateMethodTest {

    @Test
    void cancelateWithStrictCancellationMoreThanSevenDaysBeforeCheckIn() {
        Lodging lodging = new Lodging("1", "Bogotá", 2, 1, 100.0, true);
        User client = new User("Client", "1", 1000.0);
        User owner = new User("Owner", "2", 1000.0);

        CancellationContext context = new CancellationContext(
                lodging,
                client,
                owner,
                10,
                200.0,
                false
        );

        CancellationEngine cancellationEngine = new StrictCancellation(0.5, 0.3, 0.1);

        cancellationEngine.cancelate(context);

        assertEquals(900.0, client.getBalance());
        assertEquals(1000.0, owner.getBalance());
        assertFalse(lodging.isReserved());
        assertEquals(20.0, cancellationEngine.getPlatformFee());
    }

    @Test
    void cancelateWithStrictCancellationSevenDaysOrLessBeforeCheckIn() {
        Lodging lodging = new Lodging("1", "Bogotá", 2, 1, 100.0, true);
        User client = new User("Client", "1", 1000.0);
        User owner = new User("Owner", "2", 1000.0);

        CancellationContext context = new CancellationContext(
                lodging,
                client,
                owner,
                7,
                200.0,
                false
        );

        CancellationEngine cancellationEngine = new StrictCancellation(0.5, 0.3, 0.1);

        cancellationEngine.cancelate(context);

        assertEquals(1000.0, client.getBalance());
        assertEquals(940.0, owner.getBalance());
        assertFalse(lodging.isReserved());
        assertEquals(20.0, cancellationEngine.getPlatformFee());
    }

    @Test
    void cancelateWithForceMajorCancellationWithoutNaturalDisaster() {
        Lodging lodging = new Lodging("1", "Medellín", 3, 2, 150.0, true);
        User client = new User("Client", "1", 1000.0);
        User owner = new User("Owner", "2", 1000.0);

        CancellationContext context = new CancellationContext(
                lodging,
                client,
                owner,
                5,
                300.0,
                false
        );

        CancellationEngine cancellationEngine = new ForceMajorCancellation(0.0, 0.0, 0.0);

        cancellationEngine.cancelate(context);

        assertEquals(700.0, client.getBalance());
        assertEquals(700.0, owner.getBalance());
        assertFalse(lodging.isReserved());
        assertEquals(0.0, cancellationEngine.getPlatformFee());
    }

    @Test
    void cancelateWithForceMajorCancellationWithNaturalDisasterDoesNotFreeLodging() {
        Lodging lodging = new Lodging("1", "Medellín", 3, 2, 150.0, true);
        User client = new User("Client", "1", 1000.0);
        User owner = new User("Owner", "2", 1000.0);

        CancellationContext context = new CancellationContext(
                lodging,
                client,
                owner,
                5,
                300.0,
                true
        );

        CancellationEngine cancellationEngine = new ForceMajorCancellation(0.0, 0.0, 0.0);

        cancellationEngine.cancelate(context);

        assertEquals(700.0, client.getBalance());
        assertEquals(700.0, owner.getBalance());
        assertTrue(lodging.isReserved());
        assertEquals(0.0, cancellationEngine.getPlatformFee());
    }

    @Test
    void getPlatformFee() {
        Lodging lodging = new Lodging("1", "Cali", 1, 1, 80.0, true);
        User client = new User("Client", "1", 1000.0);
        User owner = new User("Owner", "2", 1000.0);

        CancellationContext context = new CancellationContext(
                lodging,
                client,
                owner,
                10,
                500.0,
                false
        );

        CancellationEngine cancellationEngine = new StrictCancellation(0.5, 0.3, 0.2);

        cancellationEngine.cancelate(context);

        assertEquals(100.0, cancellationEngine.getPlatformFee());
    }
}