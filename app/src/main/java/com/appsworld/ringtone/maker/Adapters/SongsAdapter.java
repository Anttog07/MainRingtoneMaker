package com.appsworld.ringtone.maker.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsworld.ringtone.maker.Activities.RingdroidSelectActivity;
import com.appsworld.ringtone.maker.Models.SongsModel;
import com.appsworld.ringtone.maker.R;
import com.appsworld.ringtone.maker.Ringdroid.Utils;
import com.appsworld.ringtone.maker.Views.BubbleTextGetter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by REYANSH on 4/8/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ItemHolder> implements BubbleTextGetter {

    // A menu item view type.
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The native app install ad view type.
    private static final int NATIVE_APP_INSTALL_AD_VIEW_TYPE = 1;

    // The native content ad view type.
    private static final int NATIVE_CONTENT_AD_VIEW_TYPE = 2;

    public SongsAdapter(RingdroidSelectActivity ringdroidSelectActivity, ArrayList<SongsModel> data) {
        mRingdroidSelectActivity = ringdroidSelectActivity;
        mData = data;
    }

    private RingdroidSelectActivity mRingdroidSelectActivity;
    private ArrayList<SongsModel> mData;


    @Override
    public SongsAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songs, parent, false));
    }

    @Override
    public void onBindViewHolder(SongsAdapter.ItemHolder holder, int position) {

        holder.mDuration.setText(Utils.makeShortTimeString(mRingdroidSelectActivity.getApplicationContext(),
                Integer.parseInt(mData.get(position).mDuration) / 1000));


        holder.mSongName.setText(mData.get(position).mSongsName);
        holder.mArtistName.setText(mData.get(position).mArtistName);

        ImageLoader.getInstance().displayImage(Utils.getAlbumArtUri(Long.parseLong(mData.get(position).mAlbumId)).toString(),
                holder.mSongsImage,
                new DisplayImageOptions.Builder().cacheInMemory(true)
                        .showImageOnFail(R.drawable.default_art)
                        .showImageOnLoading(R.drawable.default_art)
                        .resetViewBeforeLoading(true)
                        .build());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        try {
            return String.valueOf(mData.get(pos).mSongsName.charAt(0));
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    public void updateData(ArrayList<SongsModel> data) {
        this.mData=data;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mSongsImage;
        private TextView mSongName;
        private TextView mArtistName;
        private TextView mDuration;
        private ImageView mPopUpMenu;

        public ItemHolder(View itemView) {
            super(itemView);
            mSongsImage = (ImageView) itemView.findViewById(R.id.album_art_image_view);
            mSongName = (TextView) itemView.findViewById(R.id.song_name);
            mArtistName = (TextView) itemView.findViewById(R.id.artist_name);
            mDuration = (TextView) itemView.findViewById(R.id.song_duration);
            mPopUpMenu = (ImageView) itemView.findViewById(R.id.overflow);
            mPopUpMenu.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.overflow:
                    mRingdroidSelectActivity.onPopUpMenuClickListener(v, getAdapterPosition());
                    return;
            }
            mRingdroidSelectActivity.onItemClicked(getAdapterPosition());
        }
    }
}
