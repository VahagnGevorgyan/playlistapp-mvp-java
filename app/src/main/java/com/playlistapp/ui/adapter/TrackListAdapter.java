package com.playlistapp.ui.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Adapter class for track list.
 */
public class TrackListAdapter
//        extends RecyclerView.Adapter<TrackListAdapter.OrderDetailViewHolder>
{

//    private AppCompatActivity mContext;
//    private List<OrderDetail> mOrderDetailsList;
//
//    public OrderDetailsAdapter(AppCompatActivity context) {
//        this.mContext = context;
//        this.mOrderDetailsList = new ArrayList<>();
//    }
//
//    @Override
//    public OrderDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_order, parent, false);
//        return new OrderDetailViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(OrderDetailViewHolder holder, int position) {
//        OrderDetail item = mOrderDetailsList.get(position);
//        if (item.getTitle() != null) {
//            holder.mTextViewOrderTitle.setText(item.getTitle());
//        }
//        if (item.getValue() != null) {
//            holder.mTextViewOrderValue.setText(item.getValue());
//        }
//        if (StringUtils.isNotEmptySafe(item.getSrc())) {
//            holder.mImageViewOrder.setVisibility(View.VISIBLE);
//            Glide
//                    .with(mContext)
//                    .load(item.getSrc())
//                    .into(holder.mImageViewOrder);
//        } else {
//            holder.mImageViewOrder.setVisibility(View.GONE);
//        }
//
//        if (ORDER_DETAIL_TYPE_DECLARE.equals(item.getType())) {
//            holder.itemView.setVisibility(View.GONE);
//        } else {
//            holder.itemView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mOrderDetailsList != null) {
//            return mOrderDetailsList.size();
//        }
//        return 0;
//    }
//
//    class OrderDetailViewHolder extends RecyclerView.ViewHolder {
//
//        TextView mTextViewOrderTitle;
//        TextView mTextViewOrderValue;
//        ImageView mImageViewOrder;
//
//        OrderDetailViewHolder(View itemView) {
//            super(itemView);
//            mTextViewOrderTitle = (TextView) itemView.findViewById(R.id.textViewOrderTitle);
//            mTextViewOrderValue = (TextView) itemView.findViewById(R.id.textViewOrderValue);
//            mImageViewOrder = (ImageView) itemView.findViewById(R.id.imageViewOrder);
//        }
//    }
//
//    void updateOrderList(List<OrderDetail> list) {
//        Timber.d("Updating Order Details list items " + list);
//        if (list != null) {
//            mOrderDetailsList.clear();
//            mOrderDetailsList.addAll(list);
//        }
//    }
}

