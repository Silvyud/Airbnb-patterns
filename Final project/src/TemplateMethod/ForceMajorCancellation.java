package TemplateMethod;

public class ForceMajorCancellation extends CancellationEngine{
    public ForceMajorCancellation(double cancellationGuestFee, double cancellationOwnerFee, double platformPercentage) {
        super(cancellationGuestFee, cancellationOwnerFee, platformPercentage);
    }

    @Override
    void calculatePlatformFee(CancellationContext context) {
        platformFee = 0;
    }

    @Override
    double calculateGuestRefound(int days) {
        return 1;
    }

    @Override
    double calculateHostRefound(int days) {
        return days <= 7? 1 : 0;
    }

    @Override
    void freeLodging(CancellationContext context) {
        if(!context.naturalDisaster()){
            context.lodging().setReserved(false);
        }
    }
}
