package Singleton;

import java.util.HashMap;
import Utilities.Lodging;

public class DataBase {

    private static DataBase instance;
    private HashMap<String, Lodging> publications;
    private HashMap<String, User> users;

    private DataBase() {
        this.publications = new HashMap<>();
        this.users = new HashMap<>();
    }

    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Add a publication and associate it with a user
    public void addPublication(User user, Lodging lodging) {
        publications.put(lodging.getId(), lodging);
        // also register it in the user's own list
        user.publishLodging(lodging);
    }

    public Lodging getPublication(String id) {
        return publications.get(id);
    }

    // Getter used by tests to verify stored users
    public User getUser(String username) {
        return users.get(username);
    }
}
