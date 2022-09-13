package StepDefinitions;

import RequestBuilders.Comments.CommentsRequest;
import RequestBuilders.Posts.PostsRequest;
import ResponseBuilders.Comments.CommentsResponse;
import ResponseBuilders.Posts.PostsResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.Services;
import java.util.Random;

/**
 * A child test class which holds actual methods
 * or step definitions for E2E feature file
 * scenarios
 *
 * @author Mohit Mehta
 */
public class E2EApiHandler extends BaseApiHandler {
    private static Integer selectedUserID;
    private static Integer postID;
    private static PostsRequest postsRequests = new PostsRequest();
    private static CommentsRequest commentsRequest = new CommentsRequest();

    @And("I pick an user to prepare for making a post")
    public void iPickAnUserToPrepareForMakingAPost() {
        int randomUserSelected = new Random().ints(0, usersListResponse.length - 1).findFirst().getAsInt();
        selectedUserID = usersListResponse[randomUserSelected].getId();
    }

    @When("I execute the POST request to make a post with all mandatory fields {string} {string}")
    public void iExecuteThePOSTRequestToMakeAPostWithAllMandatoryFields(String tittle, String body) {
        path = Services.servicePath.get(Services.MakeAPost);
        postsRequests = new PostsRequest(selectedUserID, tittle, body);
        requestSpecification.body(postsRequests);
        response = requestSpecification.post(path);
        responseBody = response.asString();
        System.out.println(responseBody);
    }

    @And("I prepare to comment on this post")
    public void iPrepareToCommentOnThisPost() {
        postID = postResponse.getId();
    }

    @When("I execute request to comment on a post with all mandatory fields {string} {string} {string}")
    public void iExecuteRequestToCommentOnAPostWithAllMandatoryFields(String name, String email, String body) {
        path = Services.servicePath.get(Services.MakeAPost);
        commentsRequest = new CommentsRequest(postID, name, email, body);
        requestSpecification.body(commentsRequest);
        response = requestSpecification.post(path);
        responseBody = response.asString();
        System.out.println(responseBody);
    }


    @And("response body has valid values same as post request")
    public void responseBodyHasValidValuesSameAsPostRequest() {
        postResponse = gson.fromJson(responseBody, PostsResponse.class);
        validatePostResponse();
    }

    private void validatePostResponse() {
        Assert.assertArrayEquals("Response doesn't match with request values", postsRequests.getStreamOfObject(), postResponse.getStreamOfObject());
    }

    @And("response body has valid values same as above comments request")
    public void responseBodyHasValidValuesSameAsAboveCommentsRequest() {
        commentsResponse = gson.fromJson(responseBody, CommentsResponse.class);
        validateCommentsResponse();
    }

    private void validateCommentsResponse() {
        Assert.assertArrayEquals("Response doesn't match with request values", commentsRequest.getStreamOfObject(), commentsResponse.getStreamOfObject());
    }
}
