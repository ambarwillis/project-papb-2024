package com.pam.deertoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailJadwalActivity extends AppCompatActivity {
    TextView tvTitle, tvDate;
    ImageView ivItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        ivItems = findViewById(R.id.ivItems);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("title");
            String date = bundle.getString("date");
            String imageUrl = bundle.getString("imageUrl");

            tvTitle.setText(title);
            tvDate.setText(date);

            Glide.with(this)
                    .load(imageUrl)
                    .into(ivItems);
        }
    }
}

