package com.example.matt.starwarsmovies;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Matt on 2/15/2018.
 */

public class MovieAdapter extends BaseAdapter {
    // adapter takes the app itself and a list of data to display


    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;


    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {

        // initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    // methods
    // a list of methods we need to override

    // gives you the number of movies in the data source
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    // returns the item at specific position in the data source
    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_description);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);
            holder.charactersTextView = convertView.findViewById(R.id.main_characters);
            holder.resultTextView = convertView.findViewById(R.id.has_seen);
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        }
        else {
            // get the view holder from convertview
            holder = (ViewHolder) convertView.getTag();

        }
        // get relevant subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView charactersTextView = holder.charactersTextView;
        TextView resultTextView = holder.resultTextView;

        // get corresponding movie for each row
        Movie movie = (Movie) getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorMain));
        titleTextView.setTextSize(20);

        // descriptionTextView
        descriptionTextView.setText(movie.description);
        descriptionTextView.setTextSize(9);
        descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorMain));


        // charactersTextView
        // puts the first three characters from the
        charactersTextView.setText(movie.mainCharacters.get(0) + " , " +
                movie.mainCharacters.get(1) + " , " +
                movie.mainCharacters.get(2));
        charactersTextView.setTextSize(13);
        charactersTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorMain));

        // resultTextView
        resultTextView.setText(movie.hasSeen);
        resultTextView.setTextSize(9);
        resultTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorMain));



        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.posterURL).into(thumbnailImageView);
        return convertView;


    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define


    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView thumbnailImageView;
        public TextView charactersTextView;
        public TextView resultTextView;
    }

}