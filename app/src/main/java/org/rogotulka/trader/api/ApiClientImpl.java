package org.rogotulka.trader.api;

import org.rogotulka.trader.api.request.CurrencyMatchRequest;
import org.rogotulka.trader.api.request.Request;
import org.rogotulka.trader.model.CurrencyInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApiClientImpl implements ApiClient {

    private static final String API_KEY = "cf1b96b3ecabff5d3734c60d0a77399d";

    private Map<Class<? extends Request<?>>, RequestExecutor> mMap = new HashMap<>();

    public ApiClientImpl() {

        mMap.put(CurrencyMatchRequest.class, new RequestExecutor<CurrencyMatchRequest, CurrencyInfo>() {

            @Override
            public CurrencyInfo execute(CurrencyMatchRequest request) throws IOException {
                return null;
            }
        });
    }

    @SuppressWarnings({"SuspiciousMethodCalls", "unchecked"})
    @Override
    public <ResponseType> ResponseType execute(Request<ResponseType> request) throws IOException {
        if (!mMap.containsKey(request.getClass())) {
            throw new UnsupportedOperationException("Request is not supported or implemented: " + request.getClass().getCanonicalName());
        }
        return (ResponseType) mMap.get(request.getClass()).execute(request);
    }

}
