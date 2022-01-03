package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.*;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Headers("Content-Type: application/json")
public interface BookClient {
    @RequestLine("POST /book")
    BookResponse createBook(BookRequest bookRequest);

    @RequestLine("GET /book/{id}")
    BookResponse getBookById(@Param("id") Long id);

    @RequestLine("GET /book")
    List<BookResponse> getBooks();

    @RequestLine("PUT /book/{id}")
    Response updateBook(@Param("id") Long id, BookRequest bookRequest);

    @RequestLine("DELETE /book/{id}")
    Response deleteBook(@Param("id") Long id);
}
