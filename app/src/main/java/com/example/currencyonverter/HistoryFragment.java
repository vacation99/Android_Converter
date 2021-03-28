package com.example.currencyonverter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyonverter.adapter.RecyclerViewAdapter;
import com.example.currencyonverter.database.ObjectInDB;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements RecyclerViewAdapter.OnCurrencyListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ObjectInDB> arrayList = new ArrayList<>();
    private TextView textView_history;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        textView_history = view.findViewById(R.id.textViewHistory);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        OnHistoryFragmentListener onHistoryFragmentListener = (OnHistoryFragmentListener) view.getContext();
        onHistoryFragmentListener.loadDB();

        return view;
    }

    public void dataBase(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS history (result TEXT, mainCurr TEXT, oldCurr TEXT, mainCourse TEXT, secondCourse TEXT, date TEXT, count TEXT)");
        Cursor cursor = db.rawQuery("SELECT * FROM history;", null);

        if (cursor.getCount() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
            textView_history.setVisibility(View.VISIBLE);
        }
        else {
            arrayList.clear();
            recyclerView.setVisibility(View.VISIBLE);
            textView_history.setVisibility(View.INVISIBLE);
            while (cursor.moveToNext()) {
                String result = cursor.getString(0);
                String mainCurr = cursor.getString(1);
                String oldCurr = cursor.getString(2);
                String mainCourse = cursor.getString(3);
                String secondCourse = cursor.getString(4);
                String date = cursor.getString(5);
                String count = cursor.getString(6);
                ObjectInDB objectInDB = new ObjectInDB(mainCurr, oldCurr, mainCourse, secondCourse, date, Integer.parseInt(count), Float.parseFloat(result));
                arrayList.add(objectInDB);
            }
            recyclerViewAdapter.setItems(arrayList);
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onCurrencyClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Подробная информация")
                .setMessage("Из какой: " + arrayList.get(position).getMainCurr() + "\n" +
                        "В какую: " + arrayList.get(position).getSecondCurr() + "\n" +
                        "Количество: " + arrayList.get(position).getCount() + "\n" +
                        "Результат: " + arrayList.get(position).getResult() + "\n" +
                        "Дата: " + arrayList.get(position).getDate())
                .setNegativeButton("Закрыть", (dialog, which) -> {})
                .show();
    }

    interface OnHistoryFragmentListener {
        void loadDB();
    }
}