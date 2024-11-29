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

public class DetailJadwalActivity extends AppCompatActivity {
    TextView tvTitle, tvDate;
    ImageView ivItems;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        ivItems = findViewById(R.id.ivItems);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(v -> {
            Intent intent = new Intent(DetailJadwalActivity.this, JadwalActivity.class);
            intent.putExtra("delete", 1);
            intent.putExtra("position", getIntent().getIntExtra("position", -1));
            startActivity(intent);
        });

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("title");
            String date = bundle.getString("date");
            int image = bundle.getInt("image", -1);

            tvTitle.setText(title);
            tvDate.setText(date);

            if (image != -1) {
                ivItems.setImageResource(image);
            }
        }
    }
}