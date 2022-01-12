package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.*;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

//Bearer -> Auth Header mitsenden!
@Headers("Content-Type: application/json")
public interface BookClient {

    @RequestLine("POST /book")
    BookResponse createBook(BookRequest bookRequest);

    @RequestLine("GET /book/{id}")
    BookResponse getBookById(@Param(value = "id") Long id);

    @RequestLine("GET /book")
    List<BookResponse> getBooks();

    @RequestLine("PUT /book/{id}")
    Response updateBook(@Param(value ="id") Long id, BookRequest bookRequest);

    @RequestLine("DELETE /book/{id}")
    Response deleteBook(@Param(value ="id") Long id);
}
