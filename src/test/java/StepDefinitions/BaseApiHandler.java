package StepDefinitions;

import ResponseBuilders.Comments.CommentsResponse;
import ResponseBuilders.Posts.PostsResponse;
import ResponseBuilders.Users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Constants;
import utils.PropertiesUtil;
import java.util.Properties;

/**
 * A base test class which holds common test or helper methods
 * to be shared with individual step definition classes
 *
 * @author Mohit Mehta
 */
public class BaseApiHandler {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
    protected Properties properties;
    protected static RequestSpecification requestSpecification;
    protected static Response response;
    protected static String responseBody;
    protected static User[] usersListResponse;
    protected static User userResponse;
    protected static PostsResponse postResponse;
    protected static CommentsResponse commentsResponse;
    protected static String path;

    /**
     * Public constructor which initialises start-up configs and parameters
     */
    public BaseApiHandler() {
        String propFile = String.format("src/test/resources/application-%s.properties", System.getProperty(Constants.SYS_FEATURE_ENV).toLowerCase());
        properties = propertiesUtil.getProperties(propFile);
        RestAssured.baseURI = properties.getProperty(Constants.BASE_URL);
        requestSpecification = RestAssured.given();
        setRequestHeaders();
    }

    /**
     * Method to set request headers
     */
    protected void setRequestHeaders() {
        requestSpecification.header("content-Type", "application/json; charset=UTF-8");
    }
}
