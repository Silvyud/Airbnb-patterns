package Test;

import ChainOfResponsability.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginHandlerTest {

    @Test
    void verifyReturnsLoggedInWhenUserAndPasswordAreCorrect() {
        DataBase dataBase = new DataBase();
        dataBase.addUser(new LoginDTO("user", "password"));

        LoginHandler userHandler = new UserHandler(dataBase);
        LoginHandler passwordHandler = new PasswordHandler(dataBase);
        userHandler.setNext(passwordHandler);

        String result = userHandler.verify(new LoginDTO("user", "password"));

        assertEquals("You are logged in", result);
    }

    @Test
    void verifyReturnsUserNotFoundWhenUserDoesNotExist() {
        DataBase dataBase = new DataBase();
        dataBase.addUser(new LoginDTO("user", "password"));

        LoginHandler userHandler = new UserHandler(dataBase);
        LoginHandler passwordHandler = new PasswordHandler(dataBase);
        userHandler.setNext(passwordHandler);

        String result = userHandler.verify(new LoginDTO("unknown", "password"));

        assertEquals("User not found", result);
    }

    @Test
    void verifyReturnsInvalidPasswordWhenPasswordIsIncorrect() {
        DataBase dataBase = new DataBase();
        dataBase.addUser(new LoginDTO("user", "password"));

        LoginHandler userHandler = new UserHandler(dataBase);
        LoginHandler passwordHandler = new PasswordHandler(dataBase);
        userHandler.setNext(passwordHandler);

        String result = userHandler.verify(new LoginDTO("user", "wrong-password"));

        assertEquals("Invalid password", result);
    }

    @Test
    void setNext() {
        DataBase dataBase = new DataBase();
        dataBase.addUser(new LoginDTO("admin", "1234"));

        LoginHandler userHandler = new UserHandler(dataBase);
        LoginHandler passwordHandler = new PasswordHandler(dataBase);

        userHandler.setNext(passwordHandler);

        String result = userHandler.verify(new LoginDTO("admin", "1234"));

        assertEquals("You are logged in", result);
    }
}