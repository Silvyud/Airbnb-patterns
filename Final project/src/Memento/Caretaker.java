package Memento;

import java.util.HashMap;

public class Caretaker {

    private HashMap<User, Memento> reviews;

    public Caretaker() {
        this.reviews = new HashMap<>();
    }

    public void addMemento(Memento memento) {
        if (memento != null) {
            reviews.put(memento.getUser(), memento);
        }
    }

    public Memento getMemento(User user) {
        return reviews.get(user);
    }

}