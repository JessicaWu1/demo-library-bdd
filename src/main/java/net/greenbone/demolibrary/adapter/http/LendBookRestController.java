package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.services.LendBookService;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/lendBook")
@RequiredArgsConstructor
public class LendBookRestController {

    private final LendBookService lendBookService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LendBookResponse getLendBookById(@PathVariable Long id){
        LendBook lendBook = lendBookService.getLendBookById(id);
        LendBookResponse lendBookResponse = LendBookResponse.lendBookToLendBookResponse(lendBook);
        return lendBookResponse;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LendBookResponse lendBook(@Valid @RequestBody LendBookRequest lendBookRequest){
        LendBook lendBook = lendBookService.lendingABook(lendBookRequest);
        LendBookResponse lendBookResponse = LendBookResponse.lendBookToLendBookResponse(lendBook);

        return lendBookResponse;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateLendBook(@PathVariable Long id, @RequestBody LendBookRequest lendBook){
        lendBookService.updateLendBookById(id, lendBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLendBook(@PathVariable Long id){
        lendBookService.deleteLendBook(id);
    }

    @ExceptionHandler({NoSuchElementException.class, NullPointerException.class, Exception.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handle(Exception exception){
        Map<String, String> message = Collections.singletonMap("response", exception.getMessage());

        if(exception instanceof NoSuchElementException || exception instanceof NullPointerException){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }
}
