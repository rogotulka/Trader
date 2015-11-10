package org.rogotulka.trader.logic;

import org.rogotulka.trader.api.ApiClient;
import org.rogotulka.trader.api.model.CurrencyListInfo;
import org.rogotulka.trader.api.request.CurrencyListRequest;
import org.rogotulka.trader.api.request.CurrencyMatchRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LogicImpl implements Logic {

    private ApiClient mApiClient;

    public LogicImpl(ApiClient apiClient) {
        mApiClient = apiClient;
    }

    @Override
    public List<String> getCurrenciesList() {
        CurrencyListRequest currencyListRequest = new CurrencyListRequest();
        List<String> currencyList = new ArrayList<>();
        try {
            CurrencyListInfo currencyListInfo = mApiClient.execute(currencyListRequest);
            currencyList.addAll(currencyListInfo.getCurrencies().keySet());
        } catch (IOException e) {
            //todo
        }
        return currencyList;
    }

    @Override
    public Map<String, Double> getCurrencyInfo(String fromCurrency, List<String> toCurrency) {
        CurrencyMatchRequest currencyMatchRequest = new CurrencyMatchRequest();
        currencyMatchRequest.setFromCurrency(fromCurrency);
        currencyMatchRequest.setToCurrency(toCurrency);
        Map<String, Double> currencyMap = new HashMap<>();
        try {
            currencyMap = mApiClient.execute(currencyMatchRequest).getQuotes();
        } catch (IOException e) {
            //todo
        }
        return currencyMap;
    }
}
