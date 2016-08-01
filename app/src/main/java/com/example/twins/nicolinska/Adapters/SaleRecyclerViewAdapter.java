package com.example.twins.nicolinska.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twins.nicolinska.Model.SaleModel;
import com.example.twins.nicolinska.R;
import com.example.twins.nicolinska.fragments.OrderFragment;

import java.util.List;

/**
 * Created by Twins on 05.07.2016.
 */
public class SaleRecyclerViewAdapter extends RecyclerView.Adapter<SaleRecyclerViewAdapter.GoodsViewHolder> {

    List<SaleModel> saleModels;
    Context context;

    public SaleRecyclerViewAdapter(List<SaleModel> saleModels, Context context) {
        this.saleModels = saleModels;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sale, viewGroup, false);
        GoodsViewHolder pvh = new GoodsViewHolder(v, context);
        return pvh;
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder goodsViewHolder, int i) {
        goodsViewHolder.goodsName.setText(saleModels.get(i).title);
        goodsViewHolder.goodsPrice.setText(saleModels.get(i).description);
        if (saleModels.get(i).getValue() > 0)
            goodsViewHolder.value.setText(String.format("" + saleModels.get(i).getValue()));
    }

    @Override
    public int getItemCount() {
        return saleModels.size();
    }


    public static class GoodsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView goodsName;
        TextView goodsPrice;
        TextView value;

        GoodsViewHolder(View itemView, final Context context) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.recycler_view);
            goodsName = (TextView) itemView.findViewById(R.id.person_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.person_price);
            value = (TextView) itemView.findViewById(R.id.value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OrderFragment.OnHeadlineSelectedListener mCallback = (OrderFragment.OnHeadlineSelectedListener) context;
                    mCallback.onChooseQuaqly(getPosition(), context);

                }
            });
        }
    }
}