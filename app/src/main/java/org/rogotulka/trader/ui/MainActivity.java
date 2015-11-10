package org.rogotulka.trader.ui;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.rogotulka.trader.R;
import org.rogotulka.trader.TraderApplication;
import org.rogotulka.trader.db.TraderInfo;
import org.rogotulka.trader.logic.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    public static final int REQUEST_CODE_ADD = 1;
    private static final int LOADER_TRADER_INFO = 0;
    private static final int LOADER_CURRENCY_INFO = 1;
    public static final String FROM_CURRENCY = "from_currency";
    public static final String TO_CURRENCY = "to_currency";

    private Toolbar vToolbar;
    private RecyclerView vRecyclerView;
    private Button vAdd;
    private Logic mLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(vToolbar);

        vRecyclerView = (RecyclerView) findViewById(R.id.trader_info_list);
        vAdd = (Button) findViewById(R.id.add_trader_info);
        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseCurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(LOADER_TRADER_INFO, null, this).forceLoad();

        mLogic = ((TraderApplication) getApplication()).getLogic();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                String fromCurrency = data.getStringExtra(FROM_CURRENCY);
                String toCurrency = data.getStringExtra(TO_CURRENCY);

                getLoaderManager().initLoader(LOADER_CURRENCY_INFO, data.getExtras(), this).forceLoad();
            }
        }
    }

    @Override
    public Loader onCreateLoader(int id, final Bundle args) {

        switch (id) {
            case LOADER_TRADER_INFO: {
                return new AsyncTaskLoader<List<TraderInfo>>(getApplicationContext()) {
                    @Override
                    public List<TraderInfo> loadInBackground() {

                        return mLogic.getTradersInfoList();
                    }
                };
            }

            case LOADER_CURRENCY_INFO: {
                if (args != null) {
                    throw new IllegalStateException("Illegal params for loader LOADER_CURRENCY_INFO");
                }
                return new AsyncTaskLoader<Map<String, Double>>(getApplicationContext()) {
                    @Override
                    public Map<String, Double> loadInBackground() {
                        List<String> toCurrencyList = new ArrayList<>();
                        toCurrencyList.add(args.getString(TO_CURRENCY));
                        return mLogic.getCurrencyInfo(args.getString(FROM_CURRENCY), toCurrencyList);
                    }
                };
            }
            default: {
                throw new IllegalStateException("Unknown loader");
            }
        }


    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (data != null) {
            List<TraderInfo> traderInfoList = (List<TraderInfo>) data;
            vRecyclerView.setAdapter(new TraderAdapter(this, traderInfoList));
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        //NOP
    }
}
