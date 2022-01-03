package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Headers("Content-Type: application/json")
public interface BookClient {
    @RequestLine("POST /book")
    BookResponse createBook(BookRequest bookRequest);

    @RequestLine("GET /book/{id}")
    BookResponse getBookById(@Param("id") Long id);

    @RequestLine("PUT /book/{id}")
    void updateBook(@Param("id") Long id, BookRequest bookRequest);

    @RequestLine("DELETE /book/{id}")
    void deleteBook(@Param("id") Long id);
}
