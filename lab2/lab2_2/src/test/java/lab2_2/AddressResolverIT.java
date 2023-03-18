package lab2_2;

import java.util.Optional;

import lab2_2.Address;
import lab2_2.AddressResolver;
import lab2_2.TqsBasicHttpClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class AddressResolverIT {

    AddressResolver resolver;
    
    @BeforeEach
    public void init(){
        resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = resolver.findAddressForLocation( 40.633116,-8.658784);

        Address expected = new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null);

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation( -361,-361));
        
    }

}
