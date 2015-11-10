package org.rogotulka.trader.logic;

import android.content.Context;

import org.rogotulka.trader.api.ApiClientProvider;
import org.rogotulka.trader.db.TradereDataSourceProvider;

public class LogicProvider {

    public static Logic getLogic(Context context) {
        return new LogicImpl(ApiClientProvider.getApiClient(), TradereDataSourceProvider.getTraderDataSource(context));
    }
}
