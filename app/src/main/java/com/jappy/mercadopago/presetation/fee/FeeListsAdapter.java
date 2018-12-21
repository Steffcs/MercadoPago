package com.jappy.mercadopago.presetation.fee;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseListAdapter;
import com.jappy.mercadopago.base.BaseViewHolder;
import com.jappy.mercadopago.base.OnItemClickListener;
import com.jappy.mercadopago.databinding.ItemCardBinding;
import com.jappy.mercadopago.domain.model.feeModel.Fee;
import com.jappy.mercadopago.domain.model.feeModel.Payer;
import com.jappy.mercadopago.presetation.card.CardListsAdapter.CardViewHolder;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FeeListsAdapter extends BaseListAdapter<Object, CardViewHolder> {
    private OnItemClickListener<Payer > listener;


    public void setOnAddToListListener(OnItemClickListener<Payer> listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutIdByType(final int viewType) {
        return R.layout.item_card;
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new FeeListsAdapter.CardViewHolder(v);
    }

    public void initializeList(List<Fee> item) {

        list.clear();
        list.addAll(item.get(0).getPayer_costs());
        notifyDataSetChanged();
    }

    public class CardViewHolder extends BaseViewHolder<Object, ItemCardBinding> {
        View view;

        CardViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        @Override
        public void bind(int position, Object item) {
            Payer mitem = (Payer) item;

            binder.title.setText(getContext()
                    .getString(R.string.fee_item_message, mitem.getInstallments(),mitem.getInstallment_amount()));
            binder.thumbnail.setVisibility(View.GONE);
            binder.cardView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition(), mitem , binder.checkbox);
                binder.checkbox.playAnimation();
            });
        }
    }
}
