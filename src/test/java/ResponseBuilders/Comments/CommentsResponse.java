package ResponseBuilders.Comments;

import java.util.stream.Stream;

/**
 * A response builder POJO class to work with comment on post API
 *
 * @author Mohit Mehta
 */
public class CommentsResponse {

    private Integer postId;
    private String name;
    private String email;
    private String body;
    private Integer id;

    //Getters methods
    public Integer getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Method to return array version of this class object
     *
     * @return Object array of member variables
     */
    public Object[] getStreamOfObject() {
        return Stream.of(postId, name, email, body).toArray();
    }
}
