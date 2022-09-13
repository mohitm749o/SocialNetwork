package RequestBuilders.Comments;

public class CommentsFailure {
    /**
     * A request builder POJO class to work with error scenarios
     * of comment on post API
     *
     * @author Mohit Mehta
     */
    private String postId;
    private String name;
    private String email;
    private String body;

    public CommentsFailure() {
    }

    /**
     * Constructor to initialise comments object with values
     *
     * @param postId
     * @param name
     * @param email
     * @param body
     */
    public CommentsFailure(String postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    //Getters & Setter methods
    public void setPostId(String postId) {
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
}
