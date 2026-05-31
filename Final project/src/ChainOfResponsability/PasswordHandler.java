package ChainOfResponsability;

public class PasswordHandler extends LoginHandler{
    public PasswordHandler(DataBase database) {
        super(database);
    }
    @Override
    public String verify(LoginDTO loginDTO) {
        if(DB.checkPassword(loginDTO.getUsername(), loginDTO.getPassword())) {
            return next!= null ? next.verify(loginDTO) : getDefaultMessage();
        }
        return "Invalid password";
    }
}
