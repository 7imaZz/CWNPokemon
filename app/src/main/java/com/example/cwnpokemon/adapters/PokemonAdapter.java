package com.example.cwnpokemon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cwnpokemon.R;
import com.example.cwnpokemon.pojo.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImageView;
        TextView pokemonNameTextView;
        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImageView = itemView.findViewById(R.id.pokemon_img);
            pokemonNameTextView = itemView.findViewById(R.id.pokemon_name_tv);
        }
    }

    private ArrayList<Pokemon> pokes = new ArrayList<>();
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
    }

    public void setPokes(ArrayList<Pokemon> pokes) {
        this.pokes = pokes;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.pokemonNameTextView.setText(pokes.get(position).getName());
        Glide.with(context).load(pokes.get(position).getUrl()).into(holder.pokemonImageView);
    }

    @Override
    public int getItemCount() {
        return pokes.size();
    }


}
