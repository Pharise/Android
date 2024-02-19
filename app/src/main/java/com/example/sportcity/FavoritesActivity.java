package com.example.sportcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favRecycler = findViewById(R.id.favRecycler);
        TextView favStatus = findViewById(R.id.favStatus);

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        List<Field> fields = databaseAdapter.getFields();
        List<Integer> favIds = databaseAdapter.getFavIds();
        List<Field> favFields = new ArrayList<>();

        for (Field field: fields) {
            if (favIds.contains(field.getId())) {
                favFields.add(field);
            }
        }

        if (favFields.size() > 0) {
            favStatus.setVisibility(View.GONE);
            FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favFields);
            favRecycler.setAdapter(favoritesAdapter);
        } else {
            favRecycler.setVisibility(View.GONE);
            favStatus.setVisibility(View.VISIBLE);
        }

        Log.v("FavoritesActivity", "onCreate");
        getIntent().setAction("Already created");

        databaseAdapter.close();
    }

    @Override
    protected void onResume() {
        Log.v("FavoritesActivity", "onResume");

        String action = getIntent().getAction();
        if (action == null || !action.equals("Already created")) {
            Log.v("FavoritesActivity", "Force restart");
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
            finish();
        } else
            getIntent().setAction(null);

        super.onResume();
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showHint(View view) {
        Toast toast = Toast.makeText(this, "Раздел находится в разработке",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}