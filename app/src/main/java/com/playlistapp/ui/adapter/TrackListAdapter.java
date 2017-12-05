package com.playlistapp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.playlistapp.R;
import com.playlistapp.data.network.data.track.Image;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.eventbus.SingletonBus;
import com.playlistapp.eventbus.event.OpenWebViewEvent;
import com.playlistapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import timber.log.Timber;

/**
 * Adapter class for track list.
 */
public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TrackViewHolder> {

    private AppCompatActivity mContext;
    private List<TrackItem> mList;

    public TrackListAdapter(AppCompatActivity mContext) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
    }

    public void updateTrackList(List<TrackItem> list) {
        Timber.d("Updating TrackItem list items " + list);
        if (list != null) {
            mList.clear();
            mList.addAll(list);
        }
    }

    public void addTrackList(List<TrackItem> list) {
        Timber.d("Adding TrackItem list items " + list);
        if (list != null) {
            mList.addAll(list);
        }
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_track, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TrackViewHolder holder, int position) {

        TrackItem item = mList.get(position);

        holder.textViewName.setText(item.getName());
        holder.textViewArtist.setText(item.getArtist().getName());
        if (StringUtils.isNotEmptySafe(item.getDuration())
                && !item.getDuration().equals("0")) {
            holder.textViewDuration.setText(mContext.getString(R.string.str_track_time, item.getDuration()));
        }

        for (Image img :
                item.getImageList()) {
            if (img.getSize().equals("large")) {
                Glide
                    .with(mContext)
                    .load(img.getText())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.loadingBar.setVisibility(View.INVISIBLE);
                            return false;
                        }
                    })
                    .into(holder.imageViewTrack);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;
        ImageView imageViewTrack;
        TextView textViewDuration;
        TextView textViewArtist;
        ImageButton btnFavorite;
        ProgressBar loadingBar;

        TrackViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewTrack = itemView.findViewById(R.id.imageViewTrack);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            textViewArtist = itemView.findViewById(R.id.textViewArtist);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            loadingBar = itemView.findViewById(R.id.loadingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SingletonBus.getInstance().post(
                    new OpenWebViewEvent(mList.get(getAdapterPosition()).getUrl(), mList.get(getAdapterPosition()).getName()));
        }

        @OnClick(R.id.btnFavorite)
        public void onFavoriteClicked(View v) {
            if (mList.get(getAdapterPosition()).isFavorite()) {
                // TODO: 1. Remove from DB favorites table
                // TODO: 2. Change favorite item icon : OFF
            } else {
                // TODO: 1. Add item to DB favorites table
                // TODO: 2. Change favorite item icon : ON
            }

        }
    }
}

