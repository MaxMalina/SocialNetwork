package com.learn.maksymgromov.socialnetwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learn.maksymgromov.socialnetwork.model.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsHolder> {

    private List<Friend> mFriendList;
    private Context mContext;

    public FriendsAdapter(Context context, List<Friend> data) {
        mContext = context;
        mFriendList = data;
    }

    @Override
    public FriendsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_friend, parent, false);
        return new FriendsHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendsHolder holder, int position) {
        final Friend friend = mFriendList.get(position);

        String nameSurname = friend.getmName() + ' ' + friend.getmSurname();
        holder.mNameSurname.setText(nameSurname);
        holder.mPhone.setText(friend.getmPhoneNumber());

        Glide.with(mContext)
                .load(mContext.getDrawable(R.drawable.elon))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    class FriendsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail) ImageView mThumbnail;
        @BindView(R.id.name_surname) TextView mNameSurname;
        @BindView(R.id.phone) TextView mPhone;

        FriendsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
