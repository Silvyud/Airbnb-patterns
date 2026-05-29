package Test;

import Observer.LodgingManager;
import Observer.Subscriber;
import Utilities.Lodging;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LodgingManagerTest {

    @Test
    void subscribe() {
        LodgingManager lodgingManager = new LodgingManager();
        Subscriber subscriber = new Subscriber("Subscriber 1");
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        lodgingManager.subscribe(subscriber, "Bogota");
        lodgingManager.publishLodging(lodging);

        assertEquals(1, subscriber.getReceivedLodgings().size());
        assertSame(lodging, subscriber.getReceivedLodgings().getFirst());
    }

    @Test
    void publishLodging() {
        LodgingManager lodgingManager = new LodgingManager();
        Subscriber bogotaSubscriber = new Subscriber("Bogota Subscriber");
        Subscriber medellinSubscriber = new Subscriber("Medellin Subscriber");
        Lodging bogotaLodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        lodgingManager.subscribe(bogotaSubscriber, "Bogota");
        lodgingManager.subscribe(medellinSubscriber, "Medellin");

        lodgingManager.publishLodging(bogotaLodging);

        assertEquals(1, bogotaSubscriber.getReceivedLodgings().size());
        assertSame(bogotaLodging, bogotaSubscriber.getReceivedLodgings().getFirst());
        assertTrue(medellinSubscriber.getReceivedLodgings().isEmpty());
    }
}