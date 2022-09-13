package ResponseBuilders.Users;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A response builder helper POJO class to work with get users API
 *
 * @author Mohit Mehta
 */
public class Geo {

    private String lat;
    private String lng;

    //Getters & Setter methods
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * Method to check if any member varaible is null
     *
     * @return true if any member variable of current object is null OR false
     */
    public boolean isAnyFieldNull() {
        return Stream.of(lat, lng).anyMatch(Objects::isNull);
    }
}
