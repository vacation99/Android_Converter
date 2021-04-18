package com.example.currencyonverter;

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
import com.example.currencyonverter.database.DataModel;
import com.example.currencyonverter.model.ObjectForFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements RecyclerViewAdapter.OnCurrencyListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ObjectForFragment> arrayList = new ArrayList<>();
    private TextView textView_history;
    private OnHistoryFragmentListener onHistoryFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        onHistoryFragmentListener = (OnHistoryFragmentListener) view.getContext();

        textView_history = view.findViewById(R.id.textViewHistory);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        realmDB();

        return view;
    }

    public void realmDB() {
        List<DataModel> dataModels = onHistoryFragmentListener.loadRealm();
        arrayList.clear();

        if (dataModels.size() == 0) {
            textView_history.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            textView_history.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            for (int i = 0; i < dataModels.size(); i++) {
                arrayList.add(new ObjectForFragment(
                        dataModels.get(i).getMainCurr(),
                        dataModels.get(i).getSecondCurr(),
                        dataModels.get(i).getDate(),
                        dataModels.get(i).getCount(),
                        dataModels.get(i).getResult(),
                        dataModels.get(i).getId())
                );
            }

            recyclerViewAdapter.setItems(arrayList);
        }
    }

    @Override
    public void onCurrencyClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Подробная информация")
                .setMessage(
                        "Из какой: " + arrayList.get(position).getMainCurr() + "\n" +
                        "В какую: " + arrayList.get(position).getSecondCurr() + "\n" +
                        "Количество: " + arrayList.get(position).getCount() + "\n" +
                        "Результат: " + arrayList.get(position).getResult() + "\n" +
                        "Дата: " + arrayList.get(position).getDate())
                .setNegativeButton("Удалить", (dialog, which) -> {
                    onHistoryFragmentListener.deleteFromRealm(arrayList.get(position).getId());
                    dialog.dismiss();
                })
                .setPositiveButton("Закрыть", (dialog, which) -> {})
                .show();
    }

    interface OnHistoryFragmentListener {
        List<DataModel> loadRealm();
        void deleteFromRealm(long id);
    }
}