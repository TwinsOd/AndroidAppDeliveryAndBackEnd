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
import android.widget.TextView;

import com.example.twins.nicolinska.Const.PriceProduct;
import com.example.twins.nicolinska.Interface.OnSendDataListener;
import com.example.twins.nicolinska.Model.SaleModel;
import com.example.twins.nicolinska.R;
import com.example.twins.nicolinska.data.ApiManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class SendFragment extends Fragment implements OnSendDataListener {
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    private final String URL = "http://vo-da.com";
    private SharedPreferences mSharedPreferences;
    private List<String> arrayListOrder = new ArrayList<>();
    private Map<String, String> mapOrder = new HashMap<>();
    private Map<String, SaleModel> listGoods;
    private double summa = 0;
    private Toolbar mToolbar;
    private TextView textView;
    private int year, month, day, dayWeek;
    private View mView;

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

        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar_fragment);
        mToolbar.setTitle("Заказ");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        textView = (TextView) mView.findViewById(R.id.text_name);
        textView.setText(mSharedPreferences.getString("pref_name", ""));
        textView = (TextView) mView.findViewById(R.id.text_place);
        textView.setText(mSharedPreferences.getString("pref_address", ""));
        textView = (TextView) mView.findViewById(R.id.text_phone);
        textView.setText(mSharedPreferences.getString("pref_phone", ""));
        textView = (TextView) mView.findViewById(R.id.text_email);
        textView.setText(mSharedPreferences.getString("pref_email", ""));
        textView = (TextView) mView.findViewById(R.id.text_comments);
        textView.setText(mSharedPreferences.getString("pref_comments", ""));

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        printDate(calendar);
        LinearLayout linearLayoutDate = (LinearLayout) mView.findViewById(R.id.liner_layout_summa_date);
        linearLayoutDate.setOnClickListener(v -> startDialogData());

        Button buttonSend = (Button) mView.findViewById(R.id.btn_send_to_server);

        ListView listOrder = (ListView) mView.findViewById(R.id.list_view_order);
        loadArrayListOrder();

        textView = (TextView) mView.findViewById(R.id.tv_summa);
        textView.setText(String.valueOf(summa));

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.item_order, arrayListOrder);
        listOrder.setAdapter(adapter);

        buttonSend.setOnClickListener(v -> {
                sendData();
                new DialogInfoSendOrderFragment().show(getFragmentManager(), "info_send_order_fragment");
        });

        return mView;
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
            price = Integer.parseInt(mapOrder.get("bullon")) * PriceProduct.roznica.ballon;
            arrayListOrder.add("Баллон: " + mapOrder.get("bullon") + " шт." + " = " + price + " грн");
            summa += price;
        } else if (Integer.parseInt(mapOrder.get("bullon")) >= 10) {
            price = Integer.parseInt(mapOrder.get("bullon")) * PriceProduct.opt.bullon;
            arrayListOrder.add("Баллон: " + mapOrder.get("bullon") + " шт." + " = " + price + " грн");
            summa += price;
        }
        if (mapOrder.containsKey("tara")) {
            price = Integer.parseInt(mapOrder.get("tara")) * PriceProduct.roznica.tara;
            arrayListOrder.add("Возратная тара: " + mapOrder.get("tara") + " шт." + " = " + price + " грн");
            summa += price;
        }
        if (mapOrder.containsKey("pompa")) {
            price = Integer.parseInt(mapOrder.get("pompa")) * PriceProduct.roznica.pompa;
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
        mapOrder.put("address", mSharedPreferences.getString("pref_address ", ""));
        mapOrder.put("phones", mSharedPreferences.getString("pref_phone", ""));
        mapOrder.put("summa", String.valueOf(summa));
        mapOrder.put("dateorder", year + "-" + (month + 1) + "-" + day);
        if (mSharedPreferences.getBoolean("pref_enable_confirmation", false))
            mapOrder.put("email", mSharedPreferences.getString("pref_email", ""));
        if (mSharedPreferences.getString("pref_comments", "").length() > 0)
            mapOrder.put("comments", mSharedPreferences.getString("pref_comments", ""));

        Subscription subscription = ApiManager.getVideoInfo(mapOrder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataSuccess, this::onDataError);
        mSubscriptions.add(subscription);
    }

    private void onDataSuccess(ResponseBody answerServer) {
//        Log.i("MyLog", "message = " + answerServer.getMessage());
//        Log.i("MyLog", "success = " + answerServer.getSuccess());
        try {
            Log.i("MyLog", "toString = " + answerServer.string());
            //toString = п»ї{"success":"1","message":"Product successfully created."}
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyLog", "IOException_toString = " + e.toString());
        }
    }

    private void onDataError(Throwable t) {
        //TODO say to user that there is no Internet
        Log.i("MyLog", "onDataError " + t.toString());
    }

    @Override
    public void onDestroy() {
        mSubscriptions.clear();
        super.onDestroy();
    }
}
