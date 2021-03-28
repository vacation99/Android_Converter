package com.example.currencyonverter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyonverter.database.ObjectInDB;
import com.example.currencyonverter.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleViewHolder> {

    private ArrayList<ObjectInDB> arrayList = new ArrayList<>();
    private OnCurrencyListener monCurrencyListener;

    public RecyclerViewAdapter(OnCurrencyListener onCurrencyListener) {
        this.monCurrencyListener = onCurrencyListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object, parent, false);
        return new SimpleViewHolder(view, monCurrencyListener);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setItems(ArrayList<ObjectInDB> objectInDBS) {
        arrayList.clear();
        arrayList.addAll(objectInDBS);
        notifyDataSetChanged();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView title, date;
        OnCurrencyListener onCurrencyListener;

        public SimpleViewHolder(View itemView, OnCurrencyListener onCurrencyListener) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewObjectCount);
            date = itemView.findViewById(R.id.textViewObjectDate);
            this.onCurrencyListener = onCurrencyListener;

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                onCurrencyListener.onCurrencyClick(position);
            });
        }

        public void bind(ObjectInDB objectInDB) {
            title.setText("Количесвто: " + objectInDB.getCount());
            date.setText(objectInDB.getDate());
        }
    }

    public interface OnCurrencyListener {
        void onCurrencyClick(int position);
    }
}