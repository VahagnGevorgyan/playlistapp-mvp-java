package com.playlistapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;


/**
 * Utils for typefaces stuff - get fonts.
 */
public final class Typefaces {

    private Typefaces() { }

	private static final String TAG = Typefaces.class.getSimpleName();
	
	private static final HashMap<String, Typeface> cache = new HashMap<>();
	
	public static Typeface get(Context c, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(),
                            assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + assetPath
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

}
