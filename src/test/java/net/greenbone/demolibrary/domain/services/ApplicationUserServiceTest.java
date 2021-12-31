package net.greenbone.demolibrary.domain.services;

import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;
import net.greenbone.demolibrary.representations.request.UserRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUserServiceTest {

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @Mock
    private ApplicationUser applicationUser;
    @Mock
    private UserRequest userRequest;


    @Before
    public void setUp(){
        List<Long> borrowedBooks = new ArrayList<>();
        userRequest = UserRequest.builder()
                .name("Toller Name")
                .email("maxmustermann@example.com")
                .password("password")
                .role("ADMIN")
                //.borrowedBooks(borrowedBooks)
                .build();
    }

    @Test
    @WithMockUser(username = "user")
    public void expect_getUserById_toReturn() {
        when(applicationUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(applicationUser));

        ApplicationUser result = applicationUserService.getUserById(1L);

        assertThat(applicationUser, Matchers.is(result));
    }

    @Test
    public void expect_createNewUser_toReturn() {
        when(applicationUserRepository.save(any(ApplicationUser.class)))
                .thenReturn(applicationUser);

        ApplicationUser result = applicationUserService.createNewUser(userRequest);

        assertThat(applicationUser, Matchers.is(result));
    }

    @Test
    public void expect_updateUser_toReturn() {
        /*when(applicationUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(applicationUser));
        applicationUserService.updateUser(1L,userRequest);*/
        //assertThat(true, Matchers.is(result));
    }

    @Test
    public void expect_deleteUserWithId_toReturn() {
        /*when(applicationUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(applicationUser));
        ApplicationUser result = applicationUserService.deleteUserWithId(1L);
        assertNotNull(result);
        assertThat(applicationUser, Matchers.is(result));*/
    }
}