package com.example.currencyonverter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.currencyonverter.adapter.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements ConverterFragment.OnConverterFragmentListener, HistoryFragment.OnHistoryFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabBar);
        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void sendData(String result, String mainCurr, String oldCurr, String mainCourse, String secondCourse, String date, String count) {
        HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("historyConverter.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS history (result TEXT, mainCurr TEXT, oldCurr TEXT, mainCourse TEXT, secondCourse TEXT, date TEXT, count TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("result", result);
        contentValues.put("mainCurr", mainCurr);
        contentValues.put("oldCurr", oldCurr);
        contentValues.put("mainCourse", mainCourse);
        contentValues.put("secondCourse", secondCourse);
        contentValues.put("date", date);
        contentValues.put("count", count);
        db.insert("history", null, contentValues);

        historyFragment.dataBase(db);
    }

    @Override
    public void clearDB() {
        HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("historyConverter.db", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE history");

        historyFragment.dataBase(db);
    }

    @Override
    public void loadDB() {
        HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("historyConverter.db", MODE_PRIVATE, null);

        historyFragment.dataBase(db);
    }
}