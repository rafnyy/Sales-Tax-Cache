package salestaxcache.cache;

import com.google.inject.Singleton;
import salestaxcache.address.Address;

import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
public class SalesTaxCache {
    int maxEntries;

    LinkedHashMap<Address, Double> cache = new LinkedHashMap<Address, Double>(maxEntries*10/7, 0.7f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Address, Double> eldest) {
                return size() > maxEntries;
            }
        };

    public SalesTaxCache() {
        this.maxEntries = 50000;
    }

    public SalesTaxCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }
    

    public Double getFastSalesTax(Address address) throws InterruptedException {
        Double rate = cache.get(address);
        if(rate == null){
            rate = address.getTaxRate();
            cache.put(address, rate);
        }

        return rate;
    }
}