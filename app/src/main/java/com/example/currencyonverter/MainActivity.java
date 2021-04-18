package com.example.currencyonverter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.currencyonverter.adapter.PagerAdapter;
import com.example.currencyonverter.database.DataModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements ConverterFragment.OnConverterFragmentListener, HistoryFragment.OnHistoryFragmentListener{

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm = Realm.getInstance(new RealmConfiguration.Builder().name("converter.realm").build());

        TabLayout tabLayout = findViewById(R.id.tabBar);
        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void clearDB() {
        HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);

        realm.executeTransactionAsync(
                realm1 -> realm1.deleteAll(),
                () -> {
            historyFragment.realmDB();
            Toast.makeText(this, "История успешно очистилась", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void sendToRealm(String result, String mainCurr, String secondCurr, String date, int count) {
        realm.executeTransactionAsync(realm1 -> {
            DataModel dataModel = new DataModel();

            Number current_id = realm1.where(DataModel.class).max("id");
            long next_id;
            if (current_id == null)
                next_id = 1;
            else
                next_id = current_id.intValue() + 1;

            dataModel.setId(next_id);
            dataModel.setCount(count);
            dataModel.setDate(date);
            dataModel.setMainCurr(mainCurr);
            dataModel.setSecondCurr(secondCurr);
            dataModel.setResult(result);

            realm1.copyToRealm(dataModel);
        }, () -> {
            HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);
            historyFragment.realmDB();
        });
    }

    @Override
    public List<DataModel> loadRealm() {
        return realm.where(DataModel.class).findAll();
    }

    @Override
    public void deleteFromRealm(long id) {
        realm.executeTransactionAsync(realm1 -> {
            DataModel dataModel = realm1.where(DataModel.class).equalTo("id", id).findFirst();
            dataModel.deleteFromRealm();
        }, () -> {
            HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);
            historyFragment.realmDB();
            Toast.makeText(this, "Удалено из истории", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}