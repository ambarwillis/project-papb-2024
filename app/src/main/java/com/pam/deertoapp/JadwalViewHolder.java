package com.pam.deertoapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JadwalViewHolder extends RecyclerView.ViewHolder {
    ImageView ivVehicle;
    TextView tvTitle;
    TextView tvDate;

    public JadwalViewHolder(@NonNull View itemView) {
        super(itemView);
        ivVehicle = itemView.findViewById(R.id.ivVehicle);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDate = itemView.findViewById(R.id.tvDate);
    }
}
