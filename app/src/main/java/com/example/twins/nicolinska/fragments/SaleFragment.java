package com.example.twins.nicolinska.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.Adapters.SaleRecyclerViewAdapter;
import com.example.twins.nicolinska.Model.SaleModel;
import com.example.twins.nicolinska.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class SaleFragment extends Fragment {
    private List<SaleModel> mSaleList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private Toolbar mToolbar;
    private Context mContext;
    SaleRecyclerViewAdapter adapter;

    public void newInstance(List<SaleModel> saleList, Map<String, SaleModel> listGoods, Context context) {
        this.mSaleList = saleList;
        for (Map.Entry<String, SaleModel> entry : listGoods.entrySet()) {
            String name = entry.getValue().name;
            for (int i = 0; i < mSaleList.size(); i++) {
                if (mSaleList.get(i).name.equals(name)) {
                    mSaleList.get(i).setValue(entry.getValue().getValue());
                }
            }
        }
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_sale, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view_little_battle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar_fragment);
        mToolbar.setTitle("Товары");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        createAdapter();
        return mView;
    }

    public void updateList(int position, int value) {
        mSaleList.get(position).setValue(value);
        adapter.notifyDataSetChanged();
    }

    private void createAdapter() {
        adapter = new SaleRecyclerViewAdapter(mSaleList, mContext);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(scaleAdapter);
    }
}
