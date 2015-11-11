package org.rogotulka.trader.logic;

import org.rogotulka.trader.db.TraderInfo;

import java.util.List;
import java.util.Map;

public interface Logic {

    List<String> getCurrenciesList();

    Map<String, Double> getCurrencyInfo(String fromCurrency, List<String> toCurrency);

    List<TraderInfo> getTradersInfoList();

    void addTraderInfo(String fromCurrency, String toCurrency, Double value);

    void deleteTraderInfo(TraderInfo traderInfo);
}
