package org.rogotulka.trader.api.request;

import org.rogotulka.trader.model.CurrencyInfo;

import java.util.List;

public class CurrencyMatchRequest implements Request<CurrencyInfo> {

    private String fromCurrency;

    private List<String> toCurrency;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public List<String> getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(List<String> toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyMatchRequest that = (CurrencyMatchRequest) o;

        if (fromCurrency != null ? !fromCurrency.equals(that.fromCurrency) : that.fromCurrency != null)
            return false;
        return !(toCurrency != null ? !toCurrency.equals(that.toCurrency) : that.toCurrency != null);

    }

    @Override
    public int hashCode() {
        int result = fromCurrency != null ? fromCurrency.hashCode() : 0;
        result = 31 * result + (toCurrency != null ? toCurrency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyMatchRequest{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency=" + toCurrency +
                '}';
    }
}
