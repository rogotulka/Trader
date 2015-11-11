package org.rogotulka.trader.logic;

import org.rogotulka.trader.api.ApiClient;
import org.rogotulka.trader.api.model.CurrencyListInfo;
import org.rogotulka.trader.api.request.CurrencyListRequest;
import org.rogotulka.trader.api.request.CurrencyMatchRequest;
import org.rogotulka.trader.db.TraderDataSource;
import org.rogotulka.trader.db.TraderInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LogicImpl implements Logic {

    private ApiClient mApiClient;
    private TraderDataSource mTraderDataSource;

    public LogicImpl(ApiClient apiClient, TraderDataSource traderDataSource) {
        mApiClient = apiClient;
        mTraderDataSource = traderDataSource;
    }

    @Override
    public List<String> getCurrenciesList() throws IOException {
        CurrencyListRequest currencyListRequest = new CurrencyListRequest();
        List<String> currencyList = new ArrayList<>();
        CurrencyListInfo currencyListInfo = mApiClient.execute(currencyListRequest);
        currencyList.addAll(currencyListInfo.getCurrencies().keySet());
        return currencyList;
    }

    @Override
    public Map<String, Double> getCurrencyInfo(String fromCurrency, List<String> toCurrency) throws IOException {
        CurrencyMatchRequest currencyMatchRequest = new CurrencyMatchRequest();
        currencyMatchRequest.setFromCurrency(fromCurrency);
        currencyMatchRequest.setToCurrency(toCurrency);
        Map<String, Double> currencyMap = mApiClient.execute(currencyMatchRequest).getQuotes();
        return currencyMap;
    }

    @Override
    public List<TraderInfo> getTradersInfoList() {
        return mTraderDataSource.getAllTraderRows();
    }

    @Override
    public void addTraderInfo(String fromCurrency, String toCurrency, Double value) {
        mTraderDataSource.createTraderInfo(fromCurrency, toCurrency, value);
    }

    @Override
    public void deleteTraderInfo(TraderInfo traderInfo) {
        mTraderDataSource.deleteTraderRow(traderInfo);
    }
}
