# Homework 2 - FileReader & FileWriter
Developed by Sandeep Agrawal

 This project is to show how we can merge 2 text files(json/csv) and create a new file and then search for keywords in the merged file and print the data from the searched object. 

## Technical Details

Technology used - Java
Library - Json

Classes - MyFileRunner.java

Constants
* FILE_1 
* FILE_2
* MERGED_FILE

Methods 
* main - This method is the starting point of program execution, this method will call all the methods in sequence and handle exception. 
* readAndWriteToNewFile - This method will be called with the filename parameter as argument for the 2 separate files and merge the content from both file into new file. 
* searchKeywordsInFile - After the merge file is created, this method is called for each line to search for keywords in the merged file and internally calls the searchForKeyword method. 
* searchForKeyword - This method is called after each line to search for the keywords using the regex, pattern and matcher. 
* convertStringToJsonObject - This method is called if there is a matching keyword and converts the string content into JSONObject using the Json library and then get the
json object to print the close date and fund raised percentage. 


## Assumptions
1. For the purpose of the assignment no UI was implemented. 
2. I have used the following files from the website
  * Indiegogo_2021-05-14T20_40_43_677Z
  * Indiegogo_2021-01-15T10_41_39_329Z
3. Copied 100 entries from both file for demontration purpose and smooth program execution. 
4. No duplicate entries were removed if it exists and only file content was copied from file1 and file 2 to create merged file. 
5. Search method is only called if the keyword exist in the content of the line. 
6. Program execution flow:
  * Program start with main method 
  * FILE_1 is read and content is copied to the MERGED_FILE
  * FILE_2 is read and content is copied to the MERGED_FILE 
  * MERGED_FILE is read liny by line
  * After each line keyword is searched in the line
  * If keyword exist in the line then line content is converted to JSONObject and close date and fund raised percent is printed for that object. 
