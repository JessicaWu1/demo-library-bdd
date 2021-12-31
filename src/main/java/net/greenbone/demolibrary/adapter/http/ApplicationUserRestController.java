package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.services.ApplicationUserService;
import net.greenbone.demolibrary.domain.services.helper.MapperEntityToDto;
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
public class ApplicationUserRestController {
    private final ApplicationUserService applicationUserService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable Long id){
        ApplicationUser applicationUser = applicationUserService.getUserById(id);

        UserResponse userResponse = UserResponse.applicationUserToUserResponse(applicationUser);
        return userResponse;
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewUser(@Valid @RequestBody UserRequest user){
        ApplicationUser createdUser = applicationUserService.createNewUser(user);

        if(createdUser == null){
            Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to create the new user.");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }

        UserResponse createdUserResponse = UserResponse.applicationUserToUserResponse(createdUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUserResponse);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest user){
        applicationUserService.updateUser(id, user);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserWithID(@PathVariable Long id){
        applicationUserService.deleteUserWithId(id);
    }

    //exceptions, die bis zum controller gereicht werden, werden hier behandelt -> try catch aus den services raus und hier die überprüfungen auch
    @ExceptionHandler({NoSuchElementException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handle(Exception exception){
        Map<String, String> message = Collections.singletonMap("response", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}
