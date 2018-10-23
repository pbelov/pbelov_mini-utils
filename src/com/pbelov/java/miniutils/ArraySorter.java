package com.pbelov.java.miniutils;

import com.pbelov.java.miniutils.utils.SharedUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArraySorter {
    private static final String TAG = "ArraySorter";
    private final List<Integer> mList;

    public enum SortWay {
        bubble,
        internallist,
        internalcollections,
    }

    public ArraySorter(int count) {
        long t0 = System.currentTimeMillis();
        mList = new ArrayList<>(count);
        SharedUtils.l("init: " + SharedUtils.timeDiff(t0));
    }

    public void randomize() {
        randomize(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void randomize(int maxValue) {
        randomize(0, maxValue);
    }

    public void randomize(int minValue, int maxValue) {
        // minValue must be <= maxValue
        long t0 = System.currentTimeMillis();
        final Random random = new Random(System.currentTimeMillis());
        for (int i = mList.size(); i > 0; i--) {
            mList.set(i, random.nextInt(maxValue + minValue) - minValue);
        }
        SharedUtils.l("randomizing: " + SharedUtils.timeDiff(t0));
    }

    public void sort(SortWay sortWay) {
        SharedUtils.l("sorting with [" + sortWay + "] way");

        long t0 = System.currentTimeMillis();
        switch (sortWay) {
            case bubble:
                sortBubble();
                break;
            case internallist:
                sortInternalList();
                break;
            case internalcollections:
                sortInternalCollections();
                break;
        }
        SharedUtils.l("sorting: " + SharedUtils.timeDiff(t0));
    }

    public void verify() {
        long t0 = System.currentTimeMillis();
        boolean result = true;
        for (int i = mList.size() - 1; i > 0; i--) {
            if (mList.get(i) < mList.get(i - 1)) {
                result = false;
                break;
            }
        }

        SharedUtils.l("verification result: " + result);
        SharedUtils.l("verification: " + SharedUtils.timeDiff(t0));
    }

    private void sortInternalList() {
        mList.sort(Integer::compareTo);
    }

    private void sortInternalCollections() {
        Collections.sort(mList);
    }

    private void sortBubble() {
        int size = mList.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (mList.get(i) > mList.get(j)) {
                    int t = mList.get(j);
                    mList.set(j, mList.get(i));
                    mList.set(i, t);

                }
            }
        }
    }
}
