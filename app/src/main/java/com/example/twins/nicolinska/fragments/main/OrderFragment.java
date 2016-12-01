package com.example.twins.nicolinska.fragments.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twins.nicolinska.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragment extends Fragment implements View.OnClickListener {
    private ImageView imageView;
    private TextView tv20, tvTara, textView, tvLoadData;
    private Button btn;
    private int quantityButtle = 0;
    private int quantityTara = 0;
    private Animation animation;
    private View mView;
    private OnHeadlineSelectedListener mCallback;
    private Context mContext;
    FloatingActionButton mFab;
    private CheckBox boxPompa;
    SharedPreferences mSharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order, container, false);

        mFab = (FloatingActionButton) mView.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(this);

        imageView = (ImageView) mView.findViewById(R.id.ivMinus20);
        imageView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.ivPlus20);
        imageView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.ivTaraMinus);
        imageView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.ivTaraPlus);
        imageView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.ivBottle);
        imageView.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.image_edit);
        imageView.setOnClickListener(this);

        btn = (Button) mView.findViewById(R.id.button_little_battle);
        btn.setOnClickListener(this);
        btn = (Button) mView.findViewById(R.id.button_books);
        btn.setOnClickListener(this);

        boxPompa = (CheckBox) mView.findViewById(R.id.checkbox_pompa);
        boxPompa.setOnClickListener(this);

        tv20 = (TextView) mView.findViewById(R.id.tv20);
        tvTara = (TextView) mView.findViewById(R.id.tvTara);
        tvLoadData = (TextView) mView.findViewById(R.id.tv_load_data);
        tvLoadData.setOnClickListener(this);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        initialInfoContainer();

        return mView;
    }

    public void initialInfoContainer() {

        if (mSharedPreferences.getString("pref_address", "").length() > 3 &&
                mSharedPreferences.getString("pref_phone", "").length() > 3) {
            tvLoadData.setVisibility(View.GONE);

            setInfo(R.id.text_name, "pref_name");
            setInfo(R.id.text_place, "pref_address");
            setInfo(R.id.text_phone, "pref_phone");
            setInfo(R.id.text_email, "pref_email");
            setInfo(R.id.text_comments, "pref_comments");
        } else {
            tvLoadData.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMinus20:
                if (quantityButtle > 0) {
                    quantityButtle = quantityButtle - 1;
                    if (quantityTara > 0) {
                        quantityTara = quantityTara - 1;
                    }
                }
                outPrint();
                break;
            case R.id.ivPlus20:
                quantityButtle = quantityButtle + 1;
                quantityTara = quantityTara + 1;
                outPrint();
                break;
            case R.id.ivTaraMinus:
                if (quantityTara > 0) {
                    quantityTara = quantityTara - 1;
                }
                outPrint();
                break;
            case R.id.ivTaraPlus:
                quantityTara = quantityTara + 1;
                outPrint();
                break;
            case R.id.ivBottle:
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha);
                imageView = (ImageView) mView.findViewById(view.getId());
                imageView.startAnimation(animation);
                quantityButtle = quantityButtle + 1;
                quantityTara = quantityTara + 1;
                outPrint();
                break;
            case R.id.checkbox_pompa:
                visibleFab();
                break;
            case R.id.floatingActionButton:
                Map<String, String> mapOrder = new HashMap<>();
                mapOrder.put("bullon", Integer.toString(quantityButtle));
                if ((quantityButtle - quantityTara) > 0)
                    mapOrder.put("tara", Integer.toString(quantityButtle - quantityTara));
                if (boxPompa.isChecked()) mapOrder.put("pompa", "1");
                mCallback.onSendQrder(mapOrder, null);
            case R.id.button_little_battle:
            case R.id.button_books:
                mCallback.onArticleSelected(view.getId(), mContext);
                break;
            case R.id.tv_load_data:
            case R.id.image_edit:
                mCallback.onArticleSelectedFragment(this);
                mCallback.onArticleSelected(view.getId(), mContext);
                break;
        }
    }

    private void outPrint() {
        tv20.setText(Integer.toString(quantityButtle));
        tvTara.setText(Integer.toString(quantityTara));

        visibleFab();
    }

    private void visibleFab() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (quantityButtle > 0 | quantityTara > 0 | boxPompa.isChecked()) {
                if (tvLoadData.isCursorVisible()) mFab.setVisibility(View.VISIBLE);
            } else mFab.setVisibility(View.GONE);
        } else mFab.setVisibility(View.VISIBLE);
    }


    public interface OnHeadlineSelectedListener {
        void onArticleSelected(int position, Context context);

        void onArticleSelectedFragment(OrderFragment fragment);

        void onChooseQuaqly(int position, Context context);

        void onSendQrder(Map<String, String> mapOrder, List<String> listLittleButtle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        outPrint();
    }
}
