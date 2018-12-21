package com.jappy.mercadopago.presetation.trade;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseFragment;
import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.databinding.FragmentTradeBinding;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.utils.Status;

import android.app.Fragment;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TradeFragment extends BaseFragment<FragmentTradeBinding> {

    @Inject TradeListAdapter adapter;
    @Inject ViewModelFactory viewModelFactory;
    private TradeViewModel viewModel;

    public TradeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_trade;
    }

    @Override
    protected void subscribeUi() {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.trade_tittle));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TradeViewModel.class);
        viewModel.getResponse().observe(getViewLifecycleOwner(), response -> processResponse(response));
        viewModel.getLoader().observe(getViewLifecycleOwner(), response -> loadingState(response));
        viewModel.getMessage().observe(getViewLifecycleOwner(), response -> errorState(response));

        binder.recyclerTradeList.setAdapter(adapter);
        binder.recyclerTradeList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void processResponse(List<Trade> response) {
        setData(response);
    }

    private void errorState(Status msj) {
        binder.emptyTextView.setVisibility(View.VISIBLE);

        switch (msj){
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

    private void setData(final List<Trade> item) {
        adapter.initializeList(item);
    }
}
