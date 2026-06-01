package Visitor;

public class Owner implements Element {

    private String name;
    private boolean verified; // Ajustado a boolean según el último diagrama

    public Owner(String name, boolean verified) {
        this.name = name;
        this.verified = verified;
    }

    public String getName() { return name; }
    public boolean isVerified() { return verified; }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitOwner(this);
    }

}