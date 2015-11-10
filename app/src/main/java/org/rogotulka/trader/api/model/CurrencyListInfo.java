package org.rogotulka.trader.api.model;

import java.util.Map;

public class CurrencyListInfo {

    private boolean success;
    private Map<String, String> currencies;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, String> currencies) {
        this.currencies = currencies;
    }
}
