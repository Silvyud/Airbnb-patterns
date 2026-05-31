package Test;

import Memento.Memento;
import Memento.User;
import Memento.Review;
import Memento.Caretaker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MementoTest {

    @Test
    public void testAutoguardadoYRestauracionDeResena() {
        // 1. Configurar el escenario inicial (el usuario empieza a escribir)
        User usuario = new User("silvi_viajera");
        Review resena = new Review(5, "Excelente ubicación, me encantó.", usuario);
        Caretaker guardian = new Caretaker();

        // 2. Simular un Autoguardado de la aplicación
        guardian.addMemento(resena.createMemento());

        // 3. El usuario continúa escribiendo y cambia drásticamente de opinión (o comete un error)
        resena.setStars(1);
        resena.setOpinion("Pésimo servicio, mentira, me equivoqué de publicación.");

        // Verificar que el estado actual de la reseña cambió en caliente
        assertEquals(1, resena.getStars());
        assertEquals("Pésimo servicio, mentira, me equivoqué de publicación.", resena.getOpinion());

        // 4. Simular la recuperación: El usuario cancela la última edición o la app recupera el autoguardado
        Memento respaldo = guardian.getMemento(usuario);
        resena.setMemento(respaldo);

        // 5. Verificar que regresó perfectamente a su estado original guardado
        assertEquals(5, resena.getStars());
        assertEquals("Excelente ubicación, me encantó.", resena.getOpinion());
        assertEquals("silvi_viajera", resena.getUser().getUsername());
    }

    @Test
    public void testModificacionSinGuardarNoAfectaAlRespaldo() {
        // Asegurar que el memento sea verdaderamente inmutable y no se altere por modificar la reseña viva
        User usuario = new User("jesus_16");
        Review resena = new Review(4, "Buen lugar.", usuario);
        Caretaker guardian = new Caretaker();

        // Se guarda el estado
        guardian.addMemento(resena.createMemento());

        // Se altera la reseña viva
        resena.setOpinion("Cambiando el texto sin llamar a crear memento.");

        // El memento en el caretaker debe mantener la información original intacta
        Memento guardado = guardian.getMemento(usuario);
        assertEquals("Buen lugar.", guardado.getOpinion());
        assertEquals(4, guardado.getStars());
    }

}