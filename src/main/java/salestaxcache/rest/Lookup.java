package salestaxcache.rest;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import salestaxcache.Constants;
import salestaxcache.cache.SalesTaxCache;
import salestaxcache.address.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path(Constants.Api.ROOT)
@Singleton
public class Lookup {
    private static final Logger log = Logger.getLogger( Lookup.class.getName() );
    SalesTaxCache cache;

    @Inject
    public Lookup(SalesTaxCache cache) {
        this.cache = cache;
    }

    @GET
    @Path(Constants.Api.SALES_TAX_LOOKUP)
    @Produces(MediaType.APPLICATION_JSON)
    public Double getSalesTaxRate(@QueryParam("address") String addressStr) throws InterruptedException {
        Address address = new Address(addressStr);
        return address.getTaxRate();
    }

    @GET
    @Path(Constants.Api.FAST_LOOKUP)
    @Produces(MediaType.APPLICATION_JSON)
    public Double getFastSalesTaxRate(@QueryParam("address") String address) throws InterruptedException {
        return cache.getFastSalesTax(new Address(address));
    }
}