package com.pam.deertoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahItemActivity extends AppCompatActivity {
    EditText etTitle, etDate, etBukaHarga;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_item);

        etTitle = findViewById(R.id.etTitle);
        etDate = findViewById(R.id.etDate);
        etBukaHarga = findViewById(R.id.etBukaHarga);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String date = etDate.getText().toString();
            String bukaHarga = etBukaHarga.getText().toString();

            if (title.isEmpty() || date.isEmpty() || bukaHarga.isEmpty()) {
                Toast.makeText(this, "Mohon Isi Semua Form-nya", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(TambahItemActivity.this, JadwalActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("bukaHarga", bukaHarga);

                startActivity(intent);
            }
        });
    }
}
