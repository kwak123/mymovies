package com.retroquack.kwak123.mymovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.retroquack.kwak123.mymovies.objects.MovieClass;
import com.retroquack.kwak123.mymovies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kwak123 on 10/16/2016.
 * Custom ArrayAdapter for binding images to the GridView. I added some rudimentary progress bar
 * interactions just to play around some with Picasso.
 *
 * Added static ViewHolder class, having some second thoughts about implementing progress bar...
 * The logic behind accessing the progress bar is backwards I'll give it more thought
 */

public class MovieAdapter extends ArrayAdapter<MovieClass> {

    public MovieAdapter(Context context, List<MovieClass> movieClasses) {
        super(context, 0, movieClasses);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
            holder.progressBar.setVisibility(View.VISIBLE);
        } else {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.grid_layout_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        final ProgressBar progressBar = holder.progressBar;

        final MovieClass movie = getItem(position);

        // ImageView loading for movie posters on the main fragment

        Picasso.with(getContext())
                .load(movie.getPosterUrl())
                .into(holder.posterView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {}
                });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.progress_bar) ProgressBar progressBar;
        @BindView(R.id.poster_view) ImageView posterView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
