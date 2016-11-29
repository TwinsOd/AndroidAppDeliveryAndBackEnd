package com.example.twins.nicolinska.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.twins.nicolinska.R;


public class DialogInfoSendOrderFragment extends DialogFragment implements View.OnClickListener {
    private Button mBtnOk;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_info_send_order, container, false);
//        getDialog().setTitle(R.string.info_to_order);
        mBtnOk = (Button) mView.findViewById(R.id.btn_yes);
        mBtnOk.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                dismiss();
                getActivity().onBackPressed();
                break;
        }
    }
}
