package com.example.tripsapplication.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



/**
 * The type Base fragment.
 */
public class BaseFragment extends Fragment {
//    protected CompositeDisposable disposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        disposable = new CompositeDisposable();
    }

    /**
     * Toast shown for short period of time
     * Toast.LENGTH_SHORT - Toast delay 2000 ms predefined
     *
     * @param message - String
     */
    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast shown for short period of time
     * Toast.LENGTH_SHORT - Toast delay 2000 ms predefined
     *
     * @param message - The message follow int
     */
    protected void showToast(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        disposable.clear();
    }
}
