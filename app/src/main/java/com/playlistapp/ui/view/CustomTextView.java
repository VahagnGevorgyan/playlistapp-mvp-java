package com.playlistapp.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.playlistapp.utils.Typefaces;

import static com.playlistapp.Constants.CUSTOM_ATTR_SCHEMAS;
import static com.playlistapp.Constants.DEFAULT_FONT;


/**
 * Custom text view class.
 */
public class CustomTextView extends AppCompatTextView {

	private Context context;
	private String fontName = null;
	
	public CustomTextView(Context context) {
		super(context);
		this.context = context;
		initialize();
	}
	
	public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            this.fontName = attrs.getAttributeValue(CUSTOM_ATTR_SCHEMAS, "fonts");
            initialize();
        }
    }
	
	private void initialize() {
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
	
}
