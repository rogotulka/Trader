package org.rogotulka.trader.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class CurrencyInfo {

    private boolean success;
    @SerializedName("timestamp")
    private Date date;
    private String source;
    private Map<String, Double> quotes;

    public Map<String, Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, Double> quotes) {
        this.quotes = quotes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
