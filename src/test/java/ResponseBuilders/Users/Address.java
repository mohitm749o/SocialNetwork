package ResponseBuilders.Users;

import java.util.Objects;
import java.util.stream.Stream;
/**
 * A response builder helper POJO class to work with get users API
 *
 * @author Mohit Mehta
 */
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    //Getters & Setter methods
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    /**
     * Method to check if any member varaible is null
     * @return true if any member variable of current object is null OR false
     */
    public boolean isAnyFieldNull() {
        return Stream.of(street, suite, city, zipcode, geo).anyMatch(Objects::isNull);
    }
}
