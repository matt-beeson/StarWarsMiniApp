package com.example.matt.starwarsmovies;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Matt on 2/15/2018.
 */

public class MovieDetailActivity extends AppCompatActivity{
    private Context mContext;
    private TextView titleText;
    private ImageView poster;
    private TextView descriptionText;
    private Button submitButton;

    private boolean seenChecked;
    private boolean wantChecked;
    private boolean dontLikeChecked;;

    // override the onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;
        titleText = findViewById(R.id.title_view);
        poster = findViewById(R.id.movie_poster);
        descriptionText = findViewById(R.id.movie_description_text);
        submitButton = findViewById(R.id.submit_button);

        // title
        String title = this.getIntent().getExtras().getString("title");
        String description = this.getIntent().getExtras().getString("description");


        // image
        Picasso.with(mContext).load(this.getIntent().getExtras().getString("posterURL")).into(poster);



        // set the title on the action bar
        // set title to the top text view
        descriptionText.setText(description);
        titleText.setText(title);
        setTitle(title);


        // gets the position of the movie in which we are changing the hasSeen
        final int position = this.getIntent().getExtras().getInt("has_seen_position");


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                // construct intent
                Intent radioIntent = new Intent();

                // sends the boolean of which button has been checked
                // sends to the MainActivity
                radioIntent.putExtra("already_seen", seenChecked);
                radioIntent.putExtra("want_to_see", wantChecked);
                radioIntent.putExtra("do_not_like", dontLikeChecked);

                radioIntent.putExtra("has_seen_position", position);

                setResult(RESULT_OK, radioIntent);
                finish();

            }
        });



    }

    // each checks which button pressed
    // will be a boolean statement
    public void seenChecked(View view){
        seenChecked = ((RadioButton)view).isChecked();
    }
    public void wantChecked(View view){
        wantChecked = ((RadioButton)view).isChecked();
    }
    public void dontLikeChecked(View view){
        dontLikeChecked = ((RadioButton)view).isChecked();
    }
}
