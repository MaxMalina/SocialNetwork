package com.learn.maksymgromov.socialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutMeFragment extends Fragment {

    public static String TAG = AboutMeFragment.class.getSimpleName();

    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.fabPhoto) FloatingActionButton floatingActionButton;

    public static int SELECT_IMAGE = 1;

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

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        Glide.with(this)
                                .load(bitmap)
                                .apply(RequestOptions.circleCropTransform())
                                .into(imageView);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
