package com.tganesh.pantrypal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tganesh.pantrypal.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class PantryFragment extends Fragment {

    private FloatingActionButton addFAB;
    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private ArrayList<Ingredient> ingredientArrayList;

    public PantryFragment() {
        // Required empty public constructor
    }

    //idk why this is here, its not used
    public static PantryFragment newInstance(String param1, String param2) {
        PantryFragment fragment = new PantryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);

        //initialize UI
        recyclerView = view.findViewById(R.id.ingredient_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addFAB = view.findViewById(R.id.add_FAB);
        NavController navController = NavHostFragment.findNavController(this);

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_pantryFragment_to_addIngredientFragment);
            }
        });

        //add some test data to the list of ingredients
        ingredientArrayList = new ArrayList<Ingredient>();
        Ingredient sampleIngredient1 = new Ingredient("Apples", 2, "");
        ingredientArrayList.add(sampleIngredient1);
        Ingredient sampleIngredient2 = new Ingredient("Pears", 3, "");
        ingredientArrayList.add(sampleIngredient2);

        //set up adapter and bind data from list of ingredients
        adapter = new IngredientAdapter(ingredientArrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    //Set up Recycler viewHolder
    private static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName_textView;
        private TextView ingredientQuantity_textView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientName_textView = itemView.findViewById(R.id.ingredientName_textView);
            ingredientQuantity_textView = itemView.findViewById(R.id.ingredientQuantity_textView);

        }

        public void bindData(Ingredient ingredient) {
            ingredientName_textView.setText(ingredient.getName());
            ingredientQuantity_textView.setText(Double.toString(ingredient.getQuantity()));
        }

    }

    //Set up Recycler adapter
    private static class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
        private ArrayList<Ingredient> ingredientArrayList;

        public IngredientAdapter(ArrayList<Ingredient> ingredientList) {
            this.ingredientArrayList = ingredientList;
        }

        @NonNull
        @Override
        public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            return new IngredientViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
            holder.bindData(ingredientArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return ingredientArrayList.size();
        }

    }
}