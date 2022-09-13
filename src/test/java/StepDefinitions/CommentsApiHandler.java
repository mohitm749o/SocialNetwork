package StepDefinitions;

import RequestBuilders.Comments.CommentsRequest;
import RequestBuilders.Comments.CommentsFailure;
import ResponseBuilders.Comments.CommentsResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.Constants;
import utils.ExcelFileReader;
import utils.Services;

import java.util.List;
import java.util.Map;

/**
 * A child test class which holds actual methods
 * or step definitions for CommentOnPost feature file
 * scenarios
 *
 * @author Mohit Mehta
 */
public class CommentsApiHandler extends BaseApiHandler {

    private static Map<Integer, List<Object>> dataMap;
    private static CommentsRequest commentsRequests = new CommentsRequest();
    private static CommentsFailure commentsRequestFailure = new CommentsFailure();
    private final String filePath;
    private final String sheetName;

    //default values loaded from property file application-<>.properties
    private static Integer defaultPostID;
    private static String defaultName;
    private static String defaultEmail;
    private static String defaultBody;

    /**
     * Public constructor which initialises start-up configs and parameters for Comment API
     */
    public CommentsApiHandler() {
        filePath = properties.getProperty(Constants.DATA_FILE_PATH);
        sheetName = properties.getProperty(Constants.SHEET_NAME_COMMENTS);
        defaultPostID = Integer.parseInt(properties.getProperty(Constants.DEFAULT_POST_ID));
        defaultName = properties.getProperty(Constants.DEFAULT_NAME);
        defaultEmail = properties.getProperty(Constants.DEFAULT_EMAIL);
        defaultBody = properties.getProperty(Constants.DEFAULT_BODY);
    }

    /**
     * This method is left blank as
     * authorisation feature is currently not available
     */
    @Given("I am an authorised user to comment on posts")
    public void iAmAnAuthorisedUserToCommentOnPosts() {
    }

    @Given("I have data loaded for commenting on posts")
    public void iHaveDataLoadedForCommentingOnPosts() {
        dataMap = ExcelFileReader.readExcelFile(filePath, sheetName);
    }

    private void formCommentsRequest(Integer postId, String name, String email, String body) {
        commentsRequests.setPostId(postId);
        commentsRequests.setName(name);
        commentsRequests.setEmail(email);
        commentsRequests.setBody(body);
    }

    @And("I have valid request to comment on a post for data row {int}")
    public void iHaveValidRequestToCommentOnAPostForDataRowDataRow(int dataRow) {
        Assert.assertFalse("Please use row value which are populated in data sheet", dataRow >= dataMap.size());
        Assert.assertNotEquals("You cannot use header as data input", 0, dataRow);
        path = Services.servicePath.get(Services.CommentOnAPost);
        List<Object> currentDataRow = dataMap.get(dataRow);
        formCommentsRequest(Integer.parseInt(currentDataRow.get(0).toString()),
                currentDataRow.get(1).toString(),
                currentDataRow.get(2).toString(),
                currentDataRow.get(3).toString()
        );
        requestSpecification.body(commentsRequests);
    }

    @When("I execute the POST request to comment on a post")
    public void iExecuteThePOSTRequestToCommentOnAPost() {
        response = requestSpecification.post(path);
        responseBody = response.asString();
    }

    @And("response body has valid values same as comments request")
    public void responseBodyHasValidValuesSameAsCommentsRequest() {
        commentsResponse = gson.fromJson(responseBody, CommentsResponse.class);
        validateCommentsResponse();
    }

    private void validateCommentsResponse() {
        Assert.assertArrayEquals("Response doesn't match with request values", commentsRequests.getStreamOfObject(), commentsResponse.getStreamOfObject());
    }

    @Given("I have request to comment on a post with all mandatory fields")
    public void iHaveRequestToCommentOnAPostWithAllMandatoryFields() {
        path = Services.servicePath.get(Services.CommentOnAPost);
        commentsRequests = new CommentsRequest(defaultPostID, defaultName, defaultEmail, defaultBody);
        requestSpecification.body(commentsRequests);
    }

    @But("field {string} for commenting on a post is not sent")
    public void fieldForCommentingOnAPostIsNotSent(String field) {
        switch (field) {
            case "postId":
                commentsRequests.setPostId(null);
                break;
            case "name":
                commentsRequests.setName(null);
                break;
            case "email":
                commentsRequests.setEmail(null);
                break;
            case "body":
                commentsRequests.setBody(null);
                break;
        }
        requestSpecification.body(commentsRequests);
    }

    @Given("I have field format request to comment on a post with all mandatory fields")
    public void iHaveFieldFormatRequestToCommentOnAPostWithAllMandatoryFields() {
        path = Services.servicePath.get(Services.MakeAPost);
        commentsRequestFailure = new CommentsFailure(defaultPostID.toString(), defaultName, defaultEmail, defaultBody);
        requestSpecification.body(commentsRequestFailure);
    }

    @But("field {string} is set with value {string} for commenting on a post")
    public void fieldIsSetWithValueForCommentingOnAPost(String field, String value) {
        value = (value.equalsIgnoreCase("greaterThanallowedCharacters")) ?
                "<logic to create text as per specs length>" : value;
        switch (field) {
            case "postId":
                commentsRequestFailure.setPostId(value);
                break;
            case "name":
                commentsRequestFailure.setName(value);
                break;
            case "email":
                commentsRequestFailure.setEmail(value);
                break;
            case "body":
                commentsRequestFailure.setBody(value);
                break;
        }
        requestSpecification.body(commentsRequestFailure);
    }
}
