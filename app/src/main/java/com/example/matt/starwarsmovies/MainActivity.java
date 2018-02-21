package com.example.matt.starwarsmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ListView mListView;
    private Context mContext;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;


        // data to display
        movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        adapter = new MovieAdapter(this, movieList);


        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movies_list_view);
        mListView.setAdapter(adapter);

        // make each row clickable
        // after clicked..
        // the intent should be created and sent
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);



                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit
                // from, to
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                // send the information from each movie to the MovieAdapter to be displayed
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("posterURL", selectedMovie.posterURL);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("has_seen_position", position);

                startActivityForResult(detailIntent,1);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // SECOND ACTIVITY IS SENDING DATA

                // gets the boolean statement of which button was clicked from the MovieDetailActivity
                boolean alreadySeen = data.getBooleanExtra("already_seen", false);
                boolean wantTo = data.getBooleanExtra("want_to_see", false);
                boolean doNotLike = data.getBooleanExtra("do_not_like", false);

                // check to see which button has been selected
                // then display different strings in the text view

                int position = data.getExtras().getInt("has_seen_position");

                if (alreadySeen){
                    movieList.get(position).hasSeen = "Already Seen.";
                }

                else if (wantTo){
                    movieList.get(position).hasSeen = "Want to see.";
                }

                else if (doNotLike){
                    movieList.get(position).hasSeen = "Do not like.";

                }
                adapter.notifyDataSetChanged();

            }

        }
    }

}
