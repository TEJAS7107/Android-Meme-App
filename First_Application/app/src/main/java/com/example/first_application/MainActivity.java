package com.example.first_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.request.RequestListener;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader();
    }
    private String url1="";
    private void loader(){
        // Instantiate the RequestQueue.
        View vp = findViewById(R.id.progressBar);
        vp.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://meme-api.com/gimme";
        View ff = findViewById(R.id.memeimageview);

// Request a string response from the provided URL.
        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            url1 = response.getString("url");
                            Log.d("String print",url1);
                            vp.setVisibility(View.INVISIBLE);
                            Glide.with(MainActivity.this).load(url1).into((ImageView) ff);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.

        queue.add(json);

    }

    public void shareme(View view) {

        Intent i1 = new Intent(Intent.ACTION_SEND);
        i1.setType("text/plain");
        i1.putExtra(Intent.EXTRA_TEXT,url1);
        Intent choose = Intent.createChooser(i1,"Share Using");
        startActivity(choose);


    }

    public void nextmeme(View view) {

        loader();
    }
}