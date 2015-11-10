package org.rogotulka.trader;

import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

import org.rogotulka.trader.api.ApiClient;
import org.rogotulka.trader.api.ApiClientProvider;
import org.rogotulka.trader.api.request.CurrencyMatchRequest;
import org.rogotulka.trader.model.CurrencyInfo;

import java.io.IOException;
import java.util.List;

public class ApiClientImplTest  extends AndroidTestCase {

    public void testApiClientCurrencyMatchRequestWithoutParams() throws IOException, InterruptedException {
        ApiClient apiClient = ApiClientProvider.getApiClient();
        CurrencyMatchRequest currencyMatchRequest = new CurrencyMatchRequest();
        CurrencyInfo currencyInfo = apiClient.execute(currencyMatchRequest);
        assertNotNull(currencyInfo);
        assertNotNull(currencyInfo.);
    }
}
