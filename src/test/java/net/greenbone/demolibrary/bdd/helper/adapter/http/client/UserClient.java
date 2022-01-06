package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.*;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.UserResponse;

import java.util.Map;

public interface UserClient {
    @RequestLine("POST /user")
    UserResponse createUser(UserRequest userRequest,@HeaderMap Map<String, Object> headerMap);

    @RequestLine("GET /user/{id}")
    UserResponse getUserById(@Param("id") Long id,@HeaderMap Map<String, Object> headerMap);

    @RequestLine("PUT /user/{id}")
    Response updateUser(@Param("id") Long id, UserRequest bookRequest,@HeaderMap Map<String, Object> headerMap);

    @RequestLine("DELETE /user/{id}")
    Response deleteUser(@Param("id") Long id,@HeaderMap Map<String, Object> headerMap);
}
