package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.*;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Bearer -> Auth Header mitsenden!
@Headers("Content-Type: application/json")
public interface BookClient {

    @RequestLine("POST /book/newBook")
    BookResponse createBook(BookRequest bookRequest);

    @RequestLine("GET /book/{id}")
    BookResponse getBookById(@Param(value = "id") Long id);

    @RequestLine("GET /book/author")
    List<BookResponse> getBooksByAuthor(String author);

    @RequestLine("GET /book/title")
    List<BookResponse> getBooksByTitle(String title);

    @RequestLine("GET /book/allBooks")
    List<BookResponse> getBooks();

    @RequestLine("PUT /book/updateBook/{id}")
    Response updateBook(@Param(value ="id") Long id, BookRequest bookRequest);

    @RequestLine("DELETE /book/deleteBook/{id}")
    Response deleteBook(@Param(value ="id") Long id);
}
