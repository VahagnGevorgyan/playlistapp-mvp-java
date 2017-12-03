package com.playlistapp.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.playlistapp.R;

import java.util.List;

/**
 * Adapter class for country spinner.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    Context mContext;
    private List<String> mList;

    public CustomSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.mList = objects;
        this.mContext = context;
    }

    public void updateItems(List<String> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        if(mList != null && position == 0) {
            ((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.grey_dark));
        } else {
            ((TextView) v).setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if (position == 0) {
            View tv = new View(getContext());
            tv.setVisibility(View.GONE);
            v = tv;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }
}