package org.rogotulka.trader.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rogotulka.trader.R;
import org.rogotulka.trader.db.TraderInfo;

import java.util.List;

public class TraderAdapter extends RecyclerView.Adapter<TraderAdapter.TraderInfoViewHolder> {

    private List<TraderInfo> mTraderInfoList;
    private Context mContext;

    public TraderAdapter(Context context, List<TraderInfo> traderInfoList) {
        mTraderInfoList = traderInfoList;
        mContext = context;
    }

    @Override
    public TraderInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trade_list, null);

        TraderInfoViewHolder viewHolder = new TraderInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TraderInfoViewHolder holder, int position) {
        TraderInfo traderInfo = mTraderInfoList.get(position);
        holder.currency.setText(traderInfo.getFromCurrency() + "=>" + traderInfo.getToCurrency());
        holder.value.setText(Double.toString(traderInfo.getValue()));
    }

    @Override
    public int getItemCount() {
        return (null != mTraderInfoList ? mTraderInfoList.size() : 0);
    }

    public static class TraderInfoViewHolder extends RecyclerView.ViewHolder {
        TextView currency;
        TextView value;

        TraderInfoViewHolder(View itemView) {
            super(itemView);
            currency = (TextView) itemView.findViewById(R.id.currency);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }


}
