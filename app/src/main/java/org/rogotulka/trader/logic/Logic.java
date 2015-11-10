package org.rogotulka.trader.logic;

import java.util.List;
import java.util.Map;

public interface Logic {

    List<String> getCurrenciesList();

    Map<String, Double> getCurrencyInfo(String fromCurrency, List<String> toCurrency);
}
