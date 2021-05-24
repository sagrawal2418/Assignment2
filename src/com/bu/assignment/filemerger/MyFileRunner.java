package com.bu.assignment.filemerger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyFileRunner {

    private static final String FILE_1 = "file1.json";
    private static final String FILE_2 = "file2.json";
    private static final String MERGED_FILE = "mergedFile.json";

    public static void main(String[] args) {

        try {
            readAndWriteToNewFile(FILE_1);
            readAndWriteToNewFile(FILE_2);
            readFromFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void readAndWriteToNewFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            bufferedWriter = new BufferedWriter(new FileWriter(MERGED_FILE, true));
            String s;
            if (bufferedReader != null) {
                while ((s = bufferedReader.readLine()) != null) {
                    if (bufferedWriter != null) {
                        bufferedWriter.write(s + "\n");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    public static void readFromFile() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(MERGED_FILE));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                searchForKeyword(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void searchForKeyword(String data) {

        //Regular expressions
        String regex1 = "fitness";
        String regex2 = "wearable";
        //Creating a pattern objects
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        //Creating an List object
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(pattern1);
        patterns.add(pattern2);
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                convertStringToJsonObject(data);
            }
        }
    }

    private static void convertStringToJsonObject(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject d = jsonObject.getJSONObject("data");
            double funds_raised_percent = d.getDouble("funds_raised_percent");
            String close_data = d.getString("close_date");
            System.out.println("funds_raised_percent: " + funds_raised_percent + "\nclose_date: " + close_data + "\n");

        } catch (JSONException err) {
            err.printStackTrace();
        }
    }
}
