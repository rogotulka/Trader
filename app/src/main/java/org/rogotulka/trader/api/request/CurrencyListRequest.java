package org.rogotulka.trader.api.request;

import java.util.Set;

public class CurrencyListRequest implements Request<Set<String>> {

    @Override
    public String toString() {
        return "CurrencyListRequest{}";
    }
}
