package com.example.twins.nicolinska.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.twins.nicolinska.R;

public class ChooseQuantityGoodsFragment extends DialogFragment implements View.OnClickListener {
    private NumberPicker numberpicker;
    private TextView textview;
    private int mVal;
    private Button mBtnCancel, mBtnOk;
    private Context mContext;
    OnClickItemDialogValue onClickItemDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            onClickItemDialog = (OnClickItemDialogValue) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnClickItemDialogValue");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_choose_quantity_goods, container, false);
        getDialog().setTitle("Выберите количесво");
        numberpicker = (NumberPicker) mView.findViewById(R.id.numberPicker1);

        textview = (TextView) mView.findViewById(R.id.tv_title);
        textview.setText(getArguments().getString("title"));
        textview = (TextView) mView.findViewById(R.id.textView1);

        mBtnCancel = (Button) mView.findViewById(R.id.btn_no);
        mBtnCancel.setOnClickListener(this);
        mBtnOk = (Button) mView.findViewById(R.id.btn_yes);
        mBtnOk.setOnClickListener(this);

        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(100);
        numberpicker.setOnValueChangedListener((NumberPicker picker, int oldVal, int newVal) -> {
                mVal = newVal;
            textview.setText(getString(R.string.choose) + newVal);
        });
        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                onClickItemDialog.onArticleDialog(getArguments().getInt("position"), mVal);
            case R.id.btn_no:
                dismiss();
                break;
        }
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public interface OnClickItemDialogValue {
        void onArticleDialog(int position, int value);
    }

    @Override
    public void onDetach() {
        onClickItemDialog = null;
        super.onDetach();
    }
}
