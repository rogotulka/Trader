package org.rogotulka.trader;

import android.test.AndroidTestCase;

import org.rogotulka.trader.api.ApiClient;
import org.rogotulka.trader.api.ApiClientProvider;
import org.rogotulka.trader.api.model.CurrencyInfo;
import org.rogotulka.trader.api.model.CurrencyListInfo;
import org.rogotulka.trader.api.request.CurrencyListRequest;
import org.rogotulka.trader.api.request.CurrencyMatchRequest;

import java.io.IOException;

public class ApiClientImplTest  extends AndroidTestCase {

    public void testApiClientCurrencyMatchRequestWithoutParams() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        CurrencyMatchRequest currencyMatchRequest = new CurrencyMatchRequest();
        CurrencyInfo currencyInfo = apiClient.execute(currencyMatchRequest);
        assertNotNull(currencyInfo);
        assertNotNull(currencyInfo.getQuotes());
        assertNotNull(currencyInfo.getDate());
        assertNotNull(currencyInfo.isSuccess());
        assertNotNull(currencyInfo.getSource());
    }

    public void testApiClientCurrencyListRequest() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        CurrencyListRequest currencyListRequest = new CurrencyListRequest();
        CurrencyListInfo currencyListInfo = apiClient.execute(currencyListRequest);
        assertNotNull(currencyListInfo);
        assertNotNull(currencyListInfo.isSuccess());
        assertNotNull(currencyListInfo.getCurrencies());
    }
}
