package com.tganesh.pantrypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*// Inflate the PantryFragment
        PantryFragment pantryFragment = new PantryFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_layout, pantryFragment, "PantryFragment")
                .commit();*/
    }
}