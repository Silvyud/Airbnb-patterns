package Composite;

import java.util.ArrayList;

// Elemento Contenedor (Composite)
public class PropertyGroup implements RentalUnit {

    private ArrayList<RentalUnit> rooms;

    public PropertyGroup() {
        this.rooms = new ArrayList<>();
    }

    public void addRentalUnit(RentalUnit unit) {
        rooms.add(unit);
    }

    public void removeRentalUnit(RentalUnit unit) {
        rooms.remove(unit);
    }

    @Override
    public int getCapacity() {
        int totalCapacity = 0;
        // Delega el cálculo a cada uno de sus hijos
        for (RentalUnit unit : rooms) {
            totalCapacity += unit.getCapacity();
        }
        return totalCapacity;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Complejo/Grupo [Capacidad total: ").append(getCapacity()).append("]\n");
        description.append("Contiene:\n");

        for (RentalUnit unit : rooms) {
            // Se llama recursivamente al toString() de los hijos
            description.append("  - ").append(unit.toString()).append("\n");
        }
        return description.toString();
    }

}