package com.tganesh.pantrypal;

import android.database.Cursor;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tganesh.pantrypal.data.DataBaseHelper;
import com.tganesh.pantrypal.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class PantryFragment extends Fragment {

    private FloatingActionButton addFAB;
    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    //private ArrayList<Ingredient> ingredientArrayList;
    private DataBaseHelper DB;
    private ArrayList<String> ingredient_id, ingredient_name, ingredient_quantity, ingredient_unit;

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

        DB = new DataBaseHelper(getContext());
        ingredient_id = new ArrayList<>();
        ingredient_name = new ArrayList<>();
        ingredient_quantity = new ArrayList<>();
        ingredient_unit = new ArrayList<>();

        storeDataInArrays();
        adapter = new IngredientAdapter(ingredient_id, ingredient_name, ingredient_quantity, ingredient_unit);
        /*//add some test data to the list of ingredients
        ingredientArrayList = new ArrayList<Ingredient>();
        Ingredient sampleIngredient1 = new Ingredient("Apples", 2, "");
        ingredientArrayList.add(sampleIngredient1);
        Ingredient sampleIngredient2 = new Ingredient("Pears", 3, "");
        ingredientArrayList.add(sampleIngredient2);*/

        //set up adapter and bind data from list of ingredients
        //adapter = new IngredientAdapter(ingredientArrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = DB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                ingredient_id.add(cursor.getString(0));
                ingredient_name.add(cursor.getString(1));
                ingredient_quantity.add(cursor.getString(2));
                ingredient_unit.add(cursor.getString(3));
            }
        }
    }

    //Set up Recycler viewHolder
    private static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName_textView, ingredientQuantity_textView, ingredientUnit_textView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientName_textView = itemView.findViewById(R.id.ingredientName_textView);
            ingredientQuantity_textView = itemView.findViewById(R.id.ingredientQuantity_textView);
            ingredientUnit_textView = itemView.findViewById(R.id.ingredientUnit_TextView);
        }

        public void bindData(Ingredient ingredient) { } //data binded in onBindViewHolder()
    }

    //Set up Recycler adapter
    private static class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
        private ArrayList<String> ingredient_id, ingredient_name, ingredient_quantity, ingredient_unit;

        public IngredientAdapter(ArrayList<String> ingredient_id, ArrayList<String> ingredient_name,
                                 ArrayList<String> ingredient_quantity, ArrayList<String> ingredient_unit) {
            this.ingredient_id = ingredient_id;
            this.ingredient_name = ingredient_name;
            this.ingredient_quantity = ingredient_quantity;
            this.ingredient_unit = ingredient_unit;
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
            holder.ingredientName_textView.setText(String.valueOf(ingredient_name.get(position)));
            holder.ingredientQuantity_textView.setText(String.valueOf(ingredient_quantity.get(position)));
            holder.ingredientUnit_textView.setText(String.valueOf(ingredient_unit.get(position)));

        }

        @Override
        public int getItemCount() {
            return ingredient_id.size();
        }

    }
}