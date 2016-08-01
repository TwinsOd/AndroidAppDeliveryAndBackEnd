package com.example.twins.nicolinska.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.Interface.OnExitDialogListener;
import com.example.twins.nicolinska.R;

public class ButtonFragment extends android.preference.PreferenceFragment {
    private Context context;
    private OnExitDialogListener callback;

    public void newInstants(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.fragment_button);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = super.onCreateView(inflater, container, savedInstanceState);
        mView.setBackgroundColor(getResources().getColor(R.color.windowBackground));
        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();
        callback.onExitDialogListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            callback = (OnExitDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnExitDialogListener");
        }
    }
}
