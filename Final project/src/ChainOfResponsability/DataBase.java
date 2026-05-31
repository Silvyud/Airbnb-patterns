package ChainOfResponsability;

import java.util.HashMap;

public class DataBase {
    private final HashMap<String, String> users;

    public DataBase() {
        this.users = new HashMap<>();
    }

    public DataBase(HashMap<String, String> users) {
        this.users = users;
    }

    public void addUser(LoginDTO user) {
        users.put(user.getUsername(), user.getPassword());
    }

    public boolean checkUser(String user){
        return users.containsKey(user);
    }

    public boolean checkPassword(String user, String password){
        return users.get(user).equals(password);
    }
}