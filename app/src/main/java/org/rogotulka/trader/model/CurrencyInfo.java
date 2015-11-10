package org.rogotulka.trader.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class CurrencyInfo {

    private boolean success;
    @SerializedName("timestamp")
    private Date date;
    private String source;
    private Map<String, Double> quotes;
}
