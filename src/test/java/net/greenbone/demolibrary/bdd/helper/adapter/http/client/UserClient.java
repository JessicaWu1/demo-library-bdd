package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.UserResponse;

import java.util.Map;

@Headers("Content-Type: application/json")
public interface UserClient {
    @RequestLine("POST /user")
    UserResponse createUser(UserRequest userRequest);

    @RequestLine("GET /user/{id}")
    UserResponse getUserById(@Param("id") Long id);

    @RequestLine("PUT /user/{id}")
    void updateUser(@Param("id") Long id, UserRequest bookRequest);

    @RequestLine("DELETE /user/{id}")
    void deleteUser(@Param("id") Long id);
}
