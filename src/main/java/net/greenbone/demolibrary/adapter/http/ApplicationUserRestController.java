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

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ApplicationUserRestController {
    private final ApplicationUserService applicationUserService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        try{
            ApplicationUser applicationUser = applicationUserService.getUserById(id);
            UserResponse userResponse = MapperEntityToDto.applicationUserToUserResponse(applicationUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        }catch(NullPointerException nullPointerException){
            Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to retrieve user information for user with id: "+id);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewUser(@Valid @RequestBody UserRequest user){
        ApplicationUser createdUser = applicationUserService.createNewUser(user);
        if(createdUser != null){
            UserResponse createdUserResponse = MapperEntityToDto.applicationUserToUserResponse(createdUser);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdUserResponse);
        }
        Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to create the new user.");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest user){
        if(applicationUserService.updateUser(id, user)){
            Map<String, String> message = Collections.singletonMap("response","Successfully updated User with ID: " + id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(message);
        }
        Map<String, String> message = Collections.singletonMap("response","An error occurred trying to update the user with ID: " + id);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserWithID(@PathVariable Long id){
        ApplicationUser deletedUser = applicationUserService.deleteUserWithId(id);
        if(deletedUser != null){
            Map<String, String> message = Collections.singletonMap("response","Successfully removed User with ID: " + id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(message);
        }
        Map<String, String> message = Collections.singletonMap("response","An Error occurred trying to delete the user with ID: " + id);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}
