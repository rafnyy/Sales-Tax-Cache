package salestaxcache.address;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddressTest {
    int num = 2348;
    String street = "Westfield Ave.";
    String city = "Scotch Plains";
    State state = State.valueOfName("New Jersey");

    String addressStr1 = "2348 Westfield Ave.\nScotch Plains, NJ";
    String addressStr2 = "601 NW 49th St.\nSeattle, WA";

    Address address = new Address(num, street, city, state);
    Address address1 = new Address(addressStr1);
    Address address2 = new Address(addressStr2);

    @Test
    public void testEquals() throws Exception {
        assertEquals(address1.equals(address2), false);
        assertEquals(address1.equals(address1), true);
        assertEquals(address.equals(address1), true);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(address1.toString(), addressStr1);
        assertEquals(address.toString(), addressStr1);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(address.hashCode(), address1.hashCode());
        assertNotEquals(address1.equals(address1), address2.hashCode());
    }

    @Test
    public void testGetTaxRate() throws Exception {
        assertEquals(address.getTaxRate(), address1.getTaxRate());
        assertEquals(address1.getTaxRate(), address1.getTaxRate());
        assertNotEquals(address1.getTaxRate(), address2.getTaxRate());

        assertEquals(address.getTaxRate() > 0, true);
        assertEquals(address.getTaxRate() < 10, true);
    }
}