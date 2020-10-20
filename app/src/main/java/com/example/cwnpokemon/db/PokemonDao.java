package com.example.cwnpokemon.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cwnpokemon.pojo.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert()
    void insertPokemon(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table WHERE name =:pokemonName")
    void deletePokemon(String pokemonName);

    @Query("SELECT * FROM pokemon_table")
    List<Pokemon> getAllPokes();
}
