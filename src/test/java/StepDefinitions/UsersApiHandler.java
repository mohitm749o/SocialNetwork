package StepDefinitions;

import ResponseBuilders.Users.User;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utils.Services;

import java.util.Arrays;

/**
 * A child test class which holds actual methods
 * or step definitions for LIstUsers feature file
 * scenarios
 *
 * @author Mohit Mehta
 */
public class UsersApiHandler extends BaseApiHandler {


    /*
     * The API doesn't have authorisation implemented hence this
     * method is left empty
     * */
    @Given("I am an authorised user to view list of users")
    public void iAmAnAuthorisedUserToViewListOfUsers() {
    }

    @Given("I have valid request to view all the users")
    public void iHaveValidRequestToViewAllTheUsers() {
        path = Services.servicePath.get(Services.GetAllUsers);
    }

    @When("^I execute the (\\S+) request$")
    public void iExecuteTheRequest(String method) {
        switch (method) {
            case "GET":
                response = requestSpecification.get(path);
                break;
            case "POST":
                response = requestSpecification.post(path);
                break;
        }
        responseBody = response.asString();
        System.out.println(responseBody);
    }


    @And("I am able to view all the users within social network site")
    public void iAmAbleToViewAllTheUsersWithinSocialNetworkSite() {
        usersListResponse = gson.fromJson(responseBody, User[].class);
    }

    @And("I am able to verify all the fields in response are present for all users")
    public void iAmAbleToVerifyAllTheFieldsInResponseArePresentForAllUsers() {
        Assert.assertFalse("", Arrays.stream(usersListResponse).anyMatch(User::isAnyFieldNull));
        for (User user : usersListResponse) {
            Assert.assertFalse("", user.getAddress().isAnyFieldNull());
            Assert.assertFalse("", user.getAddress().getGeo().isAnyFieldNull());
            Assert.assertFalse("", user.getCompany().isAnyFieldNull());
        }
    }

    @Given("I have valid request to view a given user {string}")
    public void iHaveValidRequestToViewAGivenUser(String userID) {
        path = String.format(Services.servicePath.get(Services.GetAnUser), userID);
    }

    @And("I am able to view given user within social network site")
    public void iAmAbleToViewGivenUserWithinSocialNetworkSite() {
        userResponse = gson.fromJson(responseBody, User.class);
    }

    @And("I am able to verify all the fields in response are present for given user")
    public void iAmAbleToVerifyAllTheFieldsInResponseArePresentForGivenUser() {
        Assert.assertFalse("Address data block has null fields", userResponse.getAddress().isAnyFieldNull());
        Assert.assertFalse("Geo data block has null fields", userResponse.getAddress().getGeo().isAnyFieldNull());
        Assert.assertFalse("Company data block has null fields", userResponse.getCompany().isAnyFieldNull());

    }

    @Given("I have valid request to view a given invalid user {string}")
    public void iHaveValidRequestToViewAGivenInvalidUser(String userID) {
        path = String.format(Services.servicePath.get(Services.GetAnUser), userID);
    }

    @And("response body is empty")
    public void responseBodyIsEmpty() {
        userResponse = gson.fromJson(responseBody, User.class);
        Assert.assertTrue(userResponse.areAllFieldsNull());
    }
}
