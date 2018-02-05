package com.testwork;

public class HashMap {
    public static final long NO_VALUE = Long.MAX_VALUE;
    public static final int NO_KEY = Integer.MAX_VALUE;
    // No null for primitives, need to use my own
    // Also applies restriction for values and keys in this HashMap
    // (You can't use max values)

    private int[] keys;
    private long[] values;

    private final float loadFactor;
    // Factor that determines how many entries need to be filled
    // for HashMap to resize
    private int size, treshold, filled;
    // Size of hashmap and filled element count

    public HashMap() {
        this(8, 0.75f);
    }

    public HashMap(int size, float loadFactor) {
        this.loadFactor = loadFactor;
        this.size = size;
        keys = new int[size];
        values = new long[size];
        treshold = (int) (size * loadFactor);

        for (int i = 0, len = keys.length; i < len; i++) {
            keys[i] = NO_KEY;
            values[i] = NO_VALUE;
        }
    }

    private int index(int key) {
        return Math.abs(key) % size;
    }

    public int size() {
        return filled;
    }

    private void resize() {
       size = size << 1;
       treshold = (int) (size * loadFactor);
       int[] oldKeys = keys;
       long[] oldValues = values;
       keys = new int[size];
       values = new long[size];
       filled = 0;

       for (int i = 0, len = keys.length; i < len; i++) {
           keys[i] = NO_KEY;
           values[i] = NO_VALUE;
       }

       for (int i = 0, len = oldKeys.length; i < len; i++) {
           int key = oldKeys[i];
           if (key != NO_KEY) {
               put(key, oldValues[i]);
           }
       }
    }

    public void put(int key, long value) {
        if (filled >= treshold) {
            resize();
        }

        int i = index(key), len = keys.length;
        while (true) {
            if (i == len) {
                i = 0;
                // Zero wrapping
            }

            int key_ = keys[i];
            if (key_ == NO_KEY) {
                keys[i] = key;
                values[i] = value;
                filled += 1;
                return;
            }
            if (key_ == key) {
                values[i] = value;
                return;
            }
            i++;
        }
    }

    public long get(int key) {
        // Search for value with key: key and return it
        // Or NO_VALUE if there is no entry with this key
        int i = index(key), len = keys.length;
        while (true) {
            if (i == len) {
                i = 0;
                // Zero wrapping
            }

            int key_ = keys[i];
            if (key_ == NO_KEY) {
                return NO_VALUE;
            }
            if (key_ == key) {
                return values[i];
            }
            i++;
        }
    }
}
