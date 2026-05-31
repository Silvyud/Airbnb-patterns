package ChainOfResponsability;

public class UserHandler extends LoginHandler{

    public UserHandler(DataBase database) {
        super(database);
    }

    @Override
    public String verify(LoginDTO loginDTO) {
        if(DB.checkUser(loginDTO.getUsername())) {
            return next!= null ? next.verify(loginDTO) : getDefaultMessage();
        }else {
            return "User not found";
        }
    }
}
