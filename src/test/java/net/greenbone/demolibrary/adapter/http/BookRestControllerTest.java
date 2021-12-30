package net.greenbone.demolibrary.adapter.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.BookService;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
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
@WebMvcTest(BookRestController.class)
@Slf4j
public class BookRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc; //Objekt des Testframeworks das die Tests ausführt (repräsentiert ????)

    @Autowired
    protected ObjectMapper objectMapper; //JSON Mapping

    @MockBean
    private BookService bookService;

    private Book book;
    private BookRequest bookRequest;
    private BookResponse bookResponse;
    private String bookRequestJson;

    @Before
    public void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        bookRequest = BookRequest.builder()
                .author("Bestselling Author")
                .publisher("Great Publisher")
                .description("Nice description")
                .publishingYear(2015)
                .quantity(2)
                .title("Greater Book")
                .build();
        book = Book.builder()
                .id(1L)
                .author("Bestselling Author")
                .publisher("Great Publisher")
                .description("Nice description")
                .publishingYear(2015)
                .quantity(2)
                .title("Greater Book")
                .build();
        bookRequestJson = objectMapper.writeValueAsString(bookRequest);
    }

    @Test
    @WithMockUser(username = "user")
    public void expect_getBookById_toReturn() throws Exception {
        when(this.bookService.getBookById(anyLong()))
                .thenReturn(book); //any(), any(klasse) 1x in der methode für den aufruf, dann müssen alle any sein oder eq
        this.mockMvc.perform(
                        get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1")); //$[0].id
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void expect_createNewBook_toReturn() throws Exception {
        when(this.bookService.createNewBook(any(Book.Create.class)))
                .thenReturn(book); //any(), any(klasse) 1x in der methode für den aufruf, dann müssen alle any sein oder eq
        this.mockMvc.perform(
                        post("/book")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(bookRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void expect_updateBook_toReturn() throws Exception {
        when(this.bookService.updateBook(anyLong(), any(Book.Update.class)))
                .thenReturn(true);
        this.mockMvc.perform(
                        put("/book/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(bookRequestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void expect_deleteBokWithID_toReturn() throws Exception {
        when(this.bookService.deleteBookWithId(anyLong()))
                .thenReturn(book);
        this.mockMvc.perform(
                        delete("/book/1")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

}