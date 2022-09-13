package StepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

/**
 * A generic test class which holds generic methods
 * or step definitions for different feature files.
 * This should contain specific methods which are not
 * required to be shared between features
 *
 * @author Mohit Mehta
 */
public class GenericApiHandler extends BaseApiHandler {


    @Then("^I receive (\\d+) response$")
    public void iReceiveSuccessResponse(int responseType) {
        Assert.assertEquals("Response is not success, received " + response.getStatusCode(), response.getStatusCode(), responseType);
    }
}
