package org.rogotulka.trader.ui;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.rogotulka.trader.R;
import org.rogotulka.trader.TraderApplication;
import org.rogotulka.trader.logic.Logic;

import java.util.List;

public class ChooseCurrencyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private static final int LOADER_CURRENCIES = 0;
    private EditText vSearch;
    private ListView vCurrencyList;
    private ArrayAdapter<String> mAdapter;
    private Toolbar vToolbar;
    private Logic mLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_currency);
        vToolbar = (Toolbar) findViewById(R.id.tool_bar_choose_currency);
        setSupportActionBar(vToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vCurrencyList = (ListView) findViewById(R.id.list_currencies);
        vCurrencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String toCurrency = mAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), AddCurrencyActivity.class);
                intent.putExtra(AddCurrencyActivity.TO_CURRENCY, toCurrency);
                setResult(RESULT_OK, intent);
                finish();
                //startActivity(intent);
            }
        });
        vSearch = (EditText) findViewById(R.id.currency_search);
        vSearch.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ChooseCurrencyActivity.this.mAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
        mLogic = ((TraderApplication) getApplication()).getLogic();
        getLoaderManager().initLoader(LOADER_CURRENCIES, null, this).forceLoad();


    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_CURRENCIES: {
                return new AsyncTaskLoader<List<String>>(getApplicationContext()) {
                    @Override
                    public List<String> loadInBackground() {
                        return mLogic.getCurrenciesList();
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
        switch (loader.getId()) {
            case LOADER_CURRENCIES: {
                if (data != null) {
                    List<String> currencyList = (List<String>) data;
                    mAdapter = new ArrayAdapter<String>(this, R.layout.item_list_currencies, R.id.currency_code, currencyList);
                    vCurrencyList.setAdapter(mAdapter);
                }
                break;
            }
            default:
                throw new IllegalStateException("Unknown loader");
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
