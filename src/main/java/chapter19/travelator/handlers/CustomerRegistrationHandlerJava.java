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

public class CustomerRegistrationHandlerJava {

    private final CustomerRegistrationHandlerJava registration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomerRegistrationHandlerJava(CustomerRegistrationHandlerJava registration) {
        this.registration = registration;
    }

    public Response handle(Request request) {
        try {
            RegistrationData data = objectMapper.readValue(
                request.getBody(),
                RegistrationData.class
            );
//            Customer customer = registration.registerToo(data);
            return new Response(HTTP_CREATED,
                objectMapper.writeValueAsString("test")
            );
        } catch (JsonProcessingException x) {
            return new Response(HTTP_BAD_REQUEST);
        } catch (Exception x) {
            return new Response(HTTP_INTERNAL_ERROR);
        }
    }
}
