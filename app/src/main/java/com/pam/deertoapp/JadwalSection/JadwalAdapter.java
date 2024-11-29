package com.pam.deertoapp.JadwalSection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pam.deertoapp.JadwalActivity;
import com.pam.deertoapp.R;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalViewHolder> {
    Fragment fragment;
    List<ItemModel> items;

    public JadwalAdapter(Fragment fragment, List<ItemModel> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull
    @Override
    public JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_view, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalViewHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getName());
        holder.tvDate.setText(items.get(position).getDate());

        Glide.with(fragment.getContext())
                .load(items.get(position).getImageUrl())
                .into(holder.ivVehicle);

        holder.itemView.setOnClickListener(v -> {
            String title = holder.tvTitle.getText().toString();
            String date = holder.tvDate.getText().toString();
            String imageUrl = items.get(position).getImageUrl();

            DetailJadwalFragment detailJadwalFragment = new DetailJadwalFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("date", date);
            bundle.putString("imageUrl", imageUrl);
            detailJadwalFragment.setArguments(bundle);

            if (fragment.getActivity() instanceof JadwalActivity) {
                ((JadwalActivity) fragment.getActivity()).replaceFragment(detailJadwalFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

