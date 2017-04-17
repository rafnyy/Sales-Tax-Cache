# Sales Tax Lookup

#BACKGROUND

Determining the sales tax rate at a given address is an expensive operation. Our team has written a function called sales_tax_lookup, that takes a street address as a parameter and returns the sales tax rate, but it is slow to return a result.

#PROBLEM

While there are billions of addresses, we've identified that 95% of lookups come from a relatively small number of addresses (less than 100K addresses). Unfortunately, you won't know what these addresses are in advance.

You have been tasked with writing a caching layer, with a constraint that the cache can only be large enough to store approximately 50K addresses. Your goal is to produce a function "fast_rate_lookup" that returns the same results as "sales_tax_lookup", but with better performance.

#NOTES
* Optimize for the fewest number of calls to sales_tax_lookup while still maintaining full functionality.
* It is okay for the cache to start empty, and to fill over time. There is no need to pre-warm the cache when the server reboots.
* Assume this will run on a single server, and that the cache can be stored in program memory (i.e., a persistent variable is okay for the exercise).

#SOLUTION
The solutions runs as REST service using embedded Jetty. Jersey is used to define the REST endpoints and Guice injection is used.

To run, simply compile and execute the main method in Main.java and the service will start.

The slow lookup is faked as it is not part of this excercise.