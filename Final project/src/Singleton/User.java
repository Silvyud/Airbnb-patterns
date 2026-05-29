package Singleton;

import java.util.HashMap;
import Utilities.Lodging;

public class User {

    private final String username;
    private HashMap<String, Lodging> publications;

    public User(String username){
        this.username = username;
        this.publications = new HashMap<String, Lodging>();
    }

    public String getUsername() {
        return username;
    }

    public void publishLodging(Lodging lodging) {
        this.publications.put(lodging.getId(), lodging);
    }

    public Lodging getPublication(String id) {
        return this.publications.get(id);
    }

}
