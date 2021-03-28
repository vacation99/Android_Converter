package com.example.currencyonverter.database;

public class ObjectInDB {
    private String mainCurr, secondCurr, mainCourse, secondCourse, date;
    private int count;
    private float result;

    public ObjectInDB(String mainCurr, String secondCurr, String mainCourse, String secondCourse, String date, int count, float result) {
        this.mainCurr = mainCurr;
        this.secondCurr = secondCurr;
        this.mainCourse = mainCourse;
        this.secondCourse = secondCourse;
        this.date = date;
        this.count = count;
        this.result = result;
    }

    public String getMainCurr() {
        return mainCurr;
    }

    public String getSecondCurr() {
        return secondCurr;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public String getSecondCourse() {
        return secondCourse;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public float getResult() {
        return result;
    }
}
