package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.*;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import net.greenbone.demolibrary.representations.response.LendBookResponse;

import java.util.List;
import java.util.Map;

@Headers("Content-Type: application/json")
public interface LendBookClient {
    @RequestLine("POST /lendBook")
    LendBookResponse lendBook(LendBookRequest bookRequest,@HeaderMap Map<String, Object> headerMap);

    @RequestLine("PUT /lendBook/{id}")
    Response updateLendBook(@Param("id") Long id, LendBookRequest bookRequest,@HeaderMap Map<String, Object> headerMap);
}
