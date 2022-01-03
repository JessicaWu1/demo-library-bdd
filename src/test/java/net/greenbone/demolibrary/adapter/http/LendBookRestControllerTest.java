package net.greenbone.demolibrary.adapter.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.enums.Role;
import net.greenbone.demolibrary.domain.services.LendBookService;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
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

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(LendBookRestController.class)
@Slf4j
public class LendBookRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc; //Objekt des Testframeworks das die Tests ausführt (repräsentiert ????)
    @Autowired
    protected ObjectMapper objectMapper; //JSON Mapping

    @MockBean
    private LendBookService lendBookService;

    private LendBook lendBook;
    private LendBookRequest lendBookRequest;
    private LendBookResponse lendBookResponse;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        lendBookRequest = LendBookRequest.builder()
                .bookId(1L)
                .userId(2L)
                .returnDate(new Date())
                .returned(false)
                .build();
        Book book = Book.builder()
                .id(1L)
                .author("great")
                .publishingYear(2015)
                .quantity(1)
                .publisher("new pub")
                .description("greatest book")
                .title("Title")
                .build();
        ApplicationUser user = ApplicationUser.builder()
                .id(2L)
                .name("Max Muster")
                .lateFees(0.0F)
                .borrowedBooks(new ArrayList<>())
                .role(Role.USER)
                .password("password")
                .email("maxmuster@example.com")
                .build();
        lendBook = LendBook.builder()
                .id(1L)
                .book(book)
                .applicationUser(user)
                .returned(false)
                .returnDate(new Date())
                .build();
        lendBookResponse = LendBookResponse.lendBookToLendBookResponse(lendBook);
    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    public void expect_getLendBookById_toReturn() throws Exception {
        when(lendBookService.getLendBookById(1L))
                .thenReturn(lendBook);

        this.mockMvc.perform(
                        get("/lendBook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    public void expect_lendBook_toReturn() throws Exception {
        when(lendBookService.lendingABook(any(LendBook.Create.class)))
                .thenReturn(lendBook);
        String lendBookJson = objectMapper.writeValueAsString(lendBookRequest);

        this.mockMvc.perform(
                        post("/lendBook")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(lendBookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    public void expect_updateLendBook_toReturn() throws Exception {
        String lendBookJson = objectMapper.writeValueAsString(lendBookRequest);
        this.mockMvc.perform(
                        put("/lendBook/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(lendBookJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    public void expect_deleteLendBook_toReturn() throws Exception {
        this.mockMvc.perform(
                        delete("/lendBook/1"))
                .andExpect(status().isOk());
    }
}