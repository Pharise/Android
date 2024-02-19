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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder>{

    private final Context context;
    private final List<Sport> sports;

    public SportAdapter(Context context, List<Sport> sports) {
        this.sports = sports;
        this.context = context;
    }
    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sport_item, parent, false);
        return new SportAdapter.SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        Sport sport = sports.get(position);

        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(sport.getImg());
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            holder.sportImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        holder.sportImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FieldsActivity.class);

                intent.putExtra("sportID", sport.getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public static final class SportViewHolder extends RecyclerView.ViewHolder {

        ImageButton sportImage;
        public SportViewHolder(@NonNull View itemView) {
            super(itemView);

            sportImage = itemView.findViewById(R.id.sportImage);
        }
    }

}
