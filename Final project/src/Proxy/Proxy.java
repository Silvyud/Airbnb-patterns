package Proxy;

public class Proxy implements AccommodationData {

    private Accommodation realAccommodation;
    private String owner;
    private String userAttemptingAccess;

    public Proxy(Accommodation realAccomodation, String owner, String userAttemptingAccess) {
        this.realAccommodation = realAccomodation;
        this.owner = owner;
        this.userAttemptingAccess = userAttemptingAccess;
    }

    @Override
    public String checkLocation() {
        String guest = realAccommodation.getActualGuest();

        // Regla del Proxy: Solo permite ver la ubicación real al dueño o al huésped con reserva activa
        if (userAttemptingAccess.equals(owner) || (guest != null && userAttemptingAccess.equals(guest))) {
            // Delega la petición al objeto real (Seguridad superada)
            return realAccommodation.checkLocation();
        } else {
            // Bloquea u oculta los datos sensibles mostrando solo información general/pública
            return "Ubicación: "
                    + realAccommodation.town + ", " + realAccommodation.city + ". (Solo disponible para el dueño o huéspedes con reserva)";
        }
    }

    // Permite cambiar dinámicamente el usuario que consulta en las pruebas
    public void setUserAttemptingAccess(String userAttemptingAccess) {
        this.userAttemptingAccess = userAttemptingAccess;
    }

}