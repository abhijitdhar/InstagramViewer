package com.adapp.instagramviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotoActivity extends ActionBarActivity {

    public static final String CLIENT_ID = "d0154260a3ac49ccb8b17dedac2664a4";

    private ArrayList<InstagramPhoto> photoList = new ArrayList<>();
    private InstagramPhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().hide();
        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {
        // https://api.instagram.com/v1/media/popular?client_id=d0154260a3ac49ccb8b17dedac2664a4

        photosAdapter = new InstagramPhotosAdapter(this, photoList);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);

        String popularUrl = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(popularUrl, new JsonHttpResponseHandler() {
           // define success and failure

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("INFO", response.toString());
                JSONArray photosJsonArr = null;
                try {
                    photoList.clear();
                    photosJsonArr = response.getJSONArray("data");
                    for (int i = 0; i < photosJsonArr.length(); i++) {
                        JSONObject photoJson = photosJsonArr.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.setUsername(photoJson.getJSONObject("user").getString("username"));
                        if(photoJson.getJSONObject("caption") != null) {
                            photo.setCaption(photoJson.getJSONObject("caption").getString("text"));
                        }
                        photo.setImageHeight(photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        photo.setImageUrl(photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        photo.setLikesCount(photoJson.getJSONObject("likes").getInt("count"));
                        photo.setUsername(photoJson.getJSONObject("user").getString("username"));
                        photo.setProfilePicture(photoJson.getJSONObject("user").getString("profile_picture"));

                        //Log.i("DEBUG", photo.toString());
                        photoList.add(photo);

                    }

                    photosAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
