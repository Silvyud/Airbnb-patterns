package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Singleton.DataBase;
import Singleton.User;
import Utilities.Lodging;

class SingletonTest {

    @Test
    void testGetInstanceReturnsSameObject() {
        DataBase db1 = DataBase.getInstance();
        DataBase db2 = DataBase.getInstance();
        assertSame(db1, db2, "getInstance should return the same DataBase instance");
    }

    @Test
    void testAddUserPersistsAcrossReferences() {
        DataBase db1 = DataBase.getInstance();
        DataBase db2 = DataBase.getInstance();

        User user = new User("alice");
        db1.addUser(user);

        assertSame(user, db2.getUser("alice"), "User added via one reference should be visible from another reference");
    }

    @Test
    void testAddPublicationPersists() {
        DataBase db = DataBase.getInstance();
        User user = new User("bob");
        db.addUser(user);

        Lodging lodging = new Lodging("L1", "Beach", 2, 1, 120.0, false);
        db.addPublication(user, lodging);

        assertSame(lodging, db.getPublication("L1"), "Publication should be stored in the database");
        assertSame(lodging, user.getPublication("L1"), "Publication should also be registered under the user");
    }
}