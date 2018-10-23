package com.pbelov.java.miniutils;

import com.pbelov.java.miniutils.utils.FileUtils;
import com.pbelov.java.miniutils.utils.SharedUtils;

import java.io.File;
import java.util.regex.Pattern;

public class PatternGenerator {
    public static void patternGenerator1(String filename, String pattern, int indexStart, int indexEnd) {
        String rawText = FileUtils.loadFileAsString(new File(filename));
        if (rawText != null) {
            StringBuilder sb = new StringBuilder();

            for (int i = indexStart; i <= indexEnd; i++) {
                String newText = rawText.replaceAll(Pattern.quote(pattern), Integer.toString(i));
                sb.append(newText).append("\n\n");
            }

            FileUtils.writeStringToFile(sb.toString(), new File(filename + "_generated.txt"), false);
            SharedUtils.l("TCGenerator", "done");
        } else {
            SharedUtils.e("TCGenerator1", "something went wrong with file " + filename);
        }
    }

    public static void patternGenerator2(String inputFilename, String listFilename, String pattern) {
        String inputFileContents = FileUtils.loadFileAsString(new File(inputFilename));
        String listFileContents = FileUtils.loadFileAsString(new File(listFilename));
        if (inputFileContents != null && listFileContents != null) {
            StringBuilder sb = new StringBuilder();

            String[] filenamesArray = listFileContents.split("\\r?\\n", -1);

            for (String s : filenamesArray) {
                if (s.contains(".")) {
                    s = s.substring(0, s.lastIndexOf("."));
                }
                String newText = inputFileContents.replaceAll(Pattern.quote(pattern), s);
                sb.append(newText).append("\n\n");
            }

            FileUtils.writeStringToFile(sb.toString(), new File(inputFilename + "_TC_generated.txt"), false);
            SharedUtils.l("TCGenerator2", "done");
        } else {
            if (inputFileContents == null) {
                SharedUtils.e("TCGenerator2", "something went wrong with file " + inputFilename);
            }

            if (listFileContents == null) {
                SharedUtils.e("TCGenerator2", "something went wrong with file " + listFilename);
            }
        }
    }

    private static String generateTCName(String filename) {
        StringBuilder TCName = new StringBuilder();
        String[] words = filename.split("_", -1);
        for (String word : words) {
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            TCName.append(word);
        }

        return TCName.toString();
    }
}
