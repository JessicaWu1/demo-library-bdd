package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.BookService;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);

        BookResponse bookResponse = BookResponse.toBookResponse(book);
        return bookResponse;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewBook(@Valid @RequestBody BookRequest book){
        Book createdBook = bookService.createNewBook(book);

        if(createdBook == null){
            Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to create the new book.");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }

        BookResponse createdBookResponse = BookResponse.toBookResponse(createdBook);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBookResponse);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest book){
        bookService.updateBook(id, book);
    }

    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookWithID(@PathVariable Long id){
        bookService.deleteBookWithId(id);
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