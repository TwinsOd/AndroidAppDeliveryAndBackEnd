package com.example.twins.nicolinska.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.R;

public class MenuContactFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.contacts);
        return inflater.inflate(R.layout.fragment_menu_contact, container, false);
    }
}
