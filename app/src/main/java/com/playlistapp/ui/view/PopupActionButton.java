package com.playlistapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playlistapp.R;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popup action button.
 */
public class PopupActionButton extends LinearLayout {

    @BindView(R.id.button_title)
    TextView mTitle;
    @BindDimen(R.dimen.navigation_margin)
    int mMargin;

    {
        init();
    }

    public PopupActionButton(Context context) {
        super(context);
    }

    public PopupActionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupActionButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflate(getContext(), R.layout.dialog_popup_button, this);
        ButterKnife.bind(this);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    /**
     * @return layout params that should be used when adding view to it's parent.
     */
    public LayoutParams getLayoutParams() {
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = mMargin;
        params.rightMargin = mMargin;
        params.bottomMargin = mMargin;
        return params;
    }

}
