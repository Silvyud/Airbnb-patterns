package TemplateMethod;

import Utilities.Lodging;
import Utilities.User;

public abstract class CancellationEngine {
    protected double platformFee;
    protected double platformPercentage;
    protected double cancellationGuestFee;
    protected double cancellationOwnerFee;

    public CancellationEngine(double cancellationGuestFee, double cancellationOwnerFee, double platformPercentage) {
        this.cancellationGuestFee = cancellationGuestFee;
        this.cancellationOwnerFee = cancellationOwnerFee;
        this.platformPercentage = platformPercentage;
    }

    public final void cancelate(CancellationContext context){
        Lodging lodging = context.lodging();
        User client = context.client();
        User owner = context.owner();
        double refoundPercentage = calculateGuestRefound(context.daysBeforeCheckIn());
        client.pay(context.amount()* refoundPercentage);
        refoundPercentage = calculateHostRefound(context.daysBeforeCheckIn());
        owner.pay(context.amount()* refoundPercentage);
        calculatePlatformFee(context);
        freeLodging(context);
    }

    abstract void calculatePlatformFee(CancellationContext context);
    abstract double calculateGuestRefound(int days);
    abstract double calculateHostRefound(int days);
    abstract void freeLodging(CancellationContext context);

    public double getPlatformFee() {
        return platformFee;
    }
}