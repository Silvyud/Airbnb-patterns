package TemplateMethod;

public class StrictCancellation extends CancellationEngine{

    public StrictCancellation(double cancellationGuestFee, double cancellationOwnerFee, double platformPercentage) {
        super(cancellationGuestFee, cancellationOwnerFee, platformPercentage);
    }

    @Override
    void calculatePlatformFee(CancellationContext context) {
        platformFee =  this.platformPercentage * context.amount();
    }

    @Override
    double calculateGuestRefound(int days) {
        return days > 7? this.cancellationGuestFee : 0;
    }

    @Override
    double calculateHostRefound(int days) {
        return days <= 7? this.cancellationOwnerFee : 0;
    }

    @Override
    void freeLodging(CancellationContext context) {
        context.lodging().setReserved(false);
    }
}
