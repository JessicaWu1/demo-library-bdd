package net.greenbone.demolibrary.bdd.helper.adapter.http.client;

import feign.Headers;

@Headers("Content-Type: application/json")
public interface UserClient {
    /*@RequestLine("POST /procedure")
    ProcedureDto createProcedure(ProcedureDto procedureDto);*/
}
