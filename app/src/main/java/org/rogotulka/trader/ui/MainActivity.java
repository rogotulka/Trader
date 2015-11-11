package org.rogotulka.trader.ui;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import org.rogotulka.trader.R;
import org.rogotulka.trader.TraderApplication;
import org.rogotulka.trader.db.TraderInfo;
import org.rogotulka.trader.logic.Logic;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks, OnStartDragListener, DeleteListener {

    public static final int REQUEST_CODE_ADD = 1;
    public static final String FROM_CURRENCY = "from_currency";
    public static final String TO_CURRENCY = "to_currency";
    private static final int LOADER_TRADER_INFO = 0;
    private static final int LOADER_CURRENCY_INFO = 1;

    private static final int LOADER_DELETE_CURRENCY_INFO = 2;
    private static final String TRADER_INFO = "traider_info";
    private Toolbar vToolbar;
    private RecyclerView vRecyclerView;
    private Button vAdd;
    private Logic mLogic;
    private TraderAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_list);
        vToolbar = (Toolbar) findViewById(R.id.tool_bar_trader_list);
        setSupportActionBar(vToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        vRecyclerView = (RecyclerView) findViewById(R.id.trader_info_list);
        vAdd = (Button) findViewById(R.id.add_trader_info);
        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TraderAdapter(this, null, null);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter, this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(vRecyclerView);

        mLogic = ((TraderApplication) getApplication()).getLogic();


        final Handler h = new Handler();
        final int delay = 20000;

        h.post(new Runnable() {
            public void run() {
                getLoaderManager().initLoader(LOADER_TRADER_INFO, null, MainActivity.this).forceLoad();
                getLoaderManager().initLoader(LOADER_CURRENCY_INFO, null, MainActivity.this).forceLoad();
                h.postDelayed(this, delay);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                getLoaderManager().initLoader(LOADER_CURRENCY_INFO, null, this).forceLoad();
                getLoaderManager().initLoader(LOADER_TRADER_INFO, null, this).forceLoad();
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

            case LOADER_DELETE_CURRENCY_INFO: {
                if (args == null) {
                    throw new IllegalStateException("Illegal params for loader LOADER_CURRENCY_INFO");
                }
                return new AsyncTaskLoader<Void>(getApplicationContext()) {
                    @Override
                    public Void loadInBackground() {
                        TraderInfo traderInfo = new TraderInfo();
                        traderInfo = args.getParcelable(TRADER_INFO);
                        mLogic.deleteTraderInfo(traderInfo);
                        return null;
                    }
                };
            }

            case LOADER_CURRENCY_INFO: {
                return new AsyncTaskLoader<Map<String, Double>>(getApplicationContext()) {
                    @Override
                    public Map<String, Double> loadInBackground() {
                        //take common list for all currency
                        return mLogic.getCurrencyInfo(null, null);
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
            case LOADER_TRADER_INFO: {
                if (data != null) {
                    List<TraderInfo> traderInfoList = (List<TraderInfo>) data;
                    mAdapter.setTraderInfoList(traderInfoList);
                    vRecyclerView.setAdapter(mAdapter);

                }
                break;
            }

            case LOADER_DELETE_CURRENCY_INFO: {
                break;
            }

            case LOADER_CURRENCY_INFO: {
                mAdapter.setCurrencyMap((Map<String, Double>) data);
                break;
            }

            default:
                throw new IllegalStateException("Unknown loader");
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {
        //NOP
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void itemDeleted(int pos) {
        TraderInfo traderInfo = mAdapter.getTraderInfo(pos);
        Bundle bundle = null;
        if (traderInfo != null) {
            bundle = new Bundle();
            bundle.putParcelable(TRADER_INFO, traderInfo);
        }

        getLoaderManager().initLoader(LOADER_DELETE_CURRENCY_INFO, bundle, this).forceLoad();
    }
}
