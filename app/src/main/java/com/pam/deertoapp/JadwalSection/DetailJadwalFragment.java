package com.pam.deertoapp.JadwalSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pam.deertoapp.R;

public class DetailJadwalFragment extends Fragment {
    TextView tvTitle, tvDate;
    ImageView ivItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_jadwal, container, false);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvDate = view.findViewById(R.id.tvDate);
        ivItems = view.findViewById(R.id.ivItems);

        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String date = getArguments().getString("date");
            String imageUrl = getArguments().getString("imageUrl");

            tvTitle.setText(title);
            tvDate.setText(date);

            Glide.with(this)
                    .load(imageUrl)
                    .into(ivItems);
        }

        return view;
    }
}
