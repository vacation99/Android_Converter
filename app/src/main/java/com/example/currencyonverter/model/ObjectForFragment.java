package com.example.currencyonverter.model;

public class ObjectForFragment {
    private String mainCurr, secondCurr, date;
    private int count;
    private float result;

    public ObjectForFragment(String mainCurr, String secondCurr, String date, int count, float result) {
        this.mainCurr = mainCurr;
        this.secondCurr = secondCurr;
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
