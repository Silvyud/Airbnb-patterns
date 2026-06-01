package Test;

import Adapter.*;
import Utilities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForeignPaymentTest {

    @Test
    void payShouldUsePaymentInterfaceAndAdaptAmountToForeignCurrency() {
        User user = new User("User 1", "U001", 150.0);
        Payment payment = new ForeignPayment(1.2, user);

        double paidAmount = payment.pay(100);

        assertEquals(120, paidAmount);
        assertEquals(30, user.getBalance());
    }

    @Test
    void payShouldReturnZeroWhenUserCannotPay() {
        User user = new User("User 1", "U001", 50.0);
        Payment payment = new ForeignPayment(1.2, user);

        double paidAmount = payment.pay(100);

        assertEquals(0, paidAmount);
        assertEquals(50, user.getBalance());
    }
}