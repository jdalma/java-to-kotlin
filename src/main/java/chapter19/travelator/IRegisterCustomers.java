package chapter19.travelator;

import chapter19.travelator.handlers.RegistrationData;

public interface IRegisterCustomers {
    Customer register(RegistrationData data)
        throws ExcludedException, DuplicateException;
}