package Memento;

public class Memento {

    private final int stars;
    private final String opinion;
    private final User user;

    public Memento(int stars, String opinion, User user) {
        this.stars = stars;
        this.opinion = opinion;
        this.user = user;
    }

    public int getStars() {
        return stars;
    }

    public String getOpinion() {
        return opinion;
    }

    public User getUser() {
        return user;
    }

}