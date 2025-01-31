package com.pam.deertoapp.JadwalSection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.pam.deertoapp.ApiService;
import com.pam.deertoapp.JadwalActivity;
import com.pam.deertoapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalFragment extends Fragment {
    ItemDao itemDao;
    List<ItemModel> items;
    RecyclerView recyclerView;
    JadwalAdapter adapter;
    FloatingActionButton btnAdd;
    ExecutorService executorService;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("items");

    public JadwalFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        items = new ArrayList<>();
        adapter = new JadwalAdapter(this, items);
        recyclerView.setAdapter(adapter);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            TambahItemFragment tambahItemFragment = new TambahItemFragment();
            if (getActivity() instanceof JadwalActivity) {
                ((JadwalActivity) getActivity()).replaceFragment(tambahItemFragment);
            }
        });

        ItemDatabase database = ItemDatabase.getInstance(getActivity());
        itemDao = database.itemDao();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loadItemsFromDatabase();
                } else {
                    fetchApi();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }

    private void fetchApi() {
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
                    List<ItemModel> fetchItems = response.body();
//                    executorService.execute(() -> {
//                        itemDao.deleteAllItems();
//                        itemDao.insert(fetchedItems.toArray(new ItemModel[0]));
//
//                        loadItemsFromDatabase();
//                    });

                    for (ItemModel item : fetchItems) {
                        reference.push().setValue(item);
                    }
                    loadItemsFromDatabase();
                } else {
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }

    private void loadItemsFromDatabase() {
//        executorService.execute(() -> {
//            List<ItemModel> localItems = itemDao.getAllItems();
//            getActivity().runOnUiThread(() -> {
//                items.clear();
//                items.addAll(localItems);
//                adapter.notifyDataSetChanged();
//            });
//        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemModel item = dataSnapshot.getValue(ItemModel.class);
                    items.add(item);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}