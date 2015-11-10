package org.rogotulka.trader;

import android.app.Application;

import org.rogotulka.trader.logic.Logic;
import org.rogotulka.trader.logic.LogicProvider;

public class TraderApplication extends Application {
    private Logic mLogic;

    public Logic getLogic() {
        return mLogic;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLogic = LogicProvider.getLogic(this);
    }
}
