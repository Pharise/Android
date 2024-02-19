package com.example.sportcity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.FieldViewHolder> {

    private final Context context;
    private final List<Field> fields;

    public FieldAdapter(Context context, List<Field> fields) {
        this.fields = fields;
        this.context = context;
        }

    @NonNull
    @Override
    public FieldAdapter.FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.field_item, parent, false);
        return new FieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldViewHolder holder, int position) {
        Field field = fields.get(position);
        Bitmap bitmap;

        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(field.getImg());
            bitmap = BitmapFactory.decodeStream(istr);
            holder.fieldImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        holder.fieldTitle.setText(field.getTitle());
        holder.fieldAddress.setText(field.getAddress());
        holder.fieldOpeningHours.setText(field.getOpeningHours());
        holder.fieldCost.setText(field.getCost());
        holder.fieldDetail.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public static final class FieldViewHolder extends RecyclerView.ViewHolder {

        ImageView fieldImage;
        TextView fieldTitle;
        TextView fieldAddress;
        TextView fieldOpeningHours;
        TextView fieldCost;
        ImageButton fieldDetail;

        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);

            fieldImage = itemView.findViewById(R.id.fieldImage);
            fieldTitle = itemView.findViewById(R.id.fieldTitle);
            fieldAddress = itemView.findViewById(R.id.fieldAddress);
            fieldOpeningHours = itemView.findViewById(R.id.fieldOpeningHours);
            fieldCost = itemView.findViewById(R.id.fieldCost);
            fieldDetail = itemView.findViewById(R.id.fieldDetail);
        }
    }
}
