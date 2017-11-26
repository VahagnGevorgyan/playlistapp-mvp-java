package com.playlistapp.ui.adapter;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.playlistapp.R;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.eventbus.SingletonBus;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static com.playlistapp.Constants.IMAGE_URL;

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

//        if (item.getSite() != null) {
//            Timber.d(":: TrackViewHolder.onBindViewHolder : item.getSite() : " + item.getSite());
//            holder.textViewSite.setText(item.getSite());
//        }
//        if (!TextUtils.isEmpty(item.getImage())) {
//            holder.loadingBar.setVisibility(View.VISIBLE);
//            holder.imageViewProduct.setImageDrawable(null);
//
//            Glide
//                    .with(mContext)
//                    .load(IMAGE_URL + item.getImage())
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            holder.loadingBar.setVisibility(View.INVISIBLE);
//                            return false;
//                        }
//                    })
//                    .into(holder.imageViewProduct);
//        }
//        if (item.getCurrency() != null) {
//            switch (item.getCurrency()) {
//                case "USD":
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        holder.imageViewFlag.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_usa_incline));
//                    } else {
//                        holder.imageViewFlag.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_usa_incline));
//                    }
//                    break;
//                case "GBP":
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        holder.imageViewFlag.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_gb_incline));
//                    } else {
//                        holder.imageViewFlag.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_gb_incline));
//                    }
//                    break;
//                case "RUR":
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        holder.imageViewFlag.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_ru_incline));
//                    } else {
//                        holder.imageViewFlag.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_flag_ru_incline));
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(item.getOldprice() != null) {
//            Timber.d(":: HotOffersRVAdapter.onBindViewHolder : item.getOldprice() : " + item.getOldprice());
//            holder.textViewOldPrice.setText(item.getOldprice());
//            holder.textViewOldPrice.setPaintFlags(holder.textViewOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//        }
//        if(item.getPrice() != null) {
//            Timber.d(":: HotOffersRVAdapter.onBindViewHolder : item.getPrice() : " + item.getPrice());
//            holder.textViewNewPrice.setText(item.getPrice());
//        }
//        if(item.getName() != null) {
//            Timber.d(":: HotOffersRVAdapter.onBindViewHolder : item.getName() : " + item.getName());
//            holder.textViewDescription.setText(item.getName());
//        }

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewSite;
        ImageView imageViewProduct;
        ImageView imageViewFlag;
        TextView textViewDescription;
        TextView textViewSubDescription;
        TextView textViewOldPrice;
        TextView textViewNewPrice;
        ProgressBar loadingBar;

        TrackViewHolder(View itemView) {
            super(itemView);
            textViewSite = (TextView) itemView.findViewById(R.id.textViewSite);
            imageViewProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            imageViewFlag = (ImageView) itemView.findViewById(R.id.imageViewFlag);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewSubDescription = (TextView) itemView.findViewById(R.id.textViewSubDescription);
            textViewOldPrice = (TextView) itemView.findViewById(R.id.textViewOldPrice);
            textViewNewPrice = (TextView) itemView.findViewById(R.id.textViewNewPrice);
            loadingBar = (ProgressBar) itemView.findViewById(R.id.loadingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: Open Track item
//            SingletonBus.getInstance().post(new OpenWebViewEvent(
//                    mList.get(getAdapterPosition()).getShopurl(),
//                    mList.get(getAdapterPosition()).getShop()
//            ));
        }
    }
}

