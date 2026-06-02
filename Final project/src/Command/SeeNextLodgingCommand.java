package Command;

import Utilities.Lodging;
import Utilities.User;

public class SeeNextLodgingCommand implements Command {
    private final User user;
    private final DataBase dataBase;

    public SeeNextLodgingCommand(User user, DataBase dataBase) {
        this.user = user;
        this.dataBase = dataBase;
    }


    @Override
    public String execute() {
        Lodging result = dataBase.getNextReservation(user.getId());
        return result == null ? "No hay ninguna reserva pendiente" : result.toString();
    }
}
