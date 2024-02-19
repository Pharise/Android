package com.example.sportcity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private CheckBox likeButton;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MapKitFactory.initialize(this);
        mapView = findViewById(R.id.mapview);

        double latitude = getIntent().getDoubleExtra("fieldLatitude", 0);
        double longitude = getIntent().getDoubleExtra("fieldLongitude", 0);
        Point point = new Point(latitude, longitude);
        ImageProvider imageProvider = ImageProvider.fromResource(this, R.drawable.marker);

        mapView.getMap().move(
                new CameraPosition(point, 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark(point, imageProvider);
        placemark.setUserData(getIntent().getStringExtra("fieldTitle"));
        placemark.addTapListener(mapObjectTapListener);

        ImageView fieldImage = findViewById(R.id.fieldImageDetail);
        TextView fieldTitle = findViewById(R.id.fieldTitleDetail);
        TextView fieldAddress = findViewById(R.id.fieldAddressDetail);
        TextView fieldOpeningHours = findViewById(R.id.fieldOpeningHoursDetail);
        TextView fieldPhone = findViewById(R.id.fieldPhoneDetail);
        TextView fieldType = findViewById(R.id.fieldTypeDetail);
        TextView fieldCost = findViewById(R.id.fieldCostDetail);
        likeButton = findViewById(R.id.likeButton);

        if (getIntent().getIntExtra("fieldFav", 0) == 1) {
            likeButton.setChecked(true);
        }

        byte[] byteArray = getIntent().getByteArrayExtra("fieldImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        fieldImage.setImageBitmap(bitmap);
        fieldTitle.setText(getIntent().getStringExtra("fieldTitle"));
        fieldAddress.setText(getIntent().getStringExtra("fieldAddress"));
        fieldOpeningHours.setText(getIntent().getStringExtra("fieldOpeningHours"));
        fieldPhone.setText(getIntent().getStringExtra("fieldPhone"));
        fieldType.setText(getIntent().getStringExtra("fieldType"));
        fieldCost.setText(getIntent().getStringExtra("fieldCost"));
    }

    public void addToFavorites(View view) {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        int fieldId = getIntent().getIntExtra("fieldId", 0);
        if (likeButton.isChecked()) {
            databaseAdapter.addToFavorites(fieldId);
            databaseAdapter.changeFavStatus(fieldId, 1);
        } else {
            databaseAdapter.changeFavStatus(fieldId, 0);
            databaseAdapter.deleteFromFavorites(fieldId);
        }

        databaseAdapter.close();
    }

    private final MapObjectTapListener mapObjectTapListener = (mapObject, point) -> {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                Objects.requireNonNull(mapObject.getUserData()).toString(),
                Toast.LENGTH_SHORT);
        toast.show();
        return true;
    };

    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}