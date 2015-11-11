package org.rogotulka.trader.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.rogotulka.trader.R;

public class AddCurrencyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private static final int REQUEST_CODE_CHOOSE = 0;
    private static final String TO_CURRENCY = "to_currency";
    private Toolbar vToolbar;
    private Button vToCurrency;
    private String toCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_currency);
        vToolbar = (Toolbar) findViewById(R.id.tool_bar_pair_currency);
        setSupportActionBar(vToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vToCurrency = (Button) findViewById(R.id.to_add_pair_currency);
        vToCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCurrencyActivity.this, ChooseCurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }

                toCurrency = data.getStringExtra(TO_CURRENCY);
                vToCurrency.setText(toCurrency);

                // getLoaderManager().initLoader(LOADER_CURRENCY_INFO, data.getExtras(), this).forceLoad();
            }
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
