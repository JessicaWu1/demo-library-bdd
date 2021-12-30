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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    //@PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBookById(@PathVariable Long id){
        try{
            Book book = bookService.getBookById(id);
            BookResponse bookResponse = MapperEntityToDto.bookToBookResponse(book);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookResponse);
        }catch(NullPointerException nullPointerException){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("An error occurred while trying to retrieve book information with id: "+id);
        }


    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewBook(@Valid @RequestBody BookRequest book){
        Book createdBook = bookService.createNewBook(book);
        if(createdBook != null){
            log.info("createdBook not null");
            BookResponse createdBookResponse = MapperEntityToDto.bookToBookResponse(createdBook);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdBookResponse);
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while trying to create the new book.");
    }

    //@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest book){
        if(bookService.updateBook(id, book)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successfully updated Book with ID: " + id);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("An error occurred trying to update the book with ID: " + id);
    }

    //@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBokWithID(@PathVariable Long id){
        Book deletedBook = bookService.deleteBookWithId(id);
        if(deletedBook != null){
            BookResponse deletedBookResponse = MapperEntityToDto.bookToBookResponse(deletedBook);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(deletedBookResponse);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("An Error occurred trying to delete the book with ID: " + id);
    }

}