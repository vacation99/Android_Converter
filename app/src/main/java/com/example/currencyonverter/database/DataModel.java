package com.example.currencyonverter.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModel extends RealmObject {

    @PrimaryKey
    long id;
    String mainCurr, secondCurr, date, result;
    int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainCurr() {
        return mainCurr;
    }

    public void setMainCurr(String mainCurr) {
        this.mainCurr = mainCurr;
    }

    public String getSecondCurr() {
        return secondCurr;
    }

    public void setSecondCurr(String secondCurr) {
        this.secondCurr = secondCurr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
