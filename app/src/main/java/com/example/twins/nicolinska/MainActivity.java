package com.example.twins.nicolinska;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.twins.nicolinska.Const.PriceProduct;
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
    private FragmentTransaction transaction;
    private List<SaleModel> mSaleList = new ArrayList<>();
    private SaleFragment saleFragment;
    private Map<String, String> mapOrder;
    private Map<String, SaleModel> listGoods = new HashMap<>();
    OrderFragment orderFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();

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
                "От 1 упаковки(2шт.) – " + PriceProduct.roznica.water5 + " грн.\n" +
                "От 50 упаковок – " + PriceProduct.opt.water5 + " грн.",
                "water5",
                PriceProduct.roznica.water5,
                PriceProduct.opt.water5));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 1,75 л н/г", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + PriceProduct.roznica.water175 + " грн.\n" +
                "От 50 упаковок – " + PriceProduct.opt.water175 + " грн.",
                "water175",
                PriceProduct.roznica.water175,
                PriceProduct.opt.water175));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 1,75 л газ", "При заказе: \n" +
                "От 1 упаковки(6шт.) – " + PriceProduct.roznica.water175g + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water175g + " грн.",
                "water175g",
                PriceProduct.roznica.water175g,
                PriceProduct.opt.water175g));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л н/г", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05 + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05 + " грн.",
                "water05",
                PriceProduct.roznica.water05,
                PriceProduct.opt.water05));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л газ", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05g + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05g + " грн.",
                "water05g",
                PriceProduct.roznica.water05g,
                PriceProduct.opt.water05g));
        mSaleList.add(new SaleModel("Вода \"Миколiнська\" 0,5 л н/г спорт", "При заказе: \n" +
                "От 1 упаковки(12шт.) – " + PriceProduct.roznica.water05Sport + " грн.\n" +
                "От 10 упаковок – " + PriceProduct.opt.water05Sport + " грн.",
                "water05Sport",
                PriceProduct.roznica.water05Sport,
                PriceProduct.opt.water05Sport));
    }

    private void initializeDataDooks() {
        mSaleList.add(new SaleModel("Книга \"Атеросклероза может не быть\".",
                "Цена: 50.00грн.",
                "bookAtereSkleroza",
                PriceProduct.roznica.bookAtereSkleroza, 0));
        mSaleList.add(new SaleModel("Книга \"Вода здоровья и долголетия\".",
                "Цена: 50.00грн.",
                "bookVodaZdorovya",
                PriceProduct.roznica.bookVodaZdorovya, 0));
        mSaleList.add(new SaleModel("Книга \"Здоровье матери и ребёнка\".",
                "Цена: 50.00грн.",
                "bookZdoveMateriRebenka",
                PriceProduct.roznica.bookZdoveMateriRebenka, 0));
        mSaleList.add(new SaleModel("Книга \"Как родить здорового малыша\".",
                "Цена: 50.00грн.",
                "bookKakRoditZdorovogoBaby",
                PriceProduct.roznica.bookKakRoditZdorovogoBaby, 0));
        mSaleList.add(new SaleModel("Книга \"Как продлить быстротечную жизнь\".",
                "Цена: 90.00грн.",
                "bookKakProdlitJizn",
                PriceProduct.roznica.bookKakProdlitJizn, 0));
        mSaleList.add(new SaleModel("Книга \"Почему мы полнеем\".",
                "Цена: 50.00грн.",
                "bookPochemyMiPolneem",
                PriceProduct.roznica.bookPochemyMiPolneem, 0));
        mSaleList.add(new SaleModel("Книга \"Правильное питание против болезней\".",
                "Цена: 50.00грн.",
                "bookPravilnoePitanie",
                PriceProduct.roznica.bookPravilnoePitanie, 0));
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
        Log.i("MyLog", "onDataSuccess**********************************");
        Log.i("MyLog", "getBallon = " + model.getRozniza().getBallon());
        Log.i("MyLog", "getWater05 = " + model.getRozniza().getWater05());
        Log.i("MyLog", "getOpt().getWater05() = " + model.getOpt().getWater05());
        priceModel = model;
    }

    private void onDataError(Throwable throwable) {
        Log.i("MyLog", "onDataError" + throwable.toString());
    }

}