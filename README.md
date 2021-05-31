# Homework 3 - Search handler
Developed by Sandeep Agrawal

 This project is demonstrate how we can take user input of search keywords and then print the time of search as well as number of frequency of each keyword.

## Technical Details

Technology used - Java

Classes - MyFileRunner.java

Methods 
* main() - This method is the starting point of program execution, this method will call the searchForKeyword() method.  
* searchForKeyword() - This method will prompt the user to enter the number of keywords and then take user input for the number of keywords and add it to the list of keywords to be searched. The method prints the number of keywords to be searched and checks to see if new item need to be added to the list of update the frequency of existing item in the list.
* getCurrentTimeStamp() - This method is called to get the timestamp when the search term was executed
* createMyModelObject() - This method create a new object of the MyModel and add it to the list of MyModel. This will be called if the list does not contain this keyword.
* getIndex() - This method is being used to check to see if the list contains the search term already and if it does it will return the index if not will return -1 which indicates that list does not contain the search term



## Assumptions
1. For the purpose of the assignment no UI was implemented. 
2. Used assignment 2 MyFileRunner.java as the base project. 
3. Removed all the code related to the file reading from Assignment 2 since we do not need to do anything with the content. 
4. Removed the json parsing library from Assignment 2. 
5. Calling the searchForKeyword method directly inside main method to start the program. 
6. Program execution flow:
  * Program start with main method 
  * searchForKeyword() method is executed. 
  * User will be prompted to enter the number of keywords - User need to enter integer value
  * User will be prompted to enter each keyword - User need to enter proper string value
  * System prints the total number of keywords
  * Program searches each keyword inside the list
  * During first time execution the item is added to the list 
  * For subsequent keywords list is searched to see if entry already exists
  * If entry exists then count is incremented otherwise new item is added to the list 
  * System prints all the keywords with the frequency.
