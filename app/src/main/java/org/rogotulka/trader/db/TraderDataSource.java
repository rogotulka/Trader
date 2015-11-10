package org.rogotulka.trader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TraderDataSource {
    private SQLiteDatabase database;
    private TraderDBHelper dbHelper;
    private String[] allColumns = {TraderDBHelper.COLUMN_ID,
            TraderDBHelper.COLUMN_TO_CURRENCY, TraderDBHelper.COLUMN_FROM_CURRENCY, TraderDBHelper.COLUMN_VALUE};

    public TraderDataSource(Context context) {
        dbHelper = new TraderDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public TraderRow createTraderRow(String fromCurrency, String toCurrency, double value) {
        ContentValues values = new ContentValues();
        values.put(TraderDBHelper.COLUMN_FROM_CURRENCY, fromCurrency);
        values.put(TraderDBHelper.COLUMN_TO_CURRENCY, toCurrency);
        values.put(TraderDBHelper.COLUMN_VALUE, value);
        long insertId = database.insert(TraderDBHelper.TABLE_TRADER, null,
                values);
        Cursor cursor = database.query(TraderDBHelper.TABLE_TRADER,
                allColumns, TraderDBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        TraderRow traderRow = cursorToTraderRow(cursor);
        cursor.close();
        return traderRow;
    }

    public void deleteTraderRow(TraderRow traderRow) {
        long id = traderRow.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(TraderDBHelper.TABLE_TRADER, TraderDBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<TraderRow> getAllTraderRows() {
        List<TraderRow> traderRows = new ArrayList<TraderRow>();

        Cursor cursor = database.query(TraderDBHelper.TABLE_TRADER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TraderRow traderRow = cursorToTraderRow(cursor);
            traderRows.add(traderRow);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return traderRows;
    }

    private TraderRow cursorToTraderRow(Cursor cursor) {
        TraderRow traderRow = new TraderRow();
        traderRow.setId(cursor.getLong(0));
        traderRow.setFromCurrency(cursor.getString(1));
        traderRow.setToCurrency(cursor.getString(2));
        traderRow.setValue(cursor.getDouble(3));
        return traderRow;
    }
}
