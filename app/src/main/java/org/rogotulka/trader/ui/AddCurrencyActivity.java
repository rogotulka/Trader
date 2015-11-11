package org.rogotulka.trader.ui;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.rogotulka.trader.R;
import org.rogotulka.trader.TraderApplication;
import org.rogotulka.trader.logic.Logic;

public class AddCurrencyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private static final int REQUEST_CODE_CHOOSE = 0;
    public static final String TO_CURRENCY = "to_currency";
    private static final int LOADER_ADD_PAIR_CURRENCY = 0;
    private Toolbar vToolbar;
    private Button vToCurrency;
    private Button vSavePairCurrency;
    private TextView vFromCurrency;
    private String mToCurrency;
    private String mFromCurrency = "USD";
    private Logic mLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_currency);
        vToolbar = (Toolbar) findViewById(R.id.tool_bar_pair_currency);
        setSupportActionBar(vToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vFromCurrency = (TextView) findViewById(R.id.from_currency);
        vFromCurrency.setText(mFromCurrency);
        vToCurrency = (Button) findViewById(R.id.to_currency);
        vToCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCurrencyActivity.this, ChooseCurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE);
            }
        });
        vSavePairCurrency = (Button) findViewById(R.id.save_pair_currency);
        vSavePairCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLogic = ((TraderApplication) getApplication()).getLogic();
        getLoaderManager().initLoader(LOADER_ADD_PAIR_CURRENCY, null, this).forceLoad();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }

                mToCurrency = data.getStringExtra(TO_CURRENCY);
                vToCurrency.setText(mToCurrency);

                // getLoaderManager().initLoader(LOADER_CURRENCY_INFO, data.getExtras(), this).forceLoad();
            }
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ADD_PAIR_CURRENCY: {
                return new AsyncTaskLoader<Void>(getApplicationContext()) {
                    @Override
                    public Void loadInBackground() {
                        mLogic.addTraderInfo(mFromCurrency, mToCurrency, null);
                        return null;
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

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
