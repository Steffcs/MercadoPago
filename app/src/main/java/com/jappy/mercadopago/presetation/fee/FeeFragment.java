package com.jappy.mercadopago.presetation.fee;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseFragment;
import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.databinding.FragmentFeedBinding;
import com.jappy.mercadopago.domain.model.feeModel.Fee;
import com.jappy.mercadopago.domain.model.trade.Trade;
import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.presetation.fee.FeeFragmentDirections.FeeListAction;
import com.jappy.mercadopago.utils.Status;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeFragment extends BaseFragment<FragmentFeedBinding> {

    @Inject FeeListsAdapter adapter;
    @Inject ViewModelFactory viewModelFactory;
    private FeeViewModel viewModel;
    private FeeListAction action;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public FeeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void subscribeUi() {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.fee_tittle));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        String amount = FeeFragmentArgs.fromBundle(getArguments()).getAmount();
        String payment = FeeFragmentArgs.fromBundle(getArguments()).getPayment();
        String issuer_id = FeeFragmentArgs.fromBundle(getArguments()).getIssuer();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeeViewModel.class);
        viewModel.init(payment, amount, issuer_id);
        viewModel.getResponse().observe(getViewLifecycleOwner(), response -> processResponse(response));
        viewModel.getLoader().observe(getViewLifecycleOwner(), response -> loadingState(response));
        viewModel.getMessage().observe(getViewLifecycleOwner(), response -> errorState(response));

        binder.recyclerFeeList.setAdapter(adapter);
        binder.recyclerFeeList.setLayoutManager(new LinearLayoutManager(getContext()));
        action = FeeFragmentDirections.feeListAction();

        adapter.setOnAddToListListener((adapterPosition, item, checkbox) -> {
            action.setRecommendedMessage(item.getRecommended_message());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Trade trade = new Trade(amount, item.getInstallments(), issuer_id, payment, date.toString());
            mDisposable.add(viewModel.saveTrade(trade)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> Navigation.findNavController(getView()).navigate(action),
                            throwable -> Log.e("FEEFRAGMEN", "Unable to update username", throwable)));

        });
    }

    private void processResponse(List<Fee> response) {
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

    private void setData(final List<Fee> item) {
        adapter.initializeList(item);
    }
}
