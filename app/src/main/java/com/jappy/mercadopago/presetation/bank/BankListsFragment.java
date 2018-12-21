package com.jappy.mercadopago.presetation.bank;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseFragment;
import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.databinding.FragmentBankBinding;
import com.jappy.mercadopago.domain.model.bankModel.Bank;
import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.presetation.bank.BankListsFragmentDirections.BankListAction;
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
public class BankListsFragment extends BaseFragment<FragmentBankBinding> {

    @Inject ViewModelFactory viewModelFactory;
    @Inject BankListsAdapter adapter;

    private BankViewModel viewModel;
    private BankListAction action;

    public BankListsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_bank;
    }

    @Override
    protected void subscribeUi() {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.bank_tittle));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        String payment = BankListsFragmentArgs.fromBundle(getArguments()).getPayment();
        String amount = BankListsFragmentArgs.fromBundle(getArguments()).getAmount();
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BankViewModel.class);
        viewModel.init(payment);
        viewModel.getResponse().observe(getViewLifecycleOwner(), response -> processResponse(response));
        viewModel.getLoader().observe(getViewLifecycleOwner(), response -> loadingState(response));
        viewModel.getMessage().observe(getViewLifecycleOwner(), response -> errorState(response));

        binder.recyclerBankList.setAdapter(adapter);
        binder.recyclerBankList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnAddToListListener((adapterPosition, item, checkbox) -> {
            action =
                    BankListsFragmentDirections.bankListAction();
            action.setPayment(payment);
            action.setAmount(amount);
            action.setIssuer(item.getId());

            Navigation.findNavController(getView()).navigate(action);
        });
    }

    private void processResponse(List<Bank> response) {
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

    private void setData(final List<Bank> item) {
        adapter.initializeList(item);
    }
}
