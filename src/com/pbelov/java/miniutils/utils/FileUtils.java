package com.pbelov.java.miniutils.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void removeDuplications(String filename) {
        SharedUtils.l(TAG, "removeDuplications in " + filename);
        final String contents = loadFileAsString(new File(filename));
        if (contents == null) {
            SharedUtils.e("something went wrong with file " + filename);
        } else {
            final String[] lines = contents.split("\\r?\\n", -1);
            HashSet<String> uniqLines = new HashSet<>(Arrays.asList(lines));

            StringBuilder sb = new StringBuilder();
            for (String uniqLine : uniqLines) {
                sb.append(uniqLine).append("\r\n");
            }

            File outFile = new File(filename + "_fixed.txt");
            if (outFile.exists()) {
                outFile.delete();
            }

            writeStringToFile(sb.toString(), outFile, false);
        }
    }

    public static String loadFileAsString(File file) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            return new String(encoded, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeStringToFile(String string, File outputFile, boolean append) {
        try {
            PrintWriter out = new PrintWriter(outputFile);
            if (append) {
                out.append(string);
            } else {
                out.write(string);
            }

            out.close();

            SharedUtils.l(outputFile.getName() + ": success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
