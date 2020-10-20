package com.example.cwnpokemon.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.cwnpokemon.R;
import com.example.cwnpokemon.adapters.PokemonAdapter;
import com.example.cwnpokemon.pojo.Pokemon;
import com.example.cwnpokemon.viewmodels.PokemonViewModel;

import java.util.ArrayList;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoritePokesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button mainButton;

    PokemonAdapter adapter;
    PokemonViewModel pokemonViewModel;
    ArrayList<Pokemon> pokes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pokes);

        recyclerView = findViewById(R.id.fav_pokemon_rv);
        mainButton = findViewById(R.id.main_btn);

        pokes = new ArrayList<>();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);

        setupOnSwipe();

        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        pokemonViewModel.getAllPokes();
        pokes = (ArrayList<Pokemon>) pokemonViewModel.getFavPokes();

        adapter.setPokes(pokes);

        mainButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritePokesActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void setupOnSwipe(){

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_DRAG,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = adapter.getPokemonAt(position);
                pokes.remove(viewHolder.getAdapterPosition());
                pokemonViewModel.deletePokemon(pokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoritePokesActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
