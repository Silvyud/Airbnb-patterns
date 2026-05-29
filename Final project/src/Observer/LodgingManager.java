package Observer;
import Utilities.Lodging;

import java.util.ArrayList;
import java.util.HashMap;

public class LodgingManager {
    ArrayList<Lodging> freeLodgings;
    HashMap<String, ArrayList<Subscriber>> subscribers;

    public LodgingManager() {
        freeLodgings = new ArrayList<>();
        subscribers = new HashMap<>();
    }

    public void subscribe(Subscriber subscriber, String Ubication) {
        if (subscribers.containsKey(Ubication)) {
            subscribers.get(Ubication).add(subscriber);
        } else {
            ArrayList<Subscriber> newSubscriberList = new ArrayList<>();
            newSubscriberList.add(subscriber);
            subscribers.put(Ubication, newSubscriberList);
        }
    }

    public void publishLodging(Lodging lodging) {
        freeLodgings.add(lodging);
        String ubication = lodging.getUbication();
        if(subscribers.containsKey(ubication)) {
            for(Subscriber subscriber : subscribers.get(ubication)) {
                subscriber.update(lodging);
            }
        }
    }
}
