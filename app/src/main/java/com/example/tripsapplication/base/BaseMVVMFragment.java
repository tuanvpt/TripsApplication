package com.example.tripsapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

/**
 * The type Base mvvm fragment.
 *
 * @param <V>  the type parameter of store the binding
 * @param <VM> the type parameter of store the view model
 */
public abstract class BaseMVVMFragment<V extends ViewBinding, VM extends BaseViewModel> extends BaseFragment {
    private V binding;
    private VM viewModel;

    /**
     * Add support for inflating the <fragment> tag.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getViewModelProviderOwner()).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = getLayoutBinding();
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModelObs();
        registerBaseViewModelObs();
        registerViewEvent();
        initialize();

    }

    /**
     * Inflate the content view
     *
     * @return binding - call view on screen
     */
    protected V getViewBinding() {
        return binding;
    }

    /**
     * Initialize View Model
     *
     * @return viewModel - Class view model
     */
    protected VM getViewModel() {
        return viewModel;
    }

    /**
     * Gets view model provider owner.
     *
     * @return the view model provider owner
     */
    protected ViewModelStoreOwner getViewModelProviderOwner() {
        return this;
    }

    /**
     * Gets view model class.
     *
     * @return the view model class
     */
    protected abstract Class<VM> getViewModelClass();

    /**
     * Gets layout binding.
     *
     * @return the layout binding
     */
    protected abstract V getLayoutBinding();

    /**
     * Initialize.
     */
    protected abstract void initialize();

    /**
     * Register view event.
     */
    protected abstract void registerViewEvent();

    /**
     * Register view model obs.
     */
    protected abstract void registerViewModelObs();

    /**
     * Register base view model obs
     */
    private void registerBaseViewModelObs() {

    }
}