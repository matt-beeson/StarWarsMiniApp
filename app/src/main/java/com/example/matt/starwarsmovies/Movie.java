package com.example.matt.starwarsmovies;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Matt on 2/15/2018.
 */


public class Movie {

    // instance variables
    public String title;
    public Integer episodeNumber;
    public ArrayList<String> mainCharacters = new ArrayList<>();
    public String description;
    public String posterURL;
    public String imdbURL;
    public String hasSeen;

    // constructors
    // default

    // read the json file in and load into Movies

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    // load out of movie.json using the helper method
    // this method will return an array list of movies from the json file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {


        ArrayList<Movie> movieList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct  a movie object for each movie in json
        // ad the object to arrayList
        // return arrayList
        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each recipe in the recipeArray
            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();

                movie.title = movies.getJSONObject(i).getString("title");
                movie.episodeNumber = movies.getJSONObject(i).getInt("episode_number");

                // because the characters appear in an array in the json file..
                // you need to go through the array and put them into a JSON array
                // then from that JSON array you put the characters into an array list for each movie
                JSONArray jsonCharacters = (JSONArray)movies.getJSONObject(i).get("main_characters");
                for(int j = 0; j < jsonCharacters.length()-1;j++){
                    movie.mainCharacters.add(j, jsonCharacters.getString(j));
                }

                // test if all the characters are parsed correctly
                //System.out.println(movie.mainCharacters.toString());

                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterURL = movies.getJSONObject(i).getString("poster");
                movie.imdbURL = movies.getJSONObject(i).getString("url");
                movie.hasSeen = "Has seen?";


                // add arrayList
                movieList.add(movie);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


}

