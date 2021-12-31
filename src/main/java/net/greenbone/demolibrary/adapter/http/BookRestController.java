package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.BookService;
import net.greenbone.demolibrary.domain.services.helper.MapperEntityToDto;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    //@PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);

        if(book == null){
            Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to retrieve book information with id: "+id);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        BookResponse bookResponse = MapperEntityToDto.bookToBookResponse(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookResponse);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewBook(@Valid @RequestBody BookRequest book){
        Book createdBook = bookService.createNewBook(book);

        if(createdBook == null){
            Map<String, String> message = Collections.singletonMap("response","An error occurred while trying to create the new book.");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        }

        BookResponse createdBookResponse = MapperEntityToDto.bookToBookResponse(createdBook);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBookResponse);
    }

    //@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest book){
        if(bookService.updateBook(id, book)){
            Map<String, String> message = Collections.singletonMap("response","Successfully updated Book with ID: " + id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(message);
        }
        Map<String, String> message = Collections.singletonMap("response","An error occurred trying to update the book with ID: " + id);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    //@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteBokWithID(@PathVariable Long id){
        Book deletedBook = bookService.deleteBookWithId(id);

        if(deletedBook == null){
            Map<String, String> message = Collections.singletonMap("response","An Error occurred trying to delete the book with ID: " + id);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }
        Map<String, String> message = Collections.singletonMap("response","Successfully removed Book with ID: " + id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(message);
    }

}