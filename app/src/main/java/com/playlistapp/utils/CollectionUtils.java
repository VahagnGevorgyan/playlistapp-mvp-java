package com.playlistapp.utils;


import java.util.Collection;

/**
 * Collection Utils.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // The utility class is not publicly instantiable
    }

    /**
     * Checks whether given collection is null or empty.
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
