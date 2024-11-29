package com.pam.deertoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalViewHolder> {

    Context context;
    List<ItemModel> items;

    public JadwalAdapter(Context context, List<ItemModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_view, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalViewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getName());
        holder.tvDate.setText(items.get(position).getDate());
        holder.ivVehicle.setImageResource(items.get(position).getImage());

        holder.itemView.setOnClickListener(v -> {
            String title = holder.tvTitle.getText().toString();
            String date = holder.tvDate.getText().toString();
            int imageResource = items.get(position).getImage();

            Intent intent = new Intent(context, DetailJadwalActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("date", date);
            bundle.putInt("image", imageResource);
            intent.putExtra("position", position);
            intent.putExtras(bundle);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
