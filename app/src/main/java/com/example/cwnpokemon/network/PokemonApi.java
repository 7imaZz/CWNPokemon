package com.example.cwnpokemon.network;

import com.example.cwnpokemon.pojo.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApi {

    @GET("pokemon")
    Observable<PokemonResponse> getPokes();
}
