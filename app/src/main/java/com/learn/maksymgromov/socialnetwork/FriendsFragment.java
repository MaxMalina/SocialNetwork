package com.learn.maksymgromov.socialnetwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.maksymgromov.socialnetwork.model.Friend;
import com.learn.maksymgromov.socialnetwork.model.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsFragment extends Fragment {

    public static String TAG = FriendsFragment.class.getSimpleName();

    @BindView(R.id.friends_recycler_view) RecyclerView mFriendsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container,
                false);

        ButterKnife.bind(this, view);

        mFriendsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Friend> data = Utils.getFriendsFromJsonRawFile(getResources(), R.raw.friends);
        Utils.saveFriendsToJson(data);

        FriendsAdapter mAdapter = new FriendsAdapter(getContext(), data);
        mFriendsRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
