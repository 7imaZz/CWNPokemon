package com.example.cwnpokemon.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cwnpokemon.db.PokemonDao;
import com.example.cwnpokemon.network.PokemonApi;
import com.example.cwnpokemon.pojo.Pokemon;
import com.example.cwnpokemon.pojo.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private PokemonApi pokemonApi;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(PokemonApi pokemonApi, PokemonDao pokemonDao) {
        this.pokemonApi = pokemonApi;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokes(){
        return pokemonApi.getPokes();
    }

    public void insertPokemon(Pokemon pokemon){
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName){
        pokemonDao.deletePokemon(pokemonName);
    }

    public List<Pokemon> getAllPokes(){
        return pokemonDao.getAllPokes();
    }
}
