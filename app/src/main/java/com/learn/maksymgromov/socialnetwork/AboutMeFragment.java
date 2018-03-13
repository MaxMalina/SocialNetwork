package com.learn.maksymgromov.socialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learn.maksymgromov.socialnetwork.model.User;
import com.learn.maksymgromov.socialnetwork.model.Utils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutMeFragment extends Fragment {

    public static String TAG = AboutMeFragment.class.getSimpleName();

    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.fabPhoto) FloatingActionButton floatingActionButton;

    @BindView(R.id.email) TextView emailTextView;
    @BindView(R.id.name) TextView nameTextView;
    @BindView(R.id.surname) TextView surnameTextView;
    @BindView(R.id.dateOfBirth) TextView dateOfBirthTextView;
    @BindView(R.id.university) TextView universityTextView;
    @BindView(R.id.school) TextView schoolTextView;
    @BindView(R.id.twitter) TextView twitterTextView;
    @BindView(R.id.phone) TextView phoneTextView;

    public static int SELECT_IMAGE = 1;

    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_me, container,
                false);
        ButterKnife.bind(this, view);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
        });

        user = Utils.getUserFromJsonRawFile(getResources(), R.raw.user);

        Bitmap bitmap = BitmapFactory.decodeFile(user.getImagePath());
        Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

        emailTextView.setText(user.getInfo());
        nameTextView.setText(user.getName());
        surnameTextView.setText(user.getSurname());
        dateOfBirthTextView.setText(user.getDateOfBirth());
        universityTextView.setText(user.getUniversity());
        schoolTextView.setText(user.getSchool());
        twitterTextView.setText(user.getTwitter());
        phoneTextView.setText(user.getPhone());

        return view;
    }
    /*/storage/emulated/0/Download/melon.jpg*/

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

                        user.setImagePath(data.getData().getPath());
                        Utils.saveUserToJson("/storage/emulated/0/Download/user.json", user);

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
