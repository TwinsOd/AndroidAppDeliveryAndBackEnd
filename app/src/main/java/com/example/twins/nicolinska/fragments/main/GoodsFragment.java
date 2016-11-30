package com.example.twins.nicolinska.fragments.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twins.nicolinska.Adapters.GoodsRecyclerViewAdapter;
import com.example.twins.nicolinska.Model.Goods;
import com.example.twins.nicolinska.Model.PriceModel;
import com.example.twins.nicolinska.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static android.content.Context.MODE_PRIVATE;
import static com.example.twins.nicolinska.MainActivity.PRICE_JSON;


public class GoodsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Goods> goodsList;
    private PriceModel priceModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_goods, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        getPriceFromSharedPref();
        initializeData();
        initializeAdapter();
        return mView;
    }

    private void getPriceFromSharedPref() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getActivity().getLocalClassName(), MODE_PRIVATE);
        String jsonPrice = sharedPref.getString(PRICE_JSON, "");
        ObjectMapper mapper = new ObjectMapper();

        try {
            priceModel = mapper.readValue(jsonPrice, PriceModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyLog", "error_GoodsFragment_getPriceFromSharedPref");
        }
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
                "От 1 шт. – " + priceModel.getRozniza().getBallon() + " грн.\n" +
                "От 10 шт. – " + priceModel.getOpt().getBallon() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_5, "Вода \"Миколiнська\" 5,0 л н/г", "При заказе: \n" +
                "От 1 упаковки(2шт.) – " + priceModel.getRozniza().getWater5() + " грн.\n" +
                "От 50 упаковок – " + priceModel.getOpt().getWater5() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_175_ngaz, "Вода \"Миколiнська\" 1,75 л н/г", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + priceModel.getRozniza().getWater175() + " грн.\n" +
                "От 50 упаковок – " + priceModel.getOpt().getWater175() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_175_gaz, "Вода \"Миколiнська\" 1,75 л газ", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + priceModel.getRozniza().getWater175g() + " грн.\n" +
                "От 50 упаковок – " + priceModel.getOpt().getWater175g() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_05_ngaz, "Вода \"Миколiнська\" 0,5 л н/г", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_05_gaz, "Вода \"Миколiнська\" 0,5 л газ", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05g() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05g() + " грн."));
        goodsList.add(new Goods(R.drawable.small_shop_items_catalog_sport, "Вода \"Миколiнська\" 0,5 л н/г спорт", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05Sport() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05Sport() + " грн."));
        goodsList.add(new Goods(R.drawable.pompa, "  Помпа",
                "  " + priceModel.getRozniza().getPompa() + " грн."));
        goodsList.add(new Goods(R.drawable.book_atere_skleroza, "Книга \"Атеросклероза может не быть\".",
                "Цена: " + priceModel.getRozniza().getBookAtereSkleroza() + "грн."));
        goodsList.add(new Goods(R.drawable.book_voda_zdorovya, "Книга \"Вода здоровья и долголетия\".",
                "Цена: " + priceModel.getRozniza().getBookVodaZdorovya() + "грн."));
        goodsList.add(new Goods(R.drawable.book_zdove_materi_rebenka, "Книга \"Здоровье матери и ребёнка\".",
                "Цена: " + priceModel.getRozniza().getBookZdoveMateriRebenka() + "грн."));
        goodsList.add(new Goods(R.drawable.book_kak_rodit_zdorovogo_baby, "Книга \"Как родить здорового малыша\".",
                "Цена: " + priceModel.getRozniza().getBookKakRoditZdorovogoBaby() + "грн."));
        goodsList.add(new Goods(R.drawable.kak_prodlit_jizn, "Книга \"Как продлить быстротечную жизнь\".",
                "Цена: " + priceModel.getRozniza().getBookKakProdlitJizn() + "грн."));
        goodsList.add(new Goods(R.drawable.book_poxhemy_mi_polneem, "Книга \"Почему мы полнеем\".",
                "Цена: " + priceModel.getRozniza().getBookPochemyMiPolneem() + "грн."));
        goodsList.add(new Goods(R.drawable.book_pravilnoe_pitanie, "Книга \"Правильное питание против болезней\".",
                "Цена: " + priceModel.getRozniza().getBookPravilnoePitanie() + "грн."));
    }

}
