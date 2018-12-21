package com.jappy.mercadopago.presetation.trade;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseListAdapter;
import com.jappy.mercadopago.base.BaseViewHolder;
import com.jappy.mercadopago.base.OnItemClickListener;
import com.jappy.mercadopago.databinding.ItemTradeBinding;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.presetation.card.CardListsAdapter.CardViewHolder;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TradeListAdapter extends BaseListAdapter<Object, CardViewHolder> {
    private OnItemClickListener<Trade> listener;

    @Override
    protected int getLayoutIdByType(final int viewType) {
        return R.layout.item_trade;
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new TradeListAdapter.CardViewHolder(v);
    }

    public void initializeList(List<Trade> item) {

        list.clear();
        list.addAll(item);
        notifyDataSetChanged();
    }

    public class CardViewHolder extends BaseViewHolder<Object, ItemTradeBinding> {
        View view;

        CardViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        @Override
        public void bind(int position, Object item) {
            Trade mitem = (Trade) item;

            binder.amountTextView.setText(getContext().getString(R.string.trade_amount_tittle,mitem.getAmount()));
            binder.dateTextView.setText(mitem.getDate());
            binder.paymentTextView.setText(getContext().getString(R.string.trade_payment_tittle,mitem.getPayment()));
            binder.issuerTextView.setText(getContext().getString(R.string.trade_issuer_tittle,mitem.getIssuer_id()));
            binder.feedTextView.setText(getContext().getString(R.string.trade_fee_tittle,mitem.getFee()));

        }
    }
}
