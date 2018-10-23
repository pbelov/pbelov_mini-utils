package com.pbelov.java.miniutils;

public class LevenshteinUtils {
    public static double calculateSimilarityPercentage(String a, String b, boolean ignoreCase) {
        if (a == null || b == null) {
            return a == b ? 1 : 0;
        }
        String str1 = ignoreCase ? a.toLowerCase() : a;
        String str2 = ignoreCase ? b.toLowerCase() : b;
        int distance = computeLevenshteinDist(str1, str2);
        if (distance > 0) {
            return 1 - ((double) distance) / (Math.max(a.length(), b.length()));
        } else {
            return 1;
        }
    }

    public static int computeLevenshteinDist(String a, String b) {
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }

        return costs[b.length()];
    }
}
