package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.BookService;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@PreAuthorize("isAuthenticated()")
public class BookRestController {

    private final BookService bookService;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);

        BookResponse bookResponse = BookResponse.toBookResponse(book);
        return bookResponse;
    }

    @GetMapping(value = "/allBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks(){
        List<Book> books = bookService.getBooks();

        List<BookResponse> booksResponse = books.stream()
                .map(BookResponse::toBookResponse)
                .collect(Collectors.toList());
        return booksResponse;
    }

    @GetMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooksByTitle(@RequestBody String title){
        List<Book> books = bookService.findAllByTitleContaining(title);

        List<BookResponse> booksResponse = books.stream()
                .map(BookResponse::toBookResponse)
                .collect(Collectors.toList());
        return booksResponse;
    }

    @GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooksByAuthor(@RequestBody String author){
        List<Book> books = bookService.findAllByAuthor(author);

        List<BookResponse> booksResponse = books.stream()
                .map(BookResponse::toBookResponse)
                .collect(Collectors.toList());
        log.info("BookResponse: "+booksResponse.toString());
        return booksResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/newBook",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createNewBook(@Valid @RequestBody BookRequest book){
        Book createdBook = bookService.createNewBook(book);

        BookResponse createdBookResponse = BookResponse.toBookResponse(createdBook);
        return createdBookResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/updateBook/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest book){
        bookService.updateBook(id, book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/deleteBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookWithID(@PathVariable Long id){
        bookService.deleteBookWithId(id);
    }

}