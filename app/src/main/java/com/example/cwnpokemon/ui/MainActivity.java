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


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity{

    PokemonViewModel viewModel;
    RecyclerView pokesRecyclerView;
    PokemonAdapter adapter;
    Button favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokesRecyclerView = findViewById(R.id.pokemon_rv);
        favButton = findViewById(R.id.fav_btn);
        pokesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PokemonAdapter(this);
        pokesRecyclerView.setAdapter(adapter);

        setupOnSwipe();


        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getPokes();

        viewModel.getPokesLiveData().observe(this, pokes -> {
            adapter.setPokes(pokes);
            adapter.notifyDataSetChanged();
        });

        favButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritePokesActivity.class);
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
                viewModel.insertPokemon(pokemon);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(pokesRecyclerView);
    }

}
