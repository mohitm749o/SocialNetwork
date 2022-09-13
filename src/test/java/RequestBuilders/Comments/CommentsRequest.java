package RequestBuilders.Comments;

import java.util.stream.Stream;
/**
 * A request builder POJO class to work with happy path scenarios
 * of comment on post API
 *
 * @author Mohit Mehta
 */
public class CommentsRequest {

    private Integer postId;
    private String name;
    private String email;
    private String body;

    public CommentsRequest() {
    }

    /**
     * Constructor to initialise comments object with values
     *
     * @param postId
     * @param name
     * @param email
     * @param body
     */
    public CommentsRequest(Integer postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    //Getters & Setter methods
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return Stream.of(postId, name, email, body).toArray();
    }

}
