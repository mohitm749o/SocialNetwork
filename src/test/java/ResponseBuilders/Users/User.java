package ResponseBuilders.Users;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A response builder helper POJO class to work with get users API
 *
 * @author Mohit Mehta
 */
public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    //Getters & Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Method to check if any member varaible is null
     *
     * @return true if any member variable of current object is null OR false
     */
    public boolean isAnyFieldNull() {
        return Stream.of(id, name, username, email, address, phone, website, company).anyMatch(Objects::isNull);
    }

    /**
     * Method to check if all member varaibles are null
     *
     * @return true if all member variables of current object are null OR false
     */
    public boolean areAllFieldsNull() {
        return Stream.of(id, name, username, email, address, phone, website, company).allMatch(Objects::isNull);
    }
}
