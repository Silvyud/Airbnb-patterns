import Interpreter.*;
import Proxy.*;
import Visitor.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperCommandTest {

    @Test
    void visitorCommandExecuteShouldExportElementAsJson() {
        Visitor visitor = new JsonExportVisitor();
        Element owner = new Owner("Alejandro Silva", true);

        SuperCommand visitorCommand = new VisitorCommand(visitor, owner);

        String result = visitorCommand.execute();

        assertEquals("{\"type\":\"Owner\", \"name\":\"Alejandro Silva\", \"verified\":true}", result);
    }

    @Test
    void interpreterCommandExecuteShouldReturnMatchesMessageWhenLodgingSatisfiesFilter() {
        Utilities.Lodging lodging = new Utilities.Lodging(
                "L1",
                "Cartagena",
                3,
                2,
                120.0,
                false
        );

        FilterExpression cartagenaFilter = new LocationFilter("Cartagena");
        FilterExpression atLeastThreeRooms = new RoomFilter(3, new GreaterEqualEval());
        FilterExpression maxCost150 = new CostFilter(150.0, new LessEqualEval());
        FilterExpression atLeastTwoBathrooms = new BathroomFilter(2, new GreaterEqualEval());

        FilterExpression comfortableCartagenaLodging = new AndOperator(
                new AndOperator(cartagenaFilter, atLeastThreeRooms),
                new AndOperator(maxCost150, atLeastTwoBathrooms)
        );

        SuperCommand interpreterCommand = new InterpreterCommand(lodging, comfortableCartagenaLodging);

        String result = interpreterCommand.execute();

        assertEquals("Lodging matches the filter criteria.", result);
    }

    @Test
    void proxyCommandExecuteShouldReturnCompleteLocationForOwner() {
        Accommodation apartment = new Appartment(
                "Carlos",
                "Bogotá",
                "Chapinero",
                "Calle 60 #9-12",
                402,
                "Torre A"
        );

        Proxy proxy = new Proxy(apartment, "Ana", "Ana");

        SuperCommand proxyCommand = new ProxyCommand(proxy);

        String result = proxyCommand.execute();

        assertTrue(result.contains("Dirección Exacta"));
        assertTrue(result.contains("Calle 60 #9-12"));
        assertTrue(result.contains("Apto: 402"));
    }

    @Test
    void executeCommandsFromArrayAsInvoker() {
        Visitor visitor = new JsonExportVisitor();
        Element client = new Client("Diana Prince", "diana@mail.com");

        SuperCommand visitorCommand = new VisitorCommand(visitor, client);

        Utilities.Lodging lodging = new Utilities.Lodging(
                "L1",
                "Cartagena",
                3,
                2,
                120.0,
                false
        );

        FilterExpression lodgingFilter = new AndOperator(
                new LocationFilter("Cartagena"),
                new CostFilter(150.0, new LessEqualEval())
        );

        SuperCommand interpreterCommand = new InterpreterCommand(lodging, lodgingFilter);

        Accommodation house = new House(
                "Carlos",
                "Medellín",
                "Poblado",
                "Carrera 43A #11-50",
                15
        );

        Proxy proxy = new Proxy(house, "Ana", "Carlos");

        SuperCommand proxyCommand = new ProxyCommand(proxy);

        SuperCommand[] commands = {
                visitorCommand,
                interpreterCommand,
                proxyCommand
        };

        class Invoker {
            private final SuperCommand[] commands;

            Invoker(SuperCommand[] commands) {
                this.commands = commands;
            }

            List<String> executeCommands() {
                List<String> results = new ArrayList<>();

                for (SuperCommand command : commands) {
                    results.add(command.execute());
                }

                return results;
            }
        }

        Invoker invoker = new Invoker(commands);

        List<String> results = invoker.executeCommands();

        assertEquals(3, results.size());

        assertEquals(
                "{\"type\":\"Client\", \"name\":\"Diana Prince\", \"email\":\"diana@mail.com\"}",
                results.get(0)
        );

        assertEquals(
                "Lodging matches the filter criteria.",
                results.get(1)
        );

        assertTrue(results.get(2).contains("Dirección Exacta"));
        assertTrue(results.get(2).contains("Carrera 43A #11-50"));
        assertTrue(results.get(2).contains("Casa Número: 15"));
        assertFalse(results.get(2).contains("Acceso Denegado"));
    }
}