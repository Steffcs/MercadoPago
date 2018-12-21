package com.jappy.mercadopago.presetation.card;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseListAdapter;
import com.jappy.mercadopago.base.BaseViewHolder;
import com.jappy.mercadopago.base.OnItemClickListener;
import com.jappy.mercadopago.databinding.ItemCardBinding;
import com.jappy.mercadopago.domain.model.cardModel.Card;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CardListsAdapter extends BaseListAdapter<Object, CardListsAdapter.CardViewHolder> {
    private OnItemClickListener<Card> listener;

    public void setOnAddToListListener(OnItemClickListener<Card> listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutIdByType(final int viewType) {
        return R.layout.item_card;
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new CardViewHolder(v);
    }

    public void initializeList(List<Card> item) {
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
            Card mitem = (Card) item;
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
                listener.onItemClick(getAdapterPosition(), (Card) getItem(position),binder.checkbox);

            });
        }
    }
}
