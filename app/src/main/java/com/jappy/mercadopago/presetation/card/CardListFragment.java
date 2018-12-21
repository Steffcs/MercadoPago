package com.jappy.mercadopago.presetation.card;

import com.google.android.material.snackbar.Snackbar;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseFragment;
import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.databinding.FragmentCardListBinding;
import com.jappy.mercadopago.domain.model.cardModel.Card;
import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.presetation.card.CardListFragmentDirections.CardListaction;
import com.jappy.mercadopago.utils.Status;

import android.view.View;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardListFragment extends BaseFragment<FragmentCardListBinding> {

    @Inject CardListsAdapter adapter;
    @Inject ViewModelFactory viewModelFactory;
    private CardViewModel viewModel;
    int amount;
    CardListaction action;

    public CardListFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_card_list;
    }

    @Override
    protected void subscribeUi() {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.card_tittle));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        action = CardListFragmentDirections.cardListaction();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardViewModel.class);
        viewModel.getResponse().observe(getViewLifecycleOwner(), response -> processResponse(response));
        viewModel.getLoader().observe(getViewLifecycleOwner(), response -> loadingState(response));
        viewModel.getMessage().observe(getViewLifecycleOwner(), response -> errorState(response));

        binder.recyclerCardList.setAdapter(adapter);
        binder.recyclerCardList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnAddToListListener((adapterPosition, item, checkbox) -> {
            String paymentType = item.getId();
            action.setPayment(paymentType);
            amount = Integer.parseInt(CardListFragmentArgs.fromBundle(getArguments()).getAmount());
            action.setAmount(CardListFragmentArgs.fromBundle(getArguments()).getAmount());

            if (amount > item.getMin_allowed_amount() && amount < item.getMax_allowed_amount()) {
                Navigation.findNavController(getView()).navigate(action);
            } else {
                Snackbar.make(binder.fragmentlistCard, getString(R.string.cardfragment_max_text_alert),
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void processResponse(List<Card> response) {

        setData(response);
    }

    private void errorState(Status msj) {
        binder.emptyTextView.setVisibility(View.VISIBLE);

        switch (msj) {
            case ERROR:
                binder.emptyTextView.setText(getString(R.string.network_warning));
                break;
            case EMPTY:
                binder.emptyTextView.setText(getString(R.string.empty_text));
                break;
        }
    }

    private void loadingState(boolean state) {

        binder.loaderView.setVisibility(state ? View.VISIBLE : View.GONE);
        if (state) {
            binder.emptyTextView.setVisibility(View.GONE);
            binder.loaderView.playAnimation();
        } else {
            binder.loaderView.pauseAnimation();
        }
    }

    private void setData(final List<Card> item) {
        adapter.initializeList(item);
    }
}


