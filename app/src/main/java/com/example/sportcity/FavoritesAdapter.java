package com.example.sportcity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private final Context context;
    private final List<Field> favFields;

    public FavoritesAdapter(Context context, List<Field> favFields) {
        this.favFields = favFields;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        return new FavoritesAdapter.FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Field field = favFields.get(position);
        Bitmap bitmap;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(field.getImg());
            bitmap = BitmapFactory.decodeStream(istr);
            holder.favImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        holder.favTitle.setText(field.getTitle());
        holder.favAddress.setText(field.getAddress());
        holder.favOpeningHours.setText(field.getOpeningHours());
        holder.favCost.setText(field.getCost());
        holder.favDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                intent.putExtra("fieldImage", byteArray);
                intent.putExtra("fieldTitle", field.getTitle());
                intent.putExtra("fieldAddress", field.getAddress());
                intent.putExtra("fieldOpeningHours", field.getOpeningHours());
                intent.putExtra("fieldPhone", field.getPhone());
                intent.putExtra("fieldType", field.getType());
                intent.putExtra("fieldCost", field.getCost());
                intent.putExtra("fieldId", field.getId());
                intent.putExtra("fieldFav", field.getFavStatus());
                intent.putExtra("fieldLatitude", field.getLatitude());
                intent.putExtra("fieldLongitude", field.getLongitude());
                context.startActivity(intent);
            }
        });
        holder.favDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("Вы действительно хотите удалить данный объект?");
                alert.setNegativeButton(Html.fromHtml("<font color='#333'>Нет</font>"),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                alert.setPositiveButton(Html.fromHtml("<font color='#333'>ДА</font>"),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseAdapter databaseAdapter = new DatabaseAdapter(context);
                        databaseAdapter.open();

                        databaseAdapter.changeFavStatus(field.getId(), 0);
                        databaseAdapter.deleteFromFavorites(field.getId());

                        Intent intent = new Intent(context, FavoritesActivity.class);
                        context.startActivity(intent);
                        ((FavoritesActivity) context).finish();
                        databaseAdapter.open();
                    }
                });
                alert.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return favFields.size();
    }

    public static final class FavoritesViewHolder extends RecyclerView.ViewHolder {

        ImageView favImage;
        TextView favTitle;
        TextView favAddress;
        TextView favOpeningHours;
        TextView favCost;
        ImageButton favDetail;
        ImageButton favDelete;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            favImage = itemView.findViewById(R.id.favImage);
            favTitle = itemView.findViewById(R.id.favTitle);
            favAddress = itemView.findViewById(R.id.favAddress);
            favOpeningHours = itemView.findViewById(R.id.favOpeningHours);
            favCost = itemView.findViewById(R.id.favCost);
            favDetail = itemView.findViewById(R.id.favDetail);
            favDelete = itemView.findViewById(R.id.favDelete);
        }
    }
}
