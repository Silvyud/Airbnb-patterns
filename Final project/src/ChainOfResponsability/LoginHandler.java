package ChainOfResponsability;

public abstract class LoginHandler {
    protected LoginHandler next;
    protected final DataBase DB;

    public LoginHandler(DataBase database) {
        next = null;
        DB = database;
    }

    public abstract String verify(LoginDTO loginDTO);

    public void setNext(LoginHandler next) {
        this.next = next;
    }

    public String getDefaultMessage() {
        return "You are logged in";
    }
}
