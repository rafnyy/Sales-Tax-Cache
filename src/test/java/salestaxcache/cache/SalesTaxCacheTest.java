package salestaxcache.cache;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import salestaxcache.address.Address;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.testng.Assert.*;

public class SalesTaxCacheTest {

    SalesTaxCache salesTaxCache;

    @Mock
    private Address address1;

    @Mock
    private Address address2;

    @Mock
    private Address address3;

    @BeforeMethod
    public void setUpFreshCache() throws Exception {
        initMocks(this);

        when(address1.getTaxRate()).thenReturn(1.1);
        when(address2.getTaxRate()).thenReturn(2.2);
        when(address3.getTaxRate()).thenReturn(3.3);

        salesTaxCache = new SalesTaxCache(2);
    }

    @Test
    public void testGetFastSalesTax() throws Exception {
        double attempt1 = salesTaxCache.getFastSalesTax(address1);
        double attempt2 = salesTaxCache.getFastSalesTax(address1);

        verify(address1, times(1)).getTaxRate();
        assertEquals(attempt1, 1.1);
        assertEquals(attempt2, 1.1);
        assertEquals(attempt1, attempt2);
    }

    @Test
    public void testGetFastSalesTaxIsLRU() throws Exception {
        salesTaxCache.getFastSalesTax(address1); // not from cache 1st
        salesTaxCache.getFastSalesTax(address1); // from cache
        salesTaxCache.getFastSalesTax(address2);
        salesTaxCache.getFastSalesTax(address3); // address1 dropped from cache
        salesTaxCache.getFastSalesTax(address1); // not from cache 2nd
        salesTaxCache.getFastSalesTax(address1); // from cache

        verify(address1, times(2)).getTaxRate();
    }

    @Test
    public void testGetFastSalesTaxAfterMany() throws Exception {
        salesTaxCache.getFastSalesTax(address1);
        salesTaxCache.getFastSalesTax(address1);
        salesTaxCache.getFastSalesTax(address2);
        salesTaxCache.getFastSalesTax(address1);
        salesTaxCache.getFastSalesTax(address2);
        salesTaxCache.getFastSalesTax(address2);
        salesTaxCache.getFastSalesTax(address1);
        salesTaxCache.getFastSalesTax(address2);
        salesTaxCache.getFastSalesTax(address3);
        salesTaxCache.getFastSalesTax(address3);

        verify(address3, times(1)).getTaxRate();
    }
}