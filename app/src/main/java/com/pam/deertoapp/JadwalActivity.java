package com.pam.deertoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class JadwalActivity extends AppCompatActivity {
    List<ItemModel> items;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        items = new ArrayList<>();
        items.add(new ItemModel("Palembang - Bike - 2241", "11 Okt 2024", R.drawable.bike));
        items.add(new ItemModel("Sukosewu - Cars - 0012", "10 Nov 2022", R.drawable.car));
        items.add(new ItemModel("Palembang - Bike - 2241", "11 Okt 2024", R.drawable.bike));
        items.add(new ItemModel("Sukosewu - Cars - 0012", "10 Nov 2022", R.drawable.car));
        items.add(new ItemModel("Palembang - Bike - 2241", "11 Okt 2024", R.drawable.bike));
        items.add(new ItemModel("Sukosewu - Cars - 0012", "10 Nov 2022", R.drawable.car));
        items.add(new ItemModel("Palembang - Bike - 2241", "11 Okt 2024", R.drawable.bike));
        items.add(new ItemModel("Sukosewu - Cars - 0012", "10 Nov 2022", R.drawable.car));
        items.add(new ItemModel("Palembang - Bike - 2241", "11 Okt 2024", R.drawable.bike));
        items.add(new ItemModel("Sukosewu - Cars - 0012", "10 Nov 2022", R.drawable.car));

        JadwalAdapter adapter = new JadwalAdapter(this, items);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String date = intent.getStringExtra("date");
            int delete = intent.getIntExtra("delete", 0);
            int position = intent.getIntExtra("position", -1);

            if (title != null && date != null) {
                items.add(new ItemModel(title, date, R.drawable.car));
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }
            if (delete != 0 && position != -1) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Item Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        }

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent1 = new Intent(JadwalActivity.this, TambahItemActivity.class);
            startActivity(intent1);
        });
    }
}
