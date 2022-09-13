package StepDefinitions;

import RequestBuilders.Posts.PostsRequest;
import RequestBuilders.Posts.PostsRequestFailure;
import ResponseBuilders.Posts.PostsResponse;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utils.Constants;
import utils.ExcelFileReader;
import utils.Services;

import java.util.List;
import java.util.Map;

/**
 * A child test class which holds actual methods
 * or step definitions for CreatePosts feature file
 * scenarios
 *
 * @author Mohit Mehta
 */
public class PostsApiHandler extends BaseApiHandler {

    private final String filePath;
    private final String sheetName;
    private static Map<Integer, List<Object>> dataMap;
    private static PostsRequest postsRequests = new PostsRequest();
    private static PostsRequestFailure postsRequestFailure = new PostsRequestFailure();

    //default values loaded from property file application-<>.properties
    private static Integer defaultUserID;
    private static String defaultTittle;
    private static String defaultBody;

    public PostsApiHandler() {
        filePath = properties.getProperty(Constants.DATA_FILE_PATH);
        sheetName = properties.getProperty(Constants.SHEET_NAME_POSTS);

        defaultUserID = Integer.parseInt(properties.getProperty(Constants.DEFAULT_USER_ID));
        defaultTittle = properties.getProperty(Constants.DEFAULT_TITTLE);
        defaultBody = properties.getProperty(Constants.DEFAULT_BODY);

    }

    /*
     * This method is left blank as
     * authorisation feature is currently not available
     * */
    @Given("I am an authorised user to make posts on social networking site")
    public void iAmAnAuthorisedUserToMakePostsOnSocialNetworkingSite() {
    }

    @And("I have data loaded for making posts")
    public void iHaveDataLoadedForMakingPosts() {
        dataMap = ExcelFileReader.readExcelFile(filePath, sheetName);
    }

    private void formPostsRequest(String title, String body, Integer userId) {
        postsRequests.setUserId(userId);
        postsRequests.setTitle(title);
        postsRequests.setBody(body);
    }

    @And("I have valid request to make a post for data row {int}")
    public void iHaveValidRequestToMakeAPostForDataRow(int dataRow) {
        Assert.assertFalse("Please use row value which are populated in data sheet", dataRow >= dataMap.size());
        Assert.assertNotEquals("You cannot use header as data input", 0, dataRow);
        path = Services.servicePath.get(Services.MakeAPost);
        List<Object> currentDataRow = dataMap.get(dataRow);
        formPostsRequest(currentDataRow.get(0).toString(),
                currentDataRow.get(1).toString(),
                Integer.parseInt(currentDataRow.get(2).toString()));
        requestSpecification.body(postsRequests);

    }

    @When("I execute the POST request to make a post")
    public void iExecuteThePOSTRequestToMakeAPost() {
        response = requestSpecification.post(path);
        responseBody = response.asString();
    }


    @And("response body has valid values same as request")
    public void responseBodyHasValidValuesSameAsRequest() {
        postResponse = gson.fromJson(responseBody, PostsResponse.class);
        validatePostResponse();

    }

    /**
     * Validation method to cover business validations.
     * This method can be used to maintain any future additions to validation
     */
    private void validatePostResponse() {
        Assert.assertArrayEquals("Response doesn't match with request values", postsRequests.getStreamOfObject(), postResponse.getStreamOfObject());
    }

    @Given("I have request to make a post with all mandatory fields")
    public void iHaveRequestToMakeAPostWithAllMandatoryFields() {
        path = Services.servicePath.get(Services.MakeAPost);
        postsRequests = new PostsRequest(defaultUserID, defaultTittle, defaultBody);
        requestSpecification.body(postsRequests);
    }

    @But("field {string} is not sent")
    public void fieldIsNotSent(String field) {
        switch (field) {
            case "userId":
                postsRequests.setUserId(null);
                break;
            case "title":
                postsRequests.setTitle(null);
                break;
            case "body":
                postsRequests.setBody(null);
                break;
        }
        requestSpecification.body(postsRequests);
    }

    @Given("I have field format request to make a post with all mandatory fields")
    public void iHaveFieldFormatRequestToMakeAPostWithAllMandatoryFields() {
        path = Services.servicePath.get(Services.MakeAPost);
        postsRequestFailure = new PostsRequestFailure(defaultUserID.toString(), defaultTittle, defaultBody);
        requestSpecification.body(postsRequestFailure);
    }

    @But("field {string} is set with value {string}")
    public void fieldIsSetWithValue(String field, String value) {
        value = (value.equalsIgnoreCase("greaterThanallowedCharacters")) ?
                "<logic to create text as per specs length>" : value;
        switch (field) {
            case "userId":
                postsRequestFailure.setUserId(value);
                break;
            case "title":
                postsRequestFailure.setTitle(value);
                break;
            case "body":
                postsRequestFailure.setBody(value);
                break;
        }
        requestSpecification.body(postsRequestFailure);
    }
}
