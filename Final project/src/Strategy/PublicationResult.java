package Strategy;

// Objeto con el resultado del procesamiento
public class PublicationResult {

    private boolean success;
    private String trackingId;
    private double finalPriceWithFees;
    private String logs;

    public PublicationResult(boolean success, String trackingId, double finalPriceWithFees, String logs) {
        this.success = success;
        this.trackingId = trackingId;
        this.finalPriceWithFees = finalPriceWithFees;
        this.logs = logs;
    }

    public boolean isSuccess() { return success; }
    public String getTrackingId() { return trackingId; }
    public double getFinalPriceWithFees() { return finalPriceWithFees; }
    public String getLogs() { return logs; }

}