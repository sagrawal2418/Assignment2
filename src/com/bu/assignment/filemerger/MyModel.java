package com.bu.assignment.filemerger;

public class MyModel {

    //instance variables
    private String query;
    private int count;

    //argument constructor
    public MyModel(String query, int count) {
        this.query = query;
        this.count = count;
    }

    //Getters and setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getCount() {
        return count;
    }

    //this will increment the count each time this is invoked
    public void setCount(int count) {
        this.count += count;
    }

    //tostring method
    @Override
    public String toString() {
        return "MyModel{" +
                "query='" + query + '\'' +
                ", count=" + count +
                '}';
    }
}
