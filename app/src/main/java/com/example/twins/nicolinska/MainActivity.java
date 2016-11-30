package com.example.twins.nicolinska;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.twins.nicolinska.Interface.OnExitDialogListener;
import com.example.twins.nicolinska.Model.PriceModel;
import com.example.twins.nicolinska.Model.SaleModel;
import com.example.twins.nicolinska.data.ApiManager;
import com.example.twins.nicolinska.fragments.ButtonFragment;
import com.example.twins.nicolinska.fragments.ChooseQuantityGoodsFragment;
import com.example.twins.nicolinska.fragments.SaleFragment;
import com.example.twins.nicolinska.fragments.SendFragment;
import com.example.twins.nicolinska.fragments.main.MainFragment;
import com.example.twins.nicolinska.fragments.main.OrderFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements OrderFragment.OnHeadlineSelectedListener,
        ChooseQuantityGoodsFragment.OnClickItemDialogValue, OnExitDialogListener {
    public static final String PRICE_JSON = "price_json";
    private FragmentTransaction transaction;
    private List<SaleModel> mSaleList = new ArrayList<>();
    private SaleFragment saleFragment;
    private Map<String, String> mapOrder;
    private Map<String, SaleModel> listGoods = new HashMap<>();
    private OrderFragment orderFragment = null;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();

        sharedPref = getSharedPreferences(this.getLocalClassName(), MODE_PRIVATE);
        loadPrice();
    }

    @Override
    public void onArticleSelected(int position, Context context) {
        saleFragment = new SaleFragment();
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        FragmentTransaction trV4 = getSupportFragmentManager().beginTransaction();
        trV4.setCustomAnimations(R.anim.slide_up_animation, R.anim.slide_down_animation,
                R.anim.slide_up_animation, R.anim.slide_down_animation);
        if (mSaleList.size() > 0) mSaleList.clear();
        switch (position) {
            case R.id.button_little_battle:
                initializeDataLittleBatke();
                saleFragment.newInstance(mSaleList, listGoods, context);
                trV4.add(R.id.container, saleFragment);
                trV4.addToBackStack("SaleFragment");
                trV4.commit();
                break;
            case R.id.button_books:
                initializeDataDooks();
                saleFragment.newInstance(mSaleList, listGoods, context);
                trV4.add(R.id.container, saleFragment);
                trV4.addToBackStack("ChooseQuantityGoodsFragment");
                trV4.commit();
                break;
            case R.id.tv_load_data:
            case R.id.image_edit:
                ButtonFragment buttonFragment = new ButtonFragment();
                buttonFragment.newInstants(context);
                transaction.replace(R.id.container, buttonFragment);
                transaction.addToBackStack("ButtonFragment");
                transaction.commit();
                break;
            case R.id.floatingActionButton:
                SendFragment sendFragment = new SendFragment();
                sendFragment.newInstance(mapOrder, listGoods);
                trV4.add(R.id.container, sendFragment);
                trV4.addToBackStack("SendFragment");
                trV4.commit();
                break;
        }
    }

    @Override
    public void onArticleSelectedFragment(OrderFragment fragment) {
        orderFragment = fragment;
    }

    @Override
    public void onChooseQuaqly(int position, Context context) {
        DialogFragment dialogFragment = new ChooseQuantityGoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", mSaleList.get(position).title);
        bundle.putInt("position", position);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getFragmentManager(), "dialogFragment");
    }

    @Override
    public void onSendQrder(Map<String, String> mapOrder, List<String> listLittleButtle) {
        this.mapOrder = mapOrder;
    }

    @Override
    public void onArticleDialog(int position, int value) {
        saleFragment.updateList(position, value);
        listGoods.put(mSaleList.get(position).name, mSaleList.get(position));
    }

    @Override
    public void onExitDialogListener() {
        orderFragment.initialInfoContainer();
    }

    private void initializeDataLittleBatke() {
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 5,0 л н/г", "При заказе: \n" +
                "От 1 упаковки(2шт.) – " + priceModel.getRozniza().getWater5() + " грн.\n" +
                "От 50 упаковок – " + priceModel.getOpt().getWater5() + " грн.",
                "water5",
                priceModel.getRozniza().getWater5(),
                priceModel.getOpt().getWater5()));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 1,75 л н/г", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + priceModel.getRozniza().getWater175() + " грн.\n" +
                "От 50 упаковок – " + priceModel.getOpt().getWater175() + " грн.",
                "water175",
                priceModel.getRozniza().getWater175(),
                priceModel.getOpt().getWater175()));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 1,75 л газ", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + priceModel.getRozniza().getWater175g() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater175g() + " грн.",
                "water175g",
                priceModel.getRozniza().getWater175g(),
                priceModel.getOpt().getWater175g()));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л н/г", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05() + " грн.",
                "water05",
                priceModel.getRozniza().getWater05(),
                priceModel.getOpt().getWater05()));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л газ", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05g() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05g() + " грн.",
                "water05g",
                priceModel.getRozniza().getWater05g(),
                priceModel.getOpt().getWater05g()));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л н/г спорт", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + priceModel.getRozniza().getWater05Sport() + " грн.\n" +
                "От 10 упаковок – " + priceModel.getOpt().getWater05Sport() + " грн.",
                "water05Sport",
                priceModel.getRozniza().getWater05Sport(),
                priceModel.getOpt().getWater05Sport()));
    }

    private void initializeDataDooks() {
        mSaleList.add(new SaleModel("Книга \"Атеросклероза может не быть\".",
                "Цена: " + priceModel.getRozniza().getBookAtereSkleroza() + "грн.",
                "bookAtereSkleroza",
                priceModel.getRozniza().getBookAtereSkleroza(), 0));
        mSaleList.add(new SaleModel("Книга \"Вода здоровья и долголетия\".",
                "Цена: " + priceModel.getRozniza().getBookVodaZdorovya() + "грн.",
                "bookVodaZdorovya",
                priceModel.getRozniza().getBookVodaZdorovya(), 0));
        mSaleList.add(new SaleModel("Книга \"Здоровье матери и ребёнка\".",
                "Цена: " + priceModel.getRozniza().getBookZdoveMateriRebenka() + "грн.",
                "bookZdoveMateriRebenka",
                priceModel.getRozniza().getBookZdoveMateriRebenka(), 0));
        mSaleList.add(new SaleModel("Книга \"Как родить здорового малыша\".",
                "Цена: " + priceModel.getRozniza().getBookKakRoditZdorovogoBaby() + "грн.",
                "bookKakRoditZdorovogoBaby",
                priceModel.getRozniza().getBookKakRoditZdorovogoBaby(), 0));
        mSaleList.add(new SaleModel("Книга \"Как продлить быстротечную жизнь\".",
                "Цена: " + priceModel.getRozniza().getBookKakProdlitJizn() + "грн.",
                "bookKakProdlitJizn",
                priceModel.getRozniza().getBookKakProdlitJizn(), 0));
        mSaleList.add(new SaleModel("Книга \"Почему мы полнеем\".",
                "Цена: " + priceModel.getRozniza().getBookPochemyMiPolneem() + "грн.",
                "bookPochemyMiPolneem",
                priceModel.getRozniza().getBookPochemyMiPolneem(), 0));
        mSaleList.add(new SaleModel("Книга \"Правильное питание против болезней\".",
                "Цена: " + priceModel.getRozniza().getBookPravilnoePitanie() + "грн.",
                "bookPravilnoePitanie",
                priceModel.getRozniza().getBookPravilnoePitanie(), 0));
    }

    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    public PriceModel priceModel;

    private void loadPrice() {
        Subscription subscription = ApiManager.getPrice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataSuccess, this::onDataError);
        mSubscriptions.add(subscription);
    }

    private void onDataSuccess(PriceModel model) {
        Log.i("MyLog", "MainActivity_onDataSuccess**********************************");
        Log.i("MyLog", "getBallon = " + model.getRozniza().getBallon());
        Log.i("MyLog", "getWater05 = " + model.getRozniza().getWater05());
        Log.i("MyLog", "getOpt().getWater05() = " + model.getOpt().getWater05());
        priceModel = model;

        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putString(PRICE_JSON, model.toJSON());
        ed.apply();
    }

    private void onDataError(Throwable throwable) {
        Log.i("MyLog", "onDataError" + throwable.toString());

        Toast.makeText(this, "error connection!!!", Toast.LENGTH_SHORT).show();

        String jsonPrice = sharedPref.getString(PRICE_JSON, "");
        ObjectMapper mapper = new ObjectMapper();

        try {
            priceModel = mapper.readValue(jsonPrice, PriceModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "error get price!!!", Toast.LENGTH_SHORT).show();
        }
    }

}