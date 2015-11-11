package org.rogotulka.trader.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rogotulka.trader.R;
import org.rogotulka.trader.db.TraderInfo;

import java.util.List;

public class TraderAdapter extends RecyclerView.Adapter<TraderAdapter.TraderInfoViewHolder> implements ItemTouchHelperAdapter {

    private List<TraderInfo> mTraderInfoList;
    private Context mContext;

    public TraderAdapter(Context context, List<TraderInfo> traderInfoList) {
        mTraderInfoList = traderInfoList;
        mContext = context;
    }

    @Override
    public TraderInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trade_list, parent, false);
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


    @Override
    public void onItemDismiss(int position) {
        if (mTraderInfoList != null) {
            mTraderInfoList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public static class TraderInfoViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        TextView currency;
        TextView value;

        TraderInfoViewHolder(View itemView) {
            super(itemView);
            currency = (TextView) itemView.findViewById(R.id.currency);
            value = (TextView) itemView.findViewById(R.id.value);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundResource(R.drawable.background_rectangle);
        }
    }


}
