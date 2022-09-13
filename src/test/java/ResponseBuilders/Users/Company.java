package ResponseBuilders.Users;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A response builder helper POJO class to work with get users API
 *
 * @author Mohit Mehta
 */
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    //Getters & Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    /**
     * Method to check if any member varaible is null
     *
     * @return true if any member variable of current object is null OR false
     */
    public boolean isAnyFieldNull() {
        return Stream.of(name, catchPhrase, bs).anyMatch(Objects::isNull);
    }
}
