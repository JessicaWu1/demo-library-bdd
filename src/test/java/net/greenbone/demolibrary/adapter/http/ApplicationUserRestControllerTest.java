package net.greenbone.demolibrary.adapter.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;
import net.greenbone.demolibrary.domain.services.ApplicationUserService;
import net.greenbone.demolibrary.representations.request.UserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationUserRestController.class)
@Slf4j
public class ApplicationUserRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc; //Objekt des Testframeworks das die Tests ausführt (repräsentiert ????)

    @Autowired
    protected ObjectMapper objectMapper; //JSON Mapping

    @MockBean
    private ApplicationUserService applicationUserService;

    private UserRequest userRequest;
    private ApplicationUser applicationUser;
    private String userRequestJson;

    @Before
    public void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        userRequest = UserRequest.builder()
                .name("Toller Name")
                .email("maxmustermann@gmail.com")
                .password("password")
                .role("ADMIN")
                .build();
        applicationUser = ApplicationUser.builder()
                .name("Toller Name")
                .email("maxmustermann@gmail.com")
                .password("password")
                .role(Role.valueOf("ADMIN"))
                .build();
        applicationUser.setId(1L);
        userRequestJson = objectMapper.writeValueAsString(userRequest);

    }

    @Test
    @WithMockUser(username = "user")
    public void expect_getUserById_toReturn() throws Exception {
        when(applicationUserService.getUserById(anyLong()))
                .thenReturn(applicationUser);
        this.mockMvc.perform(
                        get("/user/1")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Toller Name"))
                .andExpect(jsonPath("$.email").value("maxmustermann@gmail.com"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void expect_createNewUser_toReturn() throws Exception {
        when(applicationUserService.createNewUser(any(ApplicationUser.Create.class)))
                .thenReturn(applicationUser);
        this.mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(userRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Toller Name"))
                .andExpect(jsonPath("$.email").value("maxmustermann@gmail.com"))
                .andExpect(jsonPath("$.password").value("password"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void expect_updateUser_toReturn() throws Exception {
        this.mockMvc.perform(
                        put("/user/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(userRequestJson)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.response").value("Successfully updated User with ID: 1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void expect_deleteUserWithID_toReturn() throws Exception {
        this.mockMvc.perform(
                        delete("/user/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(userRequestJson)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.response").value("Successfully removed User with ID: 1"));
    }
}