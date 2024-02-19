package com.example.sportcity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView weatherTemp;
    private TextView weatherFeels;
    private TextView weatherDescription;
    private ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "ad28d5cf4e29005958a2b7560cfd9b2a";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Krasnoyarsk&appid="
                + key + "&units=metric&lang=ru";

        weatherDescription = findViewById(R.id.weatherDescription);
        weatherTemp = findViewById(R.id.weatherTemp);
        weatherIcon = findViewById(R.id.weatherIcon);
        weatherFeels = findViewById(R.id.weatherFeels);

        new GetURLData().execute(url);

        RecyclerView sportRecycler = findViewById(R.id.sportRecycler);
        sportRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        List<Sport> sports = databaseAdapter.getSports();

        SportAdapter sportAdapter = new SportAdapter(this, sports);
        sportRecycler.setAdapter(sportAdapter);

        databaseAdapter.close();
    }

    public void goToFavorites(View view) {
        Intent intent = new Intent(this, FavoritesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showHint(View view) {
        Toast toast = Toast.makeText(this, "Раздел находится в разработке",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private class GetURLData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                return  buffer.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                int temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp"));
                int feels = (int) Math.round(jsonObject.getJSONObject("main").getDouble("feels_like"));
                String description = jsonObject.getJSONArray("weather").getJSONObject(0)
                        .getString("description");
                String icon = jsonObject.getJSONArray("weather").getJSONObject(0)
                        .getString("icon");
                weatherTemp.setText(temp + "°");
                weatherFeels.setText("По ощущению: " + feels + "°");
                weatherDescription.setText(description.substring(0, 1).toUpperCase() + description.substring(1));
                Picasso.with(MainActivity.this).load("https://openweathermap.org/img/wn/"
                        + icon + ".png").into(weatherIcon);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}