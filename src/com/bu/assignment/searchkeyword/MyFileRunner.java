package com.bu.assignment.searchkeyword;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Sandeep Agrawal
 * This project is to search for keyword provided by user and print the time of search and after search is completed the program print the search term and
 * its frequency.
 */

public class MyFileRunner {

    /**
     * @param args this contains the java command line arguments
     * @return Does not return anything
     * @description This method is the main method and is used to execute other
     * methods in this file to read and write to new file and search from the merged file
     */
    public static void main(String[] args) {
        searchForKeyword(); //call the search for keyword
    }

    /**
     * @return This method does not return anything
     * @description This method will prompt the user to enter the number of keywords and then take user input for the number of keywords and add it to the list
     * of keywords to be searched. The method prints the number of keywords to be searched and checks to see if new item need to be added to the list of update
     * the frequency of existing item in the list.
     */
    private static void searchForKeyword() {

        final List<MyModel> myModels = new ArrayList<>(); //create arraylist object of MyModel type
        List<String> searchKeyword = new ArrayList<>(); //create list of search keyword
        Scanner scanner = new Scanner(System.in); //create scanner object
        System.out.println("Please enter the number of keywords you want to search"); //prompt the user to enter the number of keyword to search
        int n = scanner.nextInt(); //get user input for number of keywords

        for (int i = 0; i < n; i++) { //iterate through the number of keywords
            System.out.println("Please enter your search keyword: "); //prompt the user after each entry to enter the next entry
            String keyword = scanner.next(); //get the keyword from user
            searchKeyword.add(keyword); //add the keyword to the list
        }

        System.out.println("Number of keywords: " + searchKeyword.size()); //print the number of keywords to be searched
        //iterate through the list of keywords
        for (String s : searchKeyword) {
            //print the current time when keyword was searched and the search term
            System.out.println("Time: " + getCurrentTimeStamp() + " search term: " + s);
            if (myModels.size() > 0) { //check to see if the arraylist has contents
                int index = getIndex(s, myModels); //get the index of the arraylist where the keyword exists
                if (index >= 0) { //check if index is greater then 0 which means keyword exists
                    MyModel model = myModels.get(index); //using the index get the model object
                    model.setCount(1); //increment the count of the model which means keyword exist and increase the frequency
                } else {
                    createMyModelObject(s, myModels); //if keyword does not exist we need to add the new entry to the list
                }

            } else {
                createMyModelObject(s, myModels); //if list is empty we need to add new entry to the list
            }
        }

        for (MyModel model : myModels) { //iterate through the list of MyModel
            System.out.println("Search term: " + model.getQuery() + " Frequency: " + model.getCount()); //print the search term and frequency
        }
    }

    /**
     * @return This method return the time stamp in a simple date format
     * This method is called to get the timestamp when the search term was executed
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss.SSS"); //create simpledateformat object with the pattern
        Date date = new Date(System.currentTimeMillis()); //create date instance and pass the current time
        return simpleDateFormat.format(date); //return the date in string format
    }

    /**
     * @param searchTerm This is the search keyword being passed to the method to create a new object
     * @param myModels   List of <MyModel> object which is used to add to the exiting list
     * @return Does not return anything
     * This method create a new object of the MyModel and add it to the list of MyModel. This will be called if the list does not contain this keyword.
     */
    private static void createMyModelObject(String searchTerm, List<MyModel> myModels) {
        MyModel model = new MyModel(searchTerm, 1); //create new object
        myModels.add(model); //add the object to the list
    }

    /**
     * @param searchTerm - This is the search term being passed to search on
     * @param myModels   - This is the List of the MyModel object uses to iterate and see if keyword exists
     * @return This method will return the index of the object in which search term exists
     * This method is being used to check to see if the list contains the search term already and if it does it will return the index if not will return -1
     * which indicates that list does not contain the search term
     */
    private static int getIndex(String searchTerm, List<MyModel> myModels) {
        for (int i = 0; i < myModels.size(); i++) { //iterate through the list
            if (myModels.get(i).getQuery().equals(searchTerm)) { //check to see if the list contains the search term
                return i; //return the index of the searched term in the list
            }
        }
        return -1; //default value if list does not contain the keyword
    }

}
