package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.helper.MapperEntityToDto;
import net.greenbone.demolibrary.representations.response.BookResponse;
import net.greenbone.demolibrary.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@Slf4j
public class BookRestController {

    private final BookService bookService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return MapperEntityToDto.bookToBookResponse(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //needs to be changed try catch
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createNewBook(@RequestBody Book.Create book){
        Book createdBook = bookService.createNewBook(book);
        return MapperEntityToDto.bookToBookResponse(createdBook);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody Book.Update book){
        try{
            bookService.updateBook(id, book);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NullPointerException nullPointerException){
            log.error(nullPointerException.getMessage(), nullPointerException.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
