package com.example.cwnpokemon.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cwnpokemon.pojo.Pokemon;
import com.example.cwnpokemon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    private static final String TAG = "PokemonViewModel";

    private Repository repository;
    private MutableLiveData<ArrayList<Pokemon>> pokesLiveData = new MutableLiveData<>();
    private List<Pokemon> favPokes = null;

    @ViewModelInject
    PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokesLiveData() {
        return pokesLiveData;
    }

    public List<Pokemon> getFavPokes() {
        return favPokes;
    }

    public void getPokes(){
        repository.getPokes()
                .subscribeOn(Schedulers.io())
                .map(pokemonResponse -> {
                    ArrayList<Pokemon> pokes = pokemonResponse.getResult();
                    for (Pokemon pokemon: pokes){
                        String url = pokemon.getUrl();
                        String[] pokemonIndex = url.split("/");
                        pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length-1]+".png");
                    }
                    return pokes;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> pokesLiveData.setValue(result),
                        error-> Log.d(TAG, "getPokes: "+error.getMessage()));

    }

    public void insertPokemon(Pokemon pokemon){
        repository.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public void getAllPokes(){
        favPokes = repository.getAllPokes();
    }
}
