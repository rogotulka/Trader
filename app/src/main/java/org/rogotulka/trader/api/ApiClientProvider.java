package org.rogotulka.trader.api;

public class ApiClientProvider {

    public static ApiClient getApiClient() {
        return new ApiClientImpl();
    }
}
