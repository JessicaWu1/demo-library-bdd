package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;

import java.util.List;

@Headers("Content-Type: application/json")
public interface LendBookClient {
    @RequestLine("POST /lendBook")
    LendBookResponse lendBook(LendBookRequest bookRequest);

    @RequestLine("PUT /lendBook/{id}")
    Response updateLendBook(@Param("id") Long id, LendBookRequest bookRequest);
}
