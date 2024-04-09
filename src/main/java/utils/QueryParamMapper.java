package utils;

import http.QueryParams;
import model.User;

public class QueryParamMapper {
    public static User toUser(QueryParams queryParams) {
        return new User(queryParams.get("userId"),
                queryParams.get("password"),
                queryParams.get("name"),
                queryParams.get("email"));
    }
}
