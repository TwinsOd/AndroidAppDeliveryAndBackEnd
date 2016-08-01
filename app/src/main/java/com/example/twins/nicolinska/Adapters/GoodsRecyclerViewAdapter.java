package com.example.twins.nicolinska.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twins.nicolinska.Model.Goods;
import com.example.twins.nicolinska.R;

import java.util.List;

/**
 * Created by Twins on 05.07.2016.
 */
public class GoodsRecyclerViewAdapter extends RecyclerView.Adapter<GoodsRecyclerViewAdapter.GoodsViewHolder> {

    public static class GoodsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView goodsName;
        TextView goodsPrice;
        ImageView goodsPhoto;

        GoodsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recycler_view);
            goodsName = (TextView) itemView.findViewById(R.id.person_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.person_age);
            goodsPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }

    List<Goods> goodsList;

    public GoodsRecyclerViewAdapter(List<Goods> persons) {
        this.goodsList = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_goods, viewGroup, false);
        GoodsViewHolder pvh = new GoodsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder goodsViewHolder, int i) {
        goodsViewHolder.goodsName.setText(goodsList.get(i).name);
        goodsViewHolder.goodsPrice.setText(goodsList.get(i).price);
        goodsViewHolder.goodsPhoto.setImageResource(goodsList.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }
}