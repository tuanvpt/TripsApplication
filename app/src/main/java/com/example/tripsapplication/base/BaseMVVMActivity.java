package com.example.tripsapplication.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

/**
 * The type Base mvvm activity.
 *
 * @param <V> the type parameter of store the binding
 */
public abstract class BaseMVVMActivity<V extends ViewBinding> extends BaseActivity {
    private V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getLayoutBinding();
        setContentView(binding.getRoot());
        initialize();
    }

    /**
     * Inflate the content view
     *
     * @return binding
     */
    protected V getViewBinding() {
        return binding;
    }

    protected abstract V getLayoutBinding();

    protected abstract void initialize();

}
