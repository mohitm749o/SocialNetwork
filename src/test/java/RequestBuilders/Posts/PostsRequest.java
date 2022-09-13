package RequestBuilders.Posts;

import java.util.stream.Stream;
/**
 * A request builder POJO class to work with happy path scenarios
 * of make a post API
 *
 * @author Mohit Mehta
 */
public class PostsRequest {

    private Integer userId;
    private String title;
    private String body;

    public PostsRequest() {
    }

    /**
     * Constructor to initialise posts object with values
     * @param userId
     * @param title
     * @param body
     */
    public PostsRequest(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    //Getters & Setter methods
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Method to return array version of this class object
     * @return Object array of member variables
     */
    public Object[] getStreamOfObject() {
        return Stream.of(userId, title, body).toArray();
    }
}
