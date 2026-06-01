package TemplateMethod;

import Utilities.Lodging;
import Utilities.User;

public record CancellationContext(Lodging lodging, User client, User owner, int daysBeforeCheckIn, double amount,
                                  boolean naturalDisaster) {
}