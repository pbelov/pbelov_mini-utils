package com.pbelov.java.miniutils;

import com.pbelov.java.miniutils.utils.FileUtils;
import com.pbelov.java.miniutils.utils.SharedUtils;

import java.util.Arrays;

public class Main {
    private enum Case {
        arraysort,
        levenshtein,
        removedups,
        patternreplace1,
        patternreplace2,
    }

    public static void main(String[] args) {
        SharedUtils.l("pbelov_mini-utils-java version 0.4.0");

        if (args.length == 0) {
            SharedUtils.e("no params, exiting");
            System.exit(0);
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                SharedUtils.l("possible parameters: " + Arrays.toString(Case.values()));
            } else {
                SharedUtils.e("no additional params for case [" + args[0] + "]");
            }
            System.exit(0);
        } else {
            Case _case = Case.valueOf(args[0]);
            switch (_case) {
                case arraysort:
                    arraySort(args);
                    break;
                case levenshtein:
                    calcLevenshtein(args);
                    break;
                case removedups:
                    removeDuplications(args);
                    break;
                case patternreplace1:
                    patternReplace1(args);
                    break;
                case patternreplace2:
                    patternReplace2(args);
                    break;
            }
        }
    }

    private static void patternReplace1(String[] args) {
        if (args.length != 5) {
            SharedUtils.e("wrong parameters number for the case");
        } else {
            PatternGenerator.patternGenerator1(args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        }
    }

    private static void patternReplace2(String[] args) {
        if (args.length != 4) {
            SharedUtils.e("wrong parameters number for the case");
        } else {
            PatternGenerator.patternGenerator2(args[1], args[2], args[3]);
        }
    }

    private static void removeDuplications(String[] args) {
        for (int i = 1; i < args.length; i++) {
            FileUtils.removeDuplications(args[i]);
        }
    }

    private static void calcLevenshtein(String[] args) {
        if (args.length != 3) {
            SharedUtils.e("parameter number is invalid, should be two string to compare");
        } else {
            final String str1 = args[1];
            final String str2 = args[2];
            double percent = LevenshteinUtils.calculateSimilarityPercentage(str1, str2, false);
            int distance = LevenshteinUtils.computeLevenshteinDist(str1, str2);

            SharedUtils.l(str1 + "\r\n" + str2 + "\r\n" + "distance = " + distance + ", percent = " + percent);
        }
    }

    private static void arraySort(String[] args) {
        SharedUtils.l("array sort: " + Arrays.toString(args));
        // must be >= 0
        if (args.length == 1) {
            SharedUtils.e("not enough parameters");
        } else {
            int count = Integer.MAX_VALUE;
            if (args.length == 2) {
                SharedUtils.l("count is not set, using " + count);
            } else {
                count = Integer.valueOf(args[1]);
            }

            ArraySorter.SortWay sortWay = ArraySorter.SortWay.bubble;
            if (args.length < 3) {
                SharedUtils.l("sorting way is not set, using " + sortWay);
            } else {
                sortWay = ArraySorter.SortWay.valueOf(args[2]);
            }

            int maxValue = Integer.MAX_VALUE;
            if (args.length < 4) {
                SharedUtils.l("max value is not set, using " + maxValue);
            } else {
                maxValue = Integer.parseInt(args[3]);
            }

            int minValue = Integer.MIN_VALUE;
            if (args.length < 5) {
                SharedUtils.l("min value is not set, using " + minValue);
            } else {
                minValue = Integer.parseInt(args[4]);
            }

            ArraySorter arraySorter = new ArraySorter(count);
            arraySorter.randomize(minValue, maxValue);
            arraySorter.sort(sortWay);
            arraySorter.verify();
        }
    }
}
