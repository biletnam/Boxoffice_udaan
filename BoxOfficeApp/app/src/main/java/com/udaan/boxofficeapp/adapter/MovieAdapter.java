package com.udaan.boxofficeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udaan.boxofficeapp.model.Cinema;
import com.udaan.boxofficeapp.ui.MovieDetailsActivity;
import com.udaan.boxofficeapp.R;
import com.udaan.boxofficeapp.model.Movie;
import com.udaan.boxofficeapp.util.FontChanger;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shrikanthravi on 27/02/18.
 */


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> movieList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView movieNameTV,genreTV,descTV,typeTV,ratingTV;
        public CardView movieDetailsCV,moviePosterCardCV;
        public ImageView posterIV;

        public MyViewHolder(View view) {
            super(view);

            movieNameTV = view.findViewById(R.id.movieNameTV);
            genreTV = view.findViewById(R.id.genreTV);
            descTV = view.findViewById(R.id.descriptionTV);
            posterIV = view.findViewById(R.id.moviePosterIV);
            typeTV = view.findViewById(R.id.typeTV);
            ratingTV = view.findViewById(R.id.ratingTV);
            movieDetailsCV = view.findViewById(R.id.movieDetailCard);
            moviePosterCardCV = view.findViewById(R.id.moviePosterCard);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/product_san_regular.ttf");
            Typeface bold = Typeface.createFromAsset(context.getAssets(),"fonts/product_sans_bold.ttf");
            FontChanger regularFontChanger = new FontChanger(font);
            regularFontChanger.replaceFonts((ViewGroup)view);

        }
    }

    public MovieAdapter(List<Movie> verticalList, Context context) {
        this.movieList = verticalList;
        this.context = context;
    }
    public void setMovieEntries(List<Movie> entries) {
        movieList = entries;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_column_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Movie movie = movieList.get(position);

        holder.movieNameTV.setText(movie.getTitle());
        String genres="";
//        for(int i=0;i<movie.getGenres().size();i++){
//            genres = genres+" "+movie.getGenres().get(i);
//        }
        holder.genreTV.setText(movie.getGenre());
        holder.descTV.setText(movie.getDescription());

        Picasso.with(context).load(movie.getPosterPath()).into(holder.posterIV);

        holder.posterIV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie",movie);

                Pair<View, String>[] transitionPairs = new Pair[2];


                transitionPairs[0] = Pair.create((View)holder.moviePosterCardCV,holder.moviePosterCardCV.getTransitionName());
                transitionPairs[1] = Pair.create((View)holder.movieDetailsCV,holder.movieDetailsCV.getTransitionName());

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, transitionPairs);
                context.startActivity(intent,options.toBundle());
            }
        });

        holder.movieDetailsCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie",movie);

                Pair<View, String>[] transitionPairs = new Pair[2];

                transitionPairs[0] = Pair.create((View)holder.moviePosterCardCV,holder.moviePosterCardCV.getTransitionName());
                transitionPairs[1] = Pair.create((View)holder.movieDetailsCV,holder.movieDetailsCV.getTransitionName());


                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, transitionPairs);
                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}

