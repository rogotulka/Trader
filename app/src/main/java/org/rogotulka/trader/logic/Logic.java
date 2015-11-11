package org.rogotulka.trader.logic;

import org.rogotulka.trader.db.TraderInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Logic {

    List<String> getCurrenciesList() throws IOException;

    Map<String, Double> getCurrencyInfo(String fromCurrency, List<String> toCurrency) throws IOException;

    List<TraderInfo> getTradersInfoList();

    void addTraderInfo(String fromCurrency, String toCurrency, Double value);

    void deleteTraderInfo(TraderInfo traderInfo);
}
