package ResponseBuilders.Posts;

import java.util.stream.Stream;

/**
 * A response builder POJO class to work with make a post API
 *
 * @author Mohit Mehta
 */
public class PostsResponse {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    //Getters methods
    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
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
        return Stream.of(userId, title, body).toArray();
    }
}
