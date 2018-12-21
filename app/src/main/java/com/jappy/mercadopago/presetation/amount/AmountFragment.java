package com.jappy.mercadopago.presetation.amount;

import com.google.android.material.snackbar.Snackbar;

import com.jappy.mercadopago.R;
import com.jappy.mercadopago.base.BaseFragment;
import com.jappy.mercadopago.base.ViewModelFactory;
import com.jappy.mercadopago.databinding.FragmentAmountBinding;
import com.jappy.mercadopago.presetation.MainActivity;
import com.jappy.mercadopago.presetation.amount.AmountFragmentDirections.AmountAction;
import com.jappy.mercadopago.presetation.amount.AmountFragmentDirections.TradeAction;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmountFragment extends BaseFragment<FragmentAmountBinding> {
    @Inject ViewModelFactory viewModelFactory;

    private AmountViewModel viewModel;
    private AmountFragmentArgs arg;

    public AmountFragment() {
        // Required empty public constructor

    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_amount;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.trade_list:
                TradeAction trade =
                        AmountFragmentDirections.tradeAction();
                Navigation.findNavController(getView()).navigate(trade);
                // do stuff
                return true;
        }

        return false;
    }

    @Override
    protected void subscribeUi() {
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.amount_tittle));
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AmountViewModel.class);

        if (getArguments() != null) {
            arg = AmountFragmentArgs.fromBundle(getArguments());
            if (arg != null && arg.getRecommendedMessage() != null && !arg.getRecommendedMessage().equalsIgnoreCase("recommended_message")) {
                showSnackbar(arg.getRecommendedMessage());
                setArguments(null);
            }
        }

        binder.buttonPaid.setOnClickListener(view1 -> {
            submitAmount(binder.editTextAmount.getText().toString());
        });

        binder.editTextAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submitAmount(binder.editTextAmount.getText().toString());
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binder.editTextAmount.getWindowToken(), 0);

                return true;
            }
            return false;
        });
    }

    private void showSnackbar(String msj) {
        Snackbar.make(binder.fragmentAmount, msj,
                Snackbar.LENGTH_LONG)
                .show();
    }

    private void submitAmount(String amount) {
        if (!amount.isEmpty() && amount.length() > 4) {
            AmountAction action =
                    AmountFragmentDirections.amountAction();
            action.setAmount(amount.replaceAll("[^0-9]", ""));
            Navigation.findNavController(getView()).navigate(action);
        } else {
            showSnackbar(getString(R.string.amount_warning));
        }
    }
}
