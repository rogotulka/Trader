package org.rogotulka.trader.logic;

import org.rogotulka.trader.api.ApiClientProvider;

public class LogicProvider {

    public static Logic getLogic() {
        return new LogicImpl(ApiClientProvider.getApiClient());
    }
}
