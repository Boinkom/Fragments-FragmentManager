package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private int currentFragmentIndex = 1;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTablet) {
                    switchFragment();
                }
            }
        });
    }

    private void switchFragment() {
        Fragment fragment;
        switch (currentFragmentIndex) {
            case 1:
                fragment = new Fragment_2();
                currentFragmentIndex = 2;
                break;
            case 2:
                fragment = new Fragment_3();
                currentFragmentIndex = 3;
                break;
            case 3:
            default:
                fragment = new Fragment_1();
                currentFragmentIndex = 1;
                break;
        }

        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentFragmentIndex", currentFragmentIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentFragmentIndex = savedInstanceState.getInt("currentFragmentIndex", 1);
    }
}
