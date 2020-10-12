package com.example.cwnpokemon.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cwnpokemon.R;
import com.example.cwnpokemon.adapters.PokemonAdapter;
import com.example.cwnpokemon.pojo.Pokemon;
import com.example.cwnpokemon.viewmodels.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    PokemonViewModel viewModel;
    RecyclerView pokesRecyclerView;
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokesRecyclerView = findViewById(R.id.pokemon_rv);
        pokesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PokemonAdapter(this);
        pokesRecyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getPokes();

        viewModel.getLiveData().observe(this, pokes -> {
            adapter.setPokes(pokes);
            adapter.notifyDataSetChanged();
        });
    }
}
