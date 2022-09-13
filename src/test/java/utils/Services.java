package utils;

import java.util.EnumMap;
import java.util.Map;

/**
 * Service enum to maintain all the paths & endpoints
 */
public enum Services {
    GetAllUsers,
    GetAnUser,
    MakeAPost,
    CommentOnAPost;

    public static Map<Services, String> servicePath = new EnumMap<>(Services.class);

    static {
        servicePath.put(GetAllUsers, "/users");
        servicePath.put(GetAnUser, "/users/%s");
        servicePath.put(MakeAPost, "/posts");
        servicePath.put(CommentOnAPost, "/comments");
    }
}
