package com.pam.deertoapp.JadwalSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pam.deertoapp.JadwalActivity;
import com.pam.deertoapp.R;

public class TambahItemFragment extends Fragment {
    EditText etTitle, etDate, etBukaHarga;
    Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_item, container, false);

        etTitle = view.findViewById(R.id.etTitle);
        etDate = view.findViewById(R.id.etDate);
        etBukaHarga = view.findViewById(R.id.etBukaHarga);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String date = etDate.getText().toString();
            String bukaHarga = etBukaHarga.getText().toString();

            if (title.isEmpty() || date.isEmpty() || bukaHarga.isEmpty()) {
                Toast.makeText(getActivity(), "Mohon Isi Semua Form-nya", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Fitur Sementara Tidak Tersedia", Toast.LENGTH_SHORT).show();

                JadwalFragment jadwalFragment = new JadwalFragment();
                if (getActivity() instanceof JadwalActivity) {
                    ((JadwalActivity) getActivity()).replaceFragment(jadwalFragment);
                }
            }
        });

        return view;
    }
}
