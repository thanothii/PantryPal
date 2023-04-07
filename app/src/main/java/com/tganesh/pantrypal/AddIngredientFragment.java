package com.tganesh.pantrypal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tganesh.pantrypal.data.DataBaseHelper;

public class AddIngredientFragment extends Fragment {

    private Button cancelBtn, addBtn;
    private EditText name_input, quantity_input;
    private Spinner unitSpinner;

    public AddIngredientFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);

        //Initializers
        name_input = view.findViewById(R.id.name_input1_EditText);
        quantity_input = view.findViewById(R.id.quantity_input1_EditText);
        unitSpinner = view.findViewById(R.id.unit_spinner1);
        cancelBtn = view.findViewById(R.id.cancel_Button);
        addBtn = view.findViewById(R.id.add_Button);
        NavController navController = NavHostFragment.findNavController(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Ingredient entry in database
                DataBaseHelper tempDB = new DataBaseHelper(getContext());
                tempDB.addIngredient(name_input.getText().toString().trim(),
                        Double.parseDouble(quantity_input.getText().toString().trim()),
                        unitSpinner.getSelectedItem().toString());
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