package Test;

import Observer.LodgingManager;
import Observer.Subscriber;
import Utilities.Lodging;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LodgingManagerTest {

    @Test
    void subscribe() {
        LodgingManager lodgingManager = new LodgingManager();
        TestSubscriber subscriber = new TestSubscriber("Subscriber 1");
        Lodging lodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        lodgingManager.subscribe(subscriber, "Bogota");
        lodgingManager.publishLodging(lodging);

        assertEquals(1, subscriber.getReceivedLodgings().size());
        assertSame(lodging, subscriber.getReceivedLodgings().getFirst());
    }

    @Test
    void publishLodging() {
        LodgingManager lodgingManager = new LodgingManager();
        TestSubscriber bogotaSubscriber = new TestSubscriber("Bogota Subscriber");
        TestSubscriber medellinSubscriber = new TestSubscriber("Medellin Subscriber");
        Lodging bogotaLodging = new Lodging("L001", "Bogota", 2, 1, 100.0, false);

        lodgingManager.subscribe(bogotaSubscriber, "Bogota");
        lodgingManager.subscribe(medellinSubscriber, "Medellin");

        lodgingManager.publishLodging(bogotaLodging);

        assertEquals(1, bogotaSubscriber.getReceivedLodgings().size());
        assertSame(bogotaLodging, bogotaSubscriber.getReceivedLodgings().getFirst());
        assertTrue(medellinSubscriber.getReceivedLodgings().isEmpty());
    }

    private static class TestSubscriber extends Subscriber {
        private final List<Lodging> receivedLodgings;

        public TestSubscriber(String name) {
            super(name);
            this.receivedLodgings = new ArrayList<>();
        }

        @Override
        public void update(Lodging lodging) {
            receivedLodgings.add(lodging);
        }

        public List<Lodging> getReceivedLodgings() {
            return receivedLodgings;
        }
    }
}