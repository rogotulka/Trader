package org.rogotulka.trader.db;

import android.content.Context;

public class TradereDataSourceProvider {
    public static TraderDataSource getTraderDataSource(Context context) {
        return new TraderDataSource(context);
    }
}
