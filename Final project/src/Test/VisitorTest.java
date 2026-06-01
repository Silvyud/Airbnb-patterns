package Test;

import Visitor.Element;
import Visitor.JsonExportVisitor;
import Visitor.Lodging;
import Visitor.Visitor;
import Visitor.Client;
import Visitor.Owner;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    @Test
    public void testExportarOwnerConAtributoBoolean() {
        // Instancia usando el nuevo tipo boolean para la verificación
        Element owner = new Owner("Alejandro Silva", true);
        Visitor jsonSaver = new JsonExportVisitor();

        String result = owner.accept(jsonSaver);

        // Validamos que el JSON contenga el valor booleano sin comillas
        assertEquals("{\"type\":\"Owner\", \"name\":\"Alejandro Silva\", \"verified\":true}", result);
    }

    @Test
    public void testExportarListaCompleta() {
        List<Element> components = Arrays.asList(
                new Lodging("Estudio Moderno", 85.50, 2.0),
                new Client("Diana Prince", "diana@mail.com"),
                new Owner("Roberto Gómez", false)
        );

        Visitor jsonSaver = new JsonExportVisitor();
        StringBuilder output = new StringBuilder();

        for (Element element : components) {
            output.append(element.accept(jsonSaver)).append("\n");
        }

        String finalJson = output.toString();

        // Verificaciones estructurales
        assertTrue(finalJson.contains("\"price\":85.50"));
        assertTrue(finalJson.contains("\"maxCapacity\":2.0"));
        assertTrue(finalJson.contains("\"email\":\"diana@mail.com\""));
        assertTrue(finalJson.contains("\"verified\":false"));
    }

}