package Test;

import FactoryMethod.Client;
import FactoryMethod.ClientFactory;
import FactoryMethod.Owner;
import FactoryMethod.OwnerFactory;
import FactoryMethod.PremiumClient;
import FactoryMethod.PremiumClientFactory;
import FactoryMethod.User;
import FactoryMethod.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void clientFactoryCreateUser() {
        UserFactory factory = new ClientFactory();

        User user = factory.createUser("clientUser");

        assertInstanceOf(Client.class, user);
        assertEquals("clientUser", user.getNickname());
        assertEquals("password", user.getPassword());
    }

    @Test
    void ownerFactoryCreateUser() {
        UserFactory factory = new OwnerFactory();

        User user = factory.createUser("ownerUser");

        assertInstanceOf(Owner.class, user);
        assertEquals("ownerUser", user.getNickname());
        assertEquals("password", user.getPassword());

        Owner owner = (Owner) user;
        assertNotNull(owner.getLodgings());
        assertTrue(owner.getLodgings().isEmpty());
    }

    @Test
    void premiumClientFactoryCreateUser() {
        UserFactory factory = new PremiumClientFactory(0.5);

        User user = factory.createUser("premiumUser");

        assertInstanceOf(PremiumClient.class, user);
        assertEquals("premiumUser", user.getNickname());
        assertEquals("password", user.getPassword());

        PremiumClient premiumClient = (PremiumClient) user;
        assertEquals(0.5, premiumClient.getDiscount());
    }

    @Test
    void changeFactoryUsingLiskovSubstitutionPrinciple() {
        UserFactory factory = new ClientFactory();

        User client = factory.createUser("clientUser");

        assertInstanceOf(Client.class, client);
        assertEquals("clientUser", client.getNickname());

        factory = new OwnerFactory();

        User owner = factory.createUser("ownerUser");

        assertInstanceOf(Owner.class, owner);
        assertEquals("ownerUser", owner.getNickname());

        factory = new PremiumClientFactory();

        User premiumClient = factory.createUser("premiumUser");

        assertInstanceOf(PremiumClient.class, premiumClient);
        assertEquals("premiumUser", premiumClient.getNickname());

        PremiumClient premium = (PremiumClient) premiumClient;
        assertEquals(0.2, premium.getDiscount());
    }
}