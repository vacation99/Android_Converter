package com.example.currencyonverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.currencyonverter.model.Currency;
import com.example.currencyonverter.pojo.Object;
import com.example.currencyonverter.retrofit.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConverterFragment extends Fragment {

    private final String[] strings = new String[] {"AUD (Австралийский доллар)", "AZN (Азербайджанский манат)", "GBP (Фунт стерлингов Соединенного королевства)", "AMD (Армянских драмов)",
            "BYN (Белорусский рубль)", "BGN (Болгарский лев)", "BRL (Бразильский реал)", "HUF (Венгерских форинтов)", "HKD (Гонконгских долларов)", "DKK (Датская крона)", "USD (Доллар США)",
            "EUR (Евро)", "INR (Индийских рупий)", "KZT (Казахстанских тенге)", "CAD (Канадский доллар)", "KGS (Киргизских сомов)", "CNY (Китайский юань)", "MDL (Молдавских леев)", "NOK (Норвежских крон)",
            "PLN (Польский злотый)", "RON (Румынский лей)", "XDR (СДР (специальные права заимствования))", "SGD (Сингапурский доллар)", "TJS (Таджикских сомони)", "TRY (Турецких лир)",
            "TMT (Новый туркменский манат)", "UZS (Узбекских сумов)", "UAH (Украинских гривен)", "CZK (Чешских крон)", "SEK (Шведских крон)", "CHF (Швейцарский франк)", "ZAR (Южноафриканских рэндов)",
            "KRW (Вон Республики Корея)", "JPY (Японских иен)", "RUB (Российский рубль)"};
    private String main, second, currentDate;
    private OnConverterFragmentListener onConverterFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        onConverterFragmentListener = (OnConverterFragmentListener) view.getContext();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        currentDate = simpleDateFormat.format(date);

        ArrayList<Currency> currencyArrayList = new ArrayList<>();

        Spinner spinner_main = view.findViewById(R.id.spinnerMain);
        Spinner spinner_second = view.findViewById(R.id.spinnerSecond);
        TextView textView_count = view.findViewById(R.id.editTextCount);
        TextView textView_result = view.findViewById(R.id.editTextResult);
        Button button_convert = view.findViewById(R.id.buttonConvert);
        Button button_clearDB = view.findViewById(R.id.buttonClearDB);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.cbr-xml-daily.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        retrofitInterface.someResponse().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                currencyArrayList.add(new Currency(strings[0], response.body().getRates().getAUD()));
                currencyArrayList.add(new Currency(strings[1], response.body().getRates().getAZN()));
                currencyArrayList.add(new Currency(strings[2], response.body().getRates().getGBP()));
                currencyArrayList.add(new Currency(strings[3], response.body().getRates().getAMD()));
                currencyArrayList.add(new Currency(strings[4], response.body().getRates().getBYN()));
                currencyArrayList.add(new Currency(strings[5], response.body().getRates().getBGN()));
                currencyArrayList.add(new Currency(strings[6], response.body().getRates().getBRL()));
                currencyArrayList.add(new Currency(strings[7], response.body().getRates().getHUF()));
                currencyArrayList.add(new Currency(strings[8], response.body().getRates().getHKD()));
                currencyArrayList.add(new Currency(strings[9], response.body().getRates().getDKK()));
                currencyArrayList.add(new Currency(strings[10], response.body().getRates().getUSD()));
                currencyArrayList.add(new Currency(strings[11], response.body().getRates().getEUR()));
                currencyArrayList.add(new Currency(strings[12], response.body().getRates().getINR()));
                currencyArrayList.add(new Currency(strings[13], response.body().getRates().getKZT()));
                currencyArrayList.add(new Currency(strings[14], response.body().getRates().getCAD()));
                currencyArrayList.add(new Currency(strings[15], response.body().getRates().getKGS()));
                currencyArrayList.add(new Currency(strings[16], response.body().getRates().getCNY()));
                currencyArrayList.add(new Currency(strings[17], response.body().getRates().getMDL()));
                currencyArrayList.add(new Currency(strings[18], response.body().getRates().getNOK()));
                currencyArrayList.add(new Currency(strings[19], response.body().getRates().getPLN()));
                currencyArrayList.add(new Currency(strings[20], response.body().getRates().getRON()));
                currencyArrayList.add(new Currency(strings[21], response.body().getRates().getXDR()));
                currencyArrayList.add(new Currency(strings[22], response.body().getRates().getSGD()));
                currencyArrayList.add(new Currency(strings[23], response.body().getRates().getTJS()));
                currencyArrayList.add(new Currency(strings[24], response.body().getRates().getTRY()));
                currencyArrayList.add(new Currency(strings[25], response.body().getRates().getTMT()));
                currencyArrayList.add(new Currency(strings[26], response.body().getRates().getUZS()));
                currencyArrayList.add(new Currency(strings[27], response.body().getRates().getUAH()));
                currencyArrayList.add(new Currency(strings[28], response.body().getRates().getCZK()));
                currencyArrayList.add(new Currency(strings[29], response.body().getRates().getSEK()));
                currencyArrayList.add(new Currency(strings[30], response.body().getRates().getCHF()));
                currencyArrayList.add(new Currency(strings[31], response.body().getRates().getZAR()));
                currencyArrayList.add(new Currency(strings[32], response.body().getRates().getKRW()));
                currencyArrayList.add(new Currency(strings[33], response.body().getRates().getJPY()));
                currencyArrayList.add(new Currency(strings[34], 1));
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, strings);
        spinner_main.setAdapter(arrayAdapter);
        spinner_second.setAdapter(arrayAdapter);

        button_convert.setOnClickListener(v -> {
            if (textView_count.getText().toString().equals(""))
                Toast.makeText(view.getContext(), "Введите количество", Toast.LENGTH_SHORT).show();
            else {
                float firstNum = 0, secondNum = 0;
                boolean firstDone = false, secondDone = false;
                int count = Integer.parseInt(textView_count.getText().toString());
                main = (String) spinner_main.getSelectedItem();
                second = (String) spinner_second.getSelectedItem();

                for (int i = 0; i < currencyArrayList.size(); i++) {
                    if (main.equals(currencyArrayList.get(i).getName())) {
                        firstDone = true;
                        firstNum = currencyArrayList.get(i).getRate();
                    }
                    if (second.equals(currencyArrayList.get(i).getName())) {
                        secondDone = true;
                        secondNum = currencyArrayList.get(i).getRate();
                    }
                    if (firstDone && secondDone)
                        break;
                }
                float result = (secondNum / firstNum) * count;

                textView_result.setText(String.format("%.4f", result));

                onConverterFragmentListener.sendToRealm(Float.parseFloat(String.format("%.4f", result)), main, second, currentDate, count);
            }
        });

        button_clearDB.setOnClickListener(v -> onConverterFragmentListener.clearDB());

        return view;
    }

    interface OnConverterFragmentListener {
        void clearDB();
        void sendToRealm(float result, String mainCurr, String secondCurr, String date, int count);
    }
}