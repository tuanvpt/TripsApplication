package com.example.tripsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tripsapplication.base.BaseMVVMActivity;
import com.example.tripsapplication.databinding.ActivityMainBinding;
import com.example.tripsapplication.ui.AllFragment;
import com.example.tripsapplication.ui.HomeFragment;
import com.example.tripsapplication.ui.SearchFragment;
import com.example.tripsapplication.ui.UpdateFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding> {

    @Override
    protected ActivityMainBinding getLayoutBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initialize() {
        // default status
        replaceFragment(new HomeFragment());
        changeActionBar("Home");

        getViewBinding().bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.iHome:
                    replaceFragment(new HomeFragment());
                    changeActionBar("Home");
                    showToast("Home");
                    break;
                case R.id.iAll:
                    replaceFragment(new AllFragment());
                    changeActionBar("All");
                    showToast("All");
                    break;
                case R.id.iSearch:
                    replaceFragment(new SearchFragment());
                    changeActionBar("Search");
                    showToast("Search");
                    break;
                case R.id.iUpdate:
                    replaceFragment(new UpdateFragment());
                    changeActionBar("Update");
                    showToast("Update");
                    break;
            }
            return true;
        });
    }


    /**
     * Fragment all page in activity
     *
     * @param fragment return another fragments
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}