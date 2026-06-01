package Test;

import Composite.IndividualRoom;
import Composite.PropertyGroup;
import Composite.RentalUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeTest {

    @Test
    public void testCapacidadHabitacionIndividual() {
        // 1. Crear una habitación simple
        RentalUnit habitacion = new IndividualRoom(101, 2);

        // 2. Verificar que se comporte como hoja
        assertEquals(2, habitacion.getCapacity());
        assertTrue(habitacion.toString().contains("Habitación #101"));
    }

    @Test
    public void testCapacidadGrupoSimple() {
        // 1. Crear un piso con dos habitaciones
        PropertyGroup piso1 = new PropertyGroup();
        piso1.addRentalUnit(new IndividualRoom(101, 2));
        piso1.addRentalUnit(new IndividualRoom(102, 4));

        // 2. Verificar que el contenedor sume las capacidades automáticamente (2 + 4 = 6)
        assertEquals(6, piso1.getCapacity());

        // 3. Verificar la descripción generada
        String descripcion = piso1.toString();
        assertTrue(descripcion.contains("Capacidad total: 6"));
        assertTrue(descripcion.contains("Habitación #101"));
        assertTrue(descripcion.contains("Habitación #102"));
    }

    @Test
    public void testCapacidadComplejoTuristicoAnidado() {
        // 1. Construir la jerarquía (Un Resort que tiene 2 pisos, y cada piso tiene habitaciones)
        PropertyGroup resortEntero = new PropertyGroup();

        PropertyGroup piso1 = new PropertyGroup();
        piso1.addRentalUnit(new IndividualRoom(101, 2));
        piso1.addRentalUnit(new IndividualRoom(102, 2));
        piso1.addRentalUnit(new IndividualRoom(103, 2));

        PropertyGroup piso2 = new PropertyGroup();
        piso2.addRentalUnit(new IndividualRoom(201, 5)); // Suite grande

        // Añadimos los pisos al resort
        resortEntero.addRentalUnit(piso1);
        resortEntero.addRentalUnit(piso2);

        // Añadimos también una cabaña independiente directamente al resort
        resortEntero.addRentalUnit(new IndividualRoom(999, 4));

        // 2. Verificar que el cálculo recursivo sea correcto (2 + 2 + 2 + 5 + 4 = 15)
        assertEquals(15, resortEntero.getCapacity());

        // 3. Imprimir por consola para ver la estructura de árbol (Opcional, útil para debugear)
        System.out.println(resortEntero.toString());
    }

}