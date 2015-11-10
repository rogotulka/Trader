package org.rogotulka.trader.api;

import org.rogotulka.trader.api.request.Request;

import java.io.IOException;

public interface ApiClient {

    <ResponseType> ResponseType execute(Request<ResponseType> request) throws IOException;
}
