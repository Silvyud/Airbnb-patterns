package Test;

import Command.*;
import Utilities.Lodging;
import Utilities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void seeLodgingsCommandExecute() {
        Lodging lodging = new Lodging("101", "Medellín", 2, 1, 150.0, true);
        Lodging lodging2 = new Lodging("102", "Bogotá", 3, 2, 200.0, false);

        ArrayList<Lodging> lodgingsList = new ArrayList<>();
        lodgingsList.add(lodging);
        lodgingsList.add(lodging2);

        Command seeLodgingsCommand = new SeeLodgingsCommand(lodgingsList);

        String result = seeLodgingsCommand.execute();

        assertEquals(
                lodging.toString() + "\n" + lodging2.toString() + "\n",
                result
        );
    }

    @Test
    void seeNextLodgingCommandExecute() {
        User user = new User("Julia", "1", 1000);
        Lodging lodging = new Lodging("101", "Medellín", 2, 1, 150.0, true);

        HashMap<String, String> reservations = new HashMap<>();
        reservations.put(user.getId(), lodging.getId());

        HashMap<String, Lodging> lodgings = new HashMap<>();
        lodgings.put(lodging.getId(), lodging);

        DataBase dataBase = new DataBase(reservations, lodgings);

        Command seeNextLodgingCommand = new SeeNextLodgingCommand(user, dataBase);

        String result = seeNextLodgingCommand.execute();

        assertEquals(lodging.toString(), result);
    }

    @Test
    void cancelateNextReservationCommandExecute() {
        User user = new User("Julia", "1", 1000);
        Lodging lodging = new Lodging("101", "Medellín", 2, 1, 150.0, true);

        HashMap<String, String> reservations = new HashMap<>();
        reservations.put(user.getId(), lodging.getId());

        HashMap<String, Lodging> lodgings = new HashMap<>();
        lodgings.put(lodging.getId(), lodging);

        DataBase dataBase = new DataBase(reservations, lodgings);

        Command cancelateNextReservationCommand = new CancelateNextReservationCommand(user, dataBase);

        String result = cancelateNextReservationCommand.execute();

        assertEquals("Reserva cancelada", result);
        assertFalse(lodging.isReserved());
    }

    @Test
    void executeCommandsFromArray() {
        User user = new User("Julia", "1", 1000);

        Lodging lodging = new Lodging("101", "Medellín", 2, 1, 150.0, true);
        Lodging lodging2 = new Lodging("102", "Bogotá", 3, 2, 200.0, false);

        ArrayList<Lodging> lodgingsList = new ArrayList<>();
        lodgingsList.add(lodging);
        lodgingsList.add(lodging2);

        HashMap<String, String> reservations = new HashMap<>();
        reservations.put(user.getId(), lodging.getId());

        HashMap<String, Lodging> lodgings = new HashMap<>();
        lodgings.put(lodging.getId(), lodging);
        lodgings.put(lodging2.getId(), lodging2);

        DataBase dataBase = new DataBase(reservations, lodgings);

        Command seeLodgingsCommand = new SeeLodgingsCommand(lodgingsList);
        Command seeNextLodgingCommand = new SeeNextLodgingCommand(user, dataBase);
        Command cancelateNextReservationCommand = new CancelateNextReservationCommand(user, dataBase);

        class Invoker {
            private final List<Command> commands = new ArrayList<>();

            void addCommand(Command command) {
                commands.add(command);
            }

            List<String> executeCommands() {
                List<String> results = new ArrayList<>();

                for (Command command : commands) {
                    results.add(command.execute());
                }

                return results;
            }
        }

        Invoker invoker = new Invoker();
        invoker.addCommand(seeLodgingsCommand);
        invoker.addCommand(seeNextLodgingCommand);
        invoker.addCommand(cancelateNextReservationCommand);

        List<String> results = invoker.executeCommands();

        assertEquals(
                lodging.toString() + "\n" + lodging2.toString() + "\n",
                results.get(0)
        );

        assertEquals(lodging.toString(), results.get(1));
        assertEquals("Reserva cancelada", results.get(2));
        assertFalse(lodging.isReserved());
    }
}