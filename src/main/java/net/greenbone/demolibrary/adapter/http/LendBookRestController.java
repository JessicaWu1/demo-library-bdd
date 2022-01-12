package net.greenbone.demolibrary.adapter.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.services.LendBookService;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/lendBook")
@RequiredArgsConstructor
//@PreAuthorize("isAuthenticated()")
@Slf4j
public class LendBookRestController {

    private final LendBookService lendBookService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LendBookResponse lendBook(@RequestBody LendBookRequest lendBookRequest) {
        log.info("RestController LendBook");
        LendBook lendBook = lendBookService.lendingABook(lendBookRequest);
        LendBookResponse lendBookResponse = LendBookResponse.lendBookToLendBookResponse(lendBook);
        log.info("RestController LendBook response " + lendBookResponse);
        return lendBookResponse;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateLendBook(@PathVariable Long id, @RequestBody LendBookRequest lendBook) {
        lendBookService.updateLendBookById(id, lendBook);
    }
}
