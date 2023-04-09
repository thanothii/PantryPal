package com.tganesh.pantrypal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class IngredientEditingFragment extends Fragment {


    private Button updateBtn;
    private Button cancelBtn;

    public IngredientEditingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredient_editing, container, false);

        updateBtn = view.findViewById(R.id.update_Button);
        cancelBtn = view.findViewById(R.id.cancelEdit_Button);
        NavController navController = NavHostFragment.findNavController(this);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Update Ingredient entry in database
                navController.navigateUp();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
            }
        });

        return view;
    }
}