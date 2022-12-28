package travelator.marketing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HighValueCustomersMainJava {

    public static void main(String[] args) throws IOException {
        try (
                var reader = new InputStreamReader(System.in);
                var writer = new OutputStreamWriter(System.out)
        ) {
            HighValueCustomersReportJava.generate(reader, writer);
        }
    }
}
