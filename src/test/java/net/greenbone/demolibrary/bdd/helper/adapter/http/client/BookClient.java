package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Headers;
import feign.RequestLine;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.http.ResponseEntity;

@Headers("Content-Type: application/json")
public interface BookClient {
    @RequestLine("POST /book")
    BookResponse createBook(BookRequest bookRequest);
}
