package net.greenbone.demolibrary.domain.services;

import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUserServiceTest {

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Mock
    private ApplicationUserRepository applicationUserRepository;


    @Test
    public void expect_getUserById_toReturn() {
    }

    @Test
    public void expect_createNewUser_toReturn() {
    }

    @Test
    public void expect_updateUser_toReturn() {
    }

    @Test
    public void expect_deleteUserWithId_toReturn() {
    }
}