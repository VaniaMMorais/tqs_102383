package lab2_2;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.util.Optional;

public class App 
{
    public class  main
    {
        static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * demo for address resolver
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        try {
            AddressResolver resolver =new AddressResolver( new TqsBasicHttpClient());
            
            Optional<Address> result = resolver.findAddressForLocation( 40.633116,-8.658784);
            log.info("Result: ".concat( result.get().toString() ) );

        } catch (URISyntaxException | IOException | ParseException | org.json.simple.parser.ParseException ex) {
            log.error(String.valueOf(ex));
        }
    }

    }
}
