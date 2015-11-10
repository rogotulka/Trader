package org.rogotulka.trader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class TraderDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_TRADER = "trader";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FROM_CURRENCY = "from_currency";
    public static final String COLUMN_TO_CURRENCY = "to_currency";
    public static final String COLUMN_VALUE = "value";

    private static final String DATABASE_NAME = "trader.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_TRADER + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_FROM_CURRENCY
            + " text not null, " + COLUMN_TO_CURRENCY
            + " text not null, " + COLUMN_VALUE
            + " real not null);";

    public TraderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADER);
        onCreate(db);
    }
}
