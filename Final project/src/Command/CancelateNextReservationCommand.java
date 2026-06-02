package Command;

import Utilities.Lodging;
import Utilities.User;

public class CancelateNextReservationCommand implements Command{
    private final User user;
    private final DataBase dataBase;
    public CancelateNextReservationCommand(User user, DataBase dataBase) {
        this.user = user;
        this.dataBase = dataBase;
    }


    @Override
    public String execute() {
        Lodging reservation = dataBase.getNextReservation(user.getId());
        if(reservation != null){
            reservation.setReserved(false);
            return "Reserva cancelada";
        }
        return "Hubo algún error, peude que no haya ninguna reserva pendiente";
    }
}
