package com.example.currencyonverter.model;

public class ObjectForFragment {
    private String mainCurr, secondCurr, date, result;
    private int count;
    private long id;

    public ObjectForFragment(String mainCurr, String secondCurr, String date, int count, String result, long id) {
        this.mainCurr = mainCurr;
        this.secondCurr = secondCurr;
        this.date = date;
        this.count = count;
        this.result = result;
        this.id = id;
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

    public String getResult() {
        return result;
    }

    public long getId() {
        return id;
    }
}
