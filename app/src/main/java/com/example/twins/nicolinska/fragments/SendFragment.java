package com.example.twins.nicolinska.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twins.nicolinska.Interface.OnSendDataListener;
import com.example.twins.nicolinska.Model.AnswerServer;
import com.example.twins.nicolinska.Model.PriceModel;
import com.example.twins.nicolinska.Model.SaleModel;
import com.example.twins.nicolinska.R;
import com.example.twins.nicolinska.data.ApiManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;
import static com.example.twins.nicolinska.MainActivity.PRICE_JSON;


public class SendFragment extends Fragment implements OnSendDataListener {
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    private List<String> arrayListOrder = new ArrayList<>();
    private Map<String, String> mapOrder = new HashMap<>();
    private Map<String, SaleModel> listGoods;
    private double summa = 0;
    private Toolbar mToolbar;
    private TextView textView;
    private int year, month, day, dayWeek;
    private View mView;
    private ProgressBar progressBar;
    private PriceModel priceModel;
    private ListView listOrder;
    private SharedPreferences mSharedPreferences;

    public void newInstance(Map<String, String> mapOrder, Map<String, SaleModel> listGoods) {
        this.mapOrder = mapOrder;
        this.listGoods = listGoods;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_send, container, false);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        getPriceFromSharedPref();

        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar_fragment);
        mToolbar.setTitle(R.string.order);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        setInfo(R.id.text_name, "pref_name");
        setInfo(R.id.text_place, "pref_address");
        setInfo(R.id.text_phone, "pref_phone");
        setInfo(R.id.text_email, "pref_email");
        setInfo(R.id.text_comments, "pref_comments");

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        printDate(calendar);
        LinearLayout linearLayoutDate = (LinearLayout) mView.findViewById(R.id.liner_layout_summa_date);
        linearLayoutDate.setOnClickListener(v -> startDialogData());

        Button buttonSend = (Button) mView.findViewById(R.id.btn_send_to_server);

        listOrder = (ListView) mView.findViewById(R.id.list_view_order);

        progressBar = (ProgressBar) mView.findViewById(R.id.progress_bar);

        buttonSend.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            sendData();
            buttonSend.setEnabled(false);
        });

        loadArrayListOrder();
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.item_order, arrayListOrder);
        listOrder.setAdapter(adapter);

        textView = (TextView) mView.findViewById(R.id.tv_summa);
        textView.setText(String.valueOf(summa));

        return mView;
    }

    private void getPriceFromSharedPref() {
        String jsonPrice = getActivity().getSharedPreferences(getActivity().getLocalClassName(), MODE_PRIVATE).getString(PRICE_JSON, "");
        Log.i("MyLog", "jsonPrice = " + jsonPrice);
        ObjectMapper mapper = new ObjectMapper();

        try {
            priceModel = mapper.readValue(jsonPrice, PriceModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyLog", "error_GoodsFragment_getPriceFromSharedPref");
        }
    }

    private void setInfo(int idText, String idPref) {
        textView = (TextView) mView.findViewById(idText);
        String value = mSharedPreferences.getString(idPref, "");
        if (value.length() > 0)
            textView.setText(value);
        else
            textView.setVisibility(View.GONE);
    }

    private void startDialogData() {
        DialogCalendarFragment calFrag = new DialogCalendarFragment();
        calFrag.setTargetFragment(this, 0);
        calFrag.show(getFragmentManager(), "dialog-calendar");
    }

    @Override
    public void onSendDataListener(CalendarDay chooseDay) {
        printDate(chooseDay.getCalendar());
    }

    private void printDate(Calendar calendar) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        textView = (TextView) mView.findViewById(R.id.tv_date);
        textView.setText(day + "." + (month + 1) + "." + year + " ( " +
                getResources().getStringArray(R.array.custom_weekdays_full)[dayWeek - 1] + " )");
    }

    private void loadArrayListOrder() {
        int price;
        if (Integer.parseInt(mapOrder.get("bullon")) > 0) {
            price = Integer.parseInt(mapOrder.get("bullon")) * (int) priceModel.getRozniza().getBallon();
            arrayListOrder.add("Баллон: " + mapOrder.get("bullon") + " шт." + " = " + price + " грн");
            summa += price;
        } else if (Integer.parseInt(mapOrder.get("bullon")) >= 10) {
            price = Integer.parseInt(mapOrder.get("bullon")) * (int) priceModel.getOpt().getBallon();
            arrayListOrder.add("Баллон: " + mapOrder.get("bullon") + " шт." + " = " + price + " грн");
            summa += price;
        }
        if (mapOrder.containsKey("tara")) {
            price = Integer.parseInt(mapOrder.get("tara")) * (int) priceModel.getRozniza().getTara();
            arrayListOrder.add("Возратная тара: " + mapOrder.get("tara") + " шт." + " = " + price + " грн");
            summa += price;
        }
        if (mapOrder.containsKey("pompa")) {
            price = Integer.parseInt(mapOrder.get("pompa")) * (int) priceModel.getRozniza().getPompa();
            arrayListOrder.add("Помпа: " + mapOrder.get("pompa") + " шт." + " = " + price + " грн");
            summa += price;
        }
        if (listGoods.size() > 0) {
            for (Map.Entry<String, SaleModel> entry : listGoods.entrySet()) {
                arrayListOrder.add(entry.getValue().title + ": " + entry.getValue().getValue() +
                        " = " + (entry.getValue().priceRozniza * entry.getValue().getValue()) + " грн");
                summa += entry.getValue().priceRozniza * entry.getValue().getValue();
                mapOrder.put(entry.getKey(), String.valueOf(entry.getValue().getValue()));
            }
        }
    }

    private void sendData() {
        mapOrder.put("name", mSharedPreferences.getString("pref_name", ""));
        mapOrder.put("address", mSharedPreferences.getString("pref_address", ""));
        mapOrder.put("phones", mSharedPreferences.getString("pref_phone", ""));
        mapOrder.put("summa", String.valueOf(summa));
        mapOrder.put("dateorder", year + "-" + (month + 1) + "-" + day);
        if (mSharedPreferences.getBoolean("pref_enable_confirmation", false))
            mapOrder.put("email", mSharedPreferences.getString("pref_email", ""));
        if (mSharedPreferences.getString("pref_comments", "").length() > 0)
            mapOrder.put("comments", mSharedPreferences.getString("pref_comments", ""));

        Subscription subscription = ApiManager.setOrder(mapOrder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataSuccess, this::onDataError);
        mSubscriptions.add(subscription);
    }

    private void onDataSuccess(AnswerServer answerServer) {
        Log.i("MyLog", "message = " + answerServer.getMessage());
        Log.i("MyLog", "success = " + answerServer.getSuccess());
        if (answerServer.getSuccess() == 1) {
            new DialogInfoSendOrderFragment().show(getFragmentManager(), "info_send_order_fragment");
            progressBar.setVisibility(View.INVISIBLE);
            mView.findViewById(R.id.layout_error).setVisibility(View.GONE);
        } else
            setErrorConnect(answerServer.getMessage());
    }

    private void onDataError(Throwable t) {
        //TODO say to user that there is no Internet
        Log.i("MyLog", "onDataError " + t.toString());
        setErrorConnect(t.toString());
    }

    @Override
    public void onDestroy() {
        mSubscriptions.clear();
        super.onDestroy();
    }

    private void setErrorConnect(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
        mView.findViewById(R.id.layout_error).setVisibility(View.VISIBLE);
    }
}
