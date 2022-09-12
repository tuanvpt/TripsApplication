package com.example.tripsapplication.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * Standard Base View Model
 */
public class BaseViewModel extends ViewModel {
    public boolean isPopBackStack = false;
    protected CompositeDisposable disposable = new CompositeDisposable();
    protected MutableLiveData<String> errorMessageObs = new MutableLiveData<>();
    protected MutableLiveData<Boolean> loadingObs = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    /**
     * Show error message
     *
     * @param msg to show string message
     */
    protected void showErrorMessage(String msg) {
        errorMessageObs.postValue(msg);
    }

    /**
     * Show loading
     */
    protected void showLoading() {
        loadingObs.postValue(true);
    }

    /**
     * Hide loading
     */
    protected void hideLoading() {
        loadingObs.postValue(false);
    }
}
