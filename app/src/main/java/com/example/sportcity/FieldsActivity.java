package com.example.sportcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView fieldRecycler = findViewById(R.id.fieldRecycler);
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        List<Field> fields = databaseAdapter.getFields();
        List<Field> filterFields = new ArrayList<>();

        int sportID = getIntent().getIntExtra("sportID", 0);

        for (Field field : fields) {
            if (field.getSportId() == sportID) {
                filterFields.add(field);
            }
        }

        FieldAdapter fieldAdapter = new FieldAdapter(this, filterFields);
        fieldRecycler.setAdapter(fieldAdapter);

        databaseAdapter.close();
    }

    public void goToFavorites(View view) {
        Intent intent = new Intent(this, FavoritesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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