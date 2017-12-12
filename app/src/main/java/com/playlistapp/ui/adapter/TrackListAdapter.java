package com.playlistapp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.playlistapp.eventbus.event.FavoriteClickedEvent;
import com.playlistapp.eventbus.event.OpenWebViewEvent;
import com.playlistapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    public void updateTrackItem(TrackItem item, int position) {
        Timber.d("Updating TrackItem in position " + position);
        mList.set(position, item);
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

        holder.btnFavorite.setBackground(
                ContextCompat.getDrawable(mContext, item.isFavorite() ? android.R.drawable.star_big_on : android.R.drawable.star_big_off));

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

    class TrackViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.imageViewTrack)
        ImageView imageViewTrack;
        @BindView(R.id.textViewDuration)
        TextView textViewDuration;
        @BindView(R.id.textViewArtist)
        TextView textViewArtist;
        @BindView(R.id.loadingBar)
        ProgressBar loadingBar;
        @BindView(R.id.btnFavorite)
        ImageButton btnFavorite;

        TrackViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.itemLayout)
        void onItemClicked() {
            SingletonBus.getInstance().post(
                    new OpenWebViewEvent(mList.get(getAdapterPosition()).getUrl(), mList.get(getAdapterPosition()).getName()));
        }

        @OnClick(R.id.btnFavorite)
        void onFavoriteClicked() {
            SingletonBus.getInstance().post(
                    new FavoriteClickedEvent(mList.get(getAdapterPosition()), getAdapterPosition()));
        }
    }
}

