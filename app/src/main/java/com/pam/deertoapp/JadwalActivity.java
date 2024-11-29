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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalActivity extends AppCompatActivity {
    List<ItemModel> items;
    RecyclerView recyclerView;
    JadwalAdapter adapter;
    FloatingActionButton btnAdd;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        items = new ArrayList<>();
        adapter = new JadwalAdapter(this, items);
        recyclerView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(JadwalActivity.this, TambahItemActivity.class);
            startActivity(intent);
        });

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this::fetchItemsFromApi);
    }

    //menerima data api
    private void fetchItemsFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-pam.portoku.my.id")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<ItemModel>> call = apiService.getItems();
        call.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    items.clear();
                    items.addAll(response.body());

                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                } else {
                    runOnUiThread(() -> Toast.makeText(JadwalActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                runOnUiThread(() -> Toast.makeText(JadwalActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}