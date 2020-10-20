package com.example.cwnpokemon.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.cwnpokemon.db.PokemonDB;
import com.example.cwnpokemon.db.PokemonDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomDBModule {


    @Provides
    @Singleton
    static PokemonDB provideDB(Application application) {
        return Room.databaseBuilder(application, PokemonDB.class, "pokemon_table")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    static PokemonDao provideDao(PokemonDB pokemonDB){
        return pokemonDB.pokemonDao();
    }
}
