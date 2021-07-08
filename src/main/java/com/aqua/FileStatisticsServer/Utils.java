package com.aqua.FileStatisticsServer;

import java.util.Map;

public class Utils {

    public static String getFileExtFromFile(String filename) {
        String extension = null;
        int index = filename.lastIndexOf('.');
        if (index > 0) {
            extension = filename.substring(index + 1);
        }
        return extension;

    }

    public static <K, V extends Comparable<V>> Map.Entry<K, V> getMaxEntryInMapBasedOnValue(Map<K, V> map) {
        // To store the result
        Map.Entry<K, V> entryWithMaxValue = null;

        // Iterate in the map to find the required entry
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {

            if (
            // If this is the first entry, set result as this
            entryWithMaxValue == null

                    // If this entry's value is more than the max value
                    // Set this entry as the max
                    || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > 0) {

                entryWithMaxValue = currentEntry;
            }
        }

        // Return the entry with highest value
        return entryWithMaxValue;
    }

}
