package com.jappy.mercadopago.presetation.bank;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseListAdapter;
import com.jappy.mercadopago.base.BaseViewHolder;
import com.jappy.mercadopago.base.OnItemClickListener;
import com.jappy.mercadopago.databinding.ItemCardBinding;
import com.jappy.mercadopago.domain.model.bankModel.Bank;
import com.jappy.mercadopago.presetation.card.CardListsAdapter.CardViewHolder;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BankListsAdapter extends BaseListAdapter<Object, CardViewHolder> {
    private OnItemClickListener<Bank> listener;

    public void setOnAddToListListener(OnItemClickListener<Bank> listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutIdByType(final int viewType) {
        return R.layout.item_card;
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new BankListsAdapter.CardViewHolder(v);
    }

    public void initializeList(List<Bank> item) {

        list.clear();
        list.addAll(item);
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
            Bank mitem = (Bank) item;
            binder.title.setText(mitem.getName());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.fitCenter();
            requestOptions.placeholder(R.drawable.ic_card);
            requestOptions.error(R.drawable.ic_card);

            Glide.with(binder.thumbnail)
                    .setDefaultRequestOptions(requestOptions)
                    .load(mitem.getThumbnail())
                    .into(binder.thumbnail);
            binder.cardView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition(), (Bank) getItem(position), binder.checkbox);
                binder.checkbox.playAnimation();
            });
        }
    }
}
