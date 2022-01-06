package net.greenbone.demolibrary.adapter.http;

import feign.Response;
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
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@PreAuthorize("isAuthenticated()")
public class BookRestController {

    private final BookService bookService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);

        BookResponse bookResponse = BookResponse.toBookResponse(book);
        return bookResponse;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooks(){
        List<Book> books = bookService.getBooks();

        List<BookResponse> booksResponse = books.stream()
                .map(BookResponse::toBookResponse)
                .collect(Collectors.toList());
        return booksResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createNewBook(@Valid @RequestBody BookRequest book){
        Book createdBook = bookService.createNewBook(book);

        BookResponse createdBookResponse = BookResponse.toBookResponse(createdBook);
        return createdBookResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest book){
        bookService.updateBook(id, book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookWithID(@PathVariable Long id){
        bookService.deleteBookWithId(id);
    }

}