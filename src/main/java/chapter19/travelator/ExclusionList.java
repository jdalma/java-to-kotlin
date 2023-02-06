package chapter19.travelator;

import chapter19.travelator.handlers.RegistrationData;

public interface ExclusionList {
    boolean exclude(RegistrationData registrationData);
}