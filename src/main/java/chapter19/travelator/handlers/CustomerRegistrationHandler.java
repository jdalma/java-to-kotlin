package chapter19.travelator.handlers;

import chapter19.travelator.Customer;
import chapter19.travelator.DuplicateException;
import chapter19.travelator.ExcludedException;
import chapter19.travelator.IRegisterCustomers;
import chapter19.travelator.http.Request;
import chapter19.travelator.http.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.net.HttpURLConnection.*;

public class CustomerRegistrationHandler {

    private final IRegisterCustomers registration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomerRegistrationHandler(IRegisterCustomers registration) {
        this.registration = registration;
    }

    public Response handle(Request request) {
        try {
            RegistrationData data = objectMapper.readValue(
                request.getBody(),
                RegistrationData.class
            );
            Customer customer = registration.register(data);
            return new Response(HTTP_CREATED,
                objectMapper.writeValueAsString(customer)
            );
        } catch (JsonProcessingException x) {
            return new Response(HTTP_BAD_REQUEST);
        } catch (ExcludedException x) {
            return new Response(HTTP_FORBIDDEN);
        } catch (DuplicateException x) {
            return new Response(HTTP_CONFLICT);
        } catch (Exception x) {
            return new Response(HTTP_INTERNAL_ERROR);
        }
    }
}