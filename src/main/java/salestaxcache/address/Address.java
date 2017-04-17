package salestaxcache.address;

public class Address {
    private int number;
    private String street;

    private String city;
    private State state;

    public Address(int number, String street, String city, State state) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public Address(String address) {
        String[] commaSplit = address.split(",");
        this.state = State.valueOfAbbreviation(commaSplit[1].trim());

        String[] lineSplit = commaSplit[0].split("\n");
        this.city = lineSplit[1].trim();

        String numStreet = lineSplit[0];
        int firstSpace = numStreet.indexOf(" ");
        this.number = Integer.valueOf(numStreet.substring(0, firstSpace));
        this.street = numStreet.substring(firstSpace).trim();
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (number != address.number) {
            return false;
        }

        if (street != null ? !street.equals(address.street) : address.street != null) {
            return false;
        }

        if (city != null ? !city.equals(address.city) : address.city != null) {
            return false;
        }

        return state.equals(address.state);
    }

    @Override
    public String toString() {
        return number + " " + street + "\n" + city + ", " + state.getAbbreviation();
    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getState().hashCode();
        return result;
    }

    /**
     * Mock slow get tax rate. Will return in number between 0 and 10 in 3 seconds.
     * Will return the same number for the same address.
     * @return a tax rate for this Address
     * @throws InterruptedException
     */
    public double getTaxRate() throws InterruptedException {
        Thread.sleep(3000);

        double result = number * 1.1;
        result += street.hashCode();
        result += city.hashCode();
        result += state.hashCode();

        return Math.abs(result) % 10;
    }
}