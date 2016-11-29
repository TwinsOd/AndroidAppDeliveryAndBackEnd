package com.example.twins.nicolinska.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.Adapters.GoodsRecyclerViewAdapter;
import com.example.twins.nicolinska.Const.PriceProduct;
import com.example.twins.nicolinska.Model.Goods;
import com.example.twins.nicolinska.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class GoodsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Goods> goodsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_goods, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        initializeData();
        initializeAdapter();
        return mView;
    }

    private void initializeAdapter() {
        GoodsRecyclerViewAdapter adapter = new GoodsRecyclerViewAdapter(goodsList);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(scaleAdapter);
    }


    private void initializeData() {
        goodsList = new ArrayList<>();
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_20, "Вода \"Миколiнська\" 18,9 л н/г", "При заказе: \n" +
                "От 1 шт. – " + PriceProduct.roznica.ballon + " грн.\n" +
                "От 10 шт. – " + PriceProduct.opt.bullon + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_5, "Вода \"Миколiнська\" 5,0 л н/г", "При заказе: \n" +
                "От 1 упаковки(2шт.) – " + PriceProduct.roznica.water5 + " грн.\n" +
                "От 50 упаковок – " + PriceProduct.opt.water5 + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_175_ngaz, "Вода \"Миколiнська\" 1,75 л н/г", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + PriceProduct.roznica.water175 + " грн.\n" +
                "От 50 упаковок – " + PriceProduct.opt.water175 + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_175_gaz, "Вода \"Миколiнська\" 1,75 л газ", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + PriceProduct.roznica.water175g + " грн.\n" +
                "От 50 упаковок – " + PriceProduct.opt.water175g + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_05_ngaz, "Вода \"Миколiнська\" 0,5 л н/г", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05 + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05 + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_05_gaz, "Вода \"Миколiнська\" 0,5 л газ", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05g + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05g + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_sport, "Вода \"Миколiнська\" 0,5 л н/г спорт", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05Sport + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05Sport + " грн."));
        goodsList.add(new Goods(R.drawable.pompa, "  Помпа",
                "  " + PriceProduct.roznica.pompa + " грн."));
        goodsList.add(new Goods(R.drawable.book_atere_skleroza, "Книга \"Атеросклероза может не быть\".",
                "Цена: 50.00грн."));
        goodsList.add(new Goods(R.drawable.book_voda_zdorovya, "Книга \"Вода здоровья и долголетия\".",
                "Цена: 50.00грн."));
        goodsList.add(new Goods(R.drawable.book_zdove_materi_rebenka, "Книга \"Здоровье матери и ребёнка\".",
                "Цена: 50.00грн."));
        goodsList.add(new Goods(R.drawable.book_kak_rodit_zdorovogo_baby, "Книга \"Как родить здорового малыша\".",
                "Цена: 50.00грн."));
        goodsList.add(new Goods(R.drawable.kak_prodlit_jizn, "Книга \"Как продлить быстротечную жизнь\".",
                "Цена: 90.00грн."));
        goodsList.add(new Goods(R.drawable.book_poxhemy_mi_polneem, "Книга \"Почему мы полнеем\".",
                "Цена: 50.00грн."));
        goodsList.add(new Goods(R.drawable.book_pravilnoe_pitanie, "Книга \"Правильное питание против болезней\".",
                "Цена: 50.00грн."));
    }

}
