package com.playlistapp.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.playlistapp.utils.Typefaces;

import static com.playlistapp.Constants.CUSTOM_ATTR_SCHEMAS;
import static com.playlistapp.Constants.DEFAULT_FONT;


/**
 * Custom Edit text class.
 */
public class CustomEditTextView  extends AppCompatEditText {

    private Context context;
    private String fontName = null;

    public CustomEditTextView(Context context) {
        super(context);
        this.context = context;
        initilaize();
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            this.fontName = attrs.getAttributeValue(CUSTOM_ATTR_SCHEMAS, "app_font");
            initilaize();
        }
    }

    private void initilaize() {
        if (null == this.fontName) {
            this.fontName = DEFAULT_FONT;
        }
        if(!isInEditMode()){
            Typeface font = Typefaces.get(this.context, this.fontName);
            setTypeface(font);
        }
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }

    private BackPressedListener mOnImeBack;

    /* constructors */

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && mOnImeBack != null) {
            mOnImeBack.onImeBack(this);
        }
        return super.dispatchKeyEvent(event);
    }

    public interface BackPressedListener {
        void onImeBack(CustomEditTextView editText);
    }
}
