package org.rogotulka.trader.db;

import android.os.Parcel;
import android.os.Parcelable;

public class TraderInfo implements Parcelable {

    private long id;
    private String fromCurrency;
    private String toCurrency;
    private double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TraderInfo() {
    }

    private TraderInfo(Parcel in) {
        id = in.readInt();
        fromCurrency = in.readString();
        toCurrency = in.readString();
        value = in.readDouble();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(fromCurrency);
        dest.writeString(toCurrency);
        dest.writeDouble(value);
    }

    public static final Parcelable.Creator<TraderInfo> CREATOR
            = new Parcelable.Creator<TraderInfo>() {
        @Override
        public TraderInfo createFromParcel(Parcel in) {
            return new TraderInfo(in);
        }

        @Override
        public TraderInfo[] newArray(int size) {
            return new TraderInfo[size];
        }
    };
}
