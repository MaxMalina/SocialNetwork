package com.learn.maksymgromov.socialnetwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutMeFragment extends Fragment {

    public static String TAG = AboutMeFragment.class.getSimpleName();

    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.imageView) ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_me, container,
                false);
        ButterKnife.bind(this, view);

        Glide.with(this)
                .load(imageView.getDrawable())
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
        return view;
    }
}
