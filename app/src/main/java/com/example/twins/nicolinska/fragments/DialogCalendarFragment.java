package com.example.twins.nicolinska.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.twins.nicolinska.Interface.OnSendDataListener;
import com.example.twins.nicolinska.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class DialogCalendarFragment extends DialogFragment implements OnDateSelectedListener, View.OnClickListener {
    private Button mBtnCancel, mBtnOk;
    OnSendDataListener callback;
    private CalendarDay chooseDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_simple_calendar_dialog, container, false);
        mBtnCancel = (Button) mView.findViewById(R.id.btn_no);
        mBtnCancel.setOnClickListener(this);
        mBtnOk = (Button) mView.findViewById(R.id.btn_yes);
        mBtnOk.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle("Выберите дату доставки");

        MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);

        Calendar calendarTomorrow = Calendar.getInstance();
        calendarTomorrow.add(Calendar.DATE, 1);

        widget.setSelectedDate(calendarTomorrow);

        try {
            callback = (OnSendDataListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        chooseDay = date;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                if (chooseDay != null)
                    callback.onSendDataListener(chooseDay);

            case R.id.btn_no:
                dismiss();
                break;
        }
    }

}