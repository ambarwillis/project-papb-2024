package com.pam.deertoapp.JadwalSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pam.deertoapp.JadwalActivity;
import com.pam.deertoapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TambahItemFragment extends Fragment {
    EditText etTitle, etDate, etLinkGambar;
    Button btnSubmit;
    ItemDatabase itemDatabase;
    ItemDao itemDao;
    ExecutorService executorService;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("items");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_item, container, false);

        itemDatabase = ItemDatabase.getInstance(getActivity());
        itemDao = itemDatabase.itemDao();
        executorService = Executors.newSingleThreadExecutor();

        etTitle = view.findViewById(R.id.etTitle);
        etDate = view.findViewById(R.id.etDate);
        etLinkGambar = view.findViewById(R.id.etLinkGambar);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> saveItem());
        return view;
    }

    void saveItem () {
        String title = etTitle.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String link = etLinkGambar.getText().toString().trim();

        if (title.isEmpty() || date.isEmpty() || link.isEmpty()) {
            Toast.makeText(getActivity(), "Mohon isi semua form-nya", Toast.LENGTH_SHORT).show();
            return;
        }

//        ItemModel item = new ItemModel(title, date, "https://api-pam.portoku.my.id/pam/unknown.png");
//        executorService.execute(() -> {
//            itemDao.insert(item);
//            getActivity().runOnUiThread(() -> {
//                Toast.makeText(getActivity(), "Item berhasil disimpan", Toast.LENGTH_SHORT).show();
//
//                JadwalFragment jadwalFragment = new JadwalFragment();
//                if (getActivity() instanceof JadwalActivity) {
//                    ((JadwalActivity) getActivity()).replaceFragment(jadwalFragment);
//                }
//            });
//        });

        ItemModel item = new ItemModel(title, date, link);
        reference.push().setValue(item)
                .addOnSuccessListener(aVoid -> {
                    if (getActivity() != null) {
                        Toast.makeText(getActivity(), "Item Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        JadwalFragment jadwalFragment = new JadwalFragment();
                        if (getActivity() instanceof JadwalActivity) {
                            ((JadwalActivity) getActivity()).replaceFragment(jadwalFragment);
                        }
                    }
                }).addOnFailureListener(e -> {
                    if (getActivity() != null) {
                        Toast.makeText(getActivity(), "Gagal Menyimpan Item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
