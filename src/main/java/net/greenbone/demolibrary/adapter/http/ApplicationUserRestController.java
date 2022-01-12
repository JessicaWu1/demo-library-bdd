package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.services.ApplicationUserService;
import net.greenbone.demolibrary.representations.request.UserRequest;
import net.greenbone.demolibrary.representations.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@Slf4j
@PreAuthorize("isAuthenticated()")
public class ApplicationUserRestController {
    private final ApplicationUserService applicationUserService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id){
        ApplicationUser applicationUser = applicationUserService.getUserById(id);
        return UserResponse.applicationUserToUserResponse(applicationUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createNewUser(@Valid @RequestBody UserRequest user){
        ApplicationUser createdUser = applicationUserService.createNewUser(user);
        log.warn("CreatedUserResponse in Request: "+createdUser);
        UserResponse createdUserResponse = UserResponse.applicationUserToUserResponse(createdUser);
        log.warn("CreatedUserResponse in Request: "+createdUserResponse);
        return createdUserResponse;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest user){
        applicationUserService.updateUser(id, user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserWithID(@PathVariable Long id){
        applicationUserService.deleteUserWithId(id);
    }
}
