package com.example.cwnpokemon.repository;

import com.example.cwnpokemon.network.PokemonApi;
import com.example.cwnpokemon.pojo.PokemonResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private PokemonApi pokemonApi;

    @Inject
    public Repository(PokemonApi pokemonApi) {
        this.pokemonApi = pokemonApi;
    }

    public Observable<PokemonResponse> getPokes(){
        return pokemonApi.getPokes();
    }
}
