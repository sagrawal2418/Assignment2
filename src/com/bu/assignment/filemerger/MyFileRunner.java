package com.bu.assignment.filemerger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sandeep Agrawal
 * This project is to show how we can merge 2 json files and search for keywords in the merged file and print the data from the searched object
 */

public class MyFileRunner {

    //constants
    private static final String FILE_1 = "file1.json";
    private static final String FILE_2 = "file2.json";
    private static final String MERGED_FILE = "mergedFile.json";

    /**
     * @param args this contains the java command line arguments
     * @return Does not return anything
     * @description This method is the main method and is used to execute other
     * methods in this file to read and write to new file and search from the merged file
     */
    public static void main(String[] args) throws IOException {

        readAndWriteToNewFile(FILE_1); //read file 1 and write to the merged file
        readAndWriteToNewFile(FILE_2); //read file 2 and write to the merged file
        searchKeywordsInFile(); //search defined keywords from the merged file
    }

    /**
     * @param fileName Passed filename from the main method to read the file
     * @throws IOException This method will read the content of the passed filename and write the content to a new file
     */
    private static void readAndWriteToNewFile(String fileName) throws IOException {
        BufferedReader bufferedReader = null; //declare bufferedReader instance
        BufferedWriter bufferedWriter = null; //declare bufferedWriter instance

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName)); //create bufferedReader object and filereader object with the filename
            bufferedWriter = new BufferedWriter(new FileWriter(MERGED_FILE, true)); //create bufferedWriter object with the filewriter object with new file and set append to true
            String s; //declare string variable to be used for reading and writing to the file
            if (bufferedReader != null) { //check if bufferedReader is not null before accessing it
                while ((s = bufferedReader.readLine()) != null) { //check if buffered reader line is not null
                    if (bufferedWriter != null) { //check if buffered writer is not null before accessing object
                        bufferedWriter.write(s + "\n"); //write to the bufferedWriter and add line break
                    }
                }
            }
            //catch Exceptions
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //print the details of exception
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //clean up all the resources
            if (bufferedReader != null) { //check if bufferedReader is not null before cleaning up the resources
                bufferedReader.close();
            }
            if (bufferedWriter != null) {//check if bufferedWriter is not null before cleaning up the resources
                bufferedWriter.close();
            }
        }
    }

    /**
     * @return Does not return anything
     * This method reads the merged file and searches for the keywords in each line
     */
    private static void searchKeywordsInFile() {
        BufferedReader bufferedReader = null; //create bufferedReader instance

        try {
            bufferedReader = new BufferedReader(new FileReader(MERGED_FILE)); //create bufferedReader object and pass the filereader object with filename
            String s; //declare the string variable to use for reading the file
            while ((s = bufferedReader.readLine()) != null) { //loop through each line and check when file is completed
                searchForKeyword(s); //pass each line content to the search for keyword method
            }
            //handle exceptions and print the stack trace
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //check bufferedReader is not null and cleanup resources
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param content Pass each line content to this method to search for the given keyword
     * @return This method does not return anything
     * @description This method is used to search for the keywords in each line using regex and if found a match then calls the different method
     */
    private static void searchForKeyword(String content) {
        //Regular expressions
        String regex1 = "fitness";
        String regex2 = "wearable";
        //Creating a pattern objects
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        //Creating a List object
        List<Pattern> patterns = new ArrayList<>();
        //Add the patters to the list
        patterns.add(pattern1);
        patterns.add(pattern2);
        //Loop through the pattern and check if keyword matches and call the convertStringToJsonObject with the content of the line
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                convertStringToJsonObject(content);
            }
        }
    }

    /**
     * @param content This is the content of the line passed from the previous method once there is match of keyword
     * @return This method does not return anything
     * @description This method is used to convert the string to json object and print the fund_raised_percent and the close_date from the JSONObject
     */
    private static void convertStringToJsonObject(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content); //create JSONObject and pass the string
            JSONObject data = jsonObject.getJSONObject("data"); //create JSONObject and get the JSONObject for the data field which holds the other data
            double funds_raised_percent = data.getDouble("funds_raised_percent"); //using the data JSONObject and get the funds_raised_percent of double type
            String close_data = data.getString("close_date"); //using the data JSONObject and get the close_date of string type
            System.out.println("funds_raised_percent: " + funds_raised_percent + "\nclose_date: " + close_data + "\n"); //print the funds_raised percent and close date
            //handle exception
        } catch (JSONException err) {
            err.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
