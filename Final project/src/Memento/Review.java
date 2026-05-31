package Memento;

public class Review {

    private int stars;
    private String opinion;
    private User user;

    public Review(int stars, String opinion, User user) {
        this.stars = stars;
        this.opinion = opinion;
        this.user = user;
    }

    // Crea la "fotografía" de la reseña en su estado actual
    public Memento createMemento() {
        return new Memento(this.stars, this.opinion, this.user);
    }

    // Restaura el estado a partir de un Memento anterior
    public void setMemento(Memento memento) {
        if (memento != null) {
            this.stars = memento.getStars();
            this.opinion = memento.getOpinion();
            this.user = memento.getUser();
        }
    }

    // Setters y Getters para simular la edición del usuario en la app
    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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
