package org.rogotulka.trader.api;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.rogotulka.trader.api.request.CurrencyMatchRequest;
import org.rogotulka.trader.api.request.Request;
import org.rogotulka.trader.model.CurrencyInfo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class ApiClientImpl implements ApiClient {

    private static final String URL = "apilayer.net";

    private static final String API_KEY = "cf1b96b3ecabff5d3734c60d0a77399d";

    private Map<Class<? extends Request<?>>, RequestExecutor> mMap = new HashMap<>();

    public ApiClientImpl() {

        mMap.put(CurrencyMatchRequest.class, new RequestExecutor<CurrencyMatchRequest, CurrencyInfo>() {

            @Override
            public CurrencyInfo execute(CurrencyMatchRequest request) throws IOException {
                return getCurrencyInfo(request);
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

    private CurrencyInfo getCurrencyInfo(CurrencyMatchRequest request) {
        CurrencyInfo currencyInfo = new CurrencyInfo();
        if (request == null) {
            return currencyInfo;
        }

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(URL)
                .appendPath("api")
                .appendPath("live")
                .appendQueryParameter("access_key", API_KEY);

        try {
            InputStream response = Network.getInputStream(builder.build().toString());
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });
            Gson gson = gsonBuilder
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            currencyInfo = gson.fromJson(Utils.getStringFromInputStream(response), CurrencyInfo.class);
        } catch (IOException e) {
            //NOP
        } finally {
            Network.close();
        }

        return currencyInfo;
    }
}
