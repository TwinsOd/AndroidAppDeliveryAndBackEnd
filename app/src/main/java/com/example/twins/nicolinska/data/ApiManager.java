package com.example.twins.nicolinska.data;

import android.support.annotation.NonNull;

import com.example.twins.nicolinska.Model.AnswerServer;
import com.example.twins.nicolinska.Model.PriceModel;

import java.util.Map;

import rx.Observable;

public class ApiManager {
    private static final int RETRY_COUNT_FOR_REQUEST = 0;

    private ApiManager() {}
    @NonNull
    public static Observable<AnswerServer> setOrder(Map<String, String> map) {
        return ApiFactory.getApiService()
                .requestOrder(map)
                .retry(RETRY_COUNT_FOR_REQUEST)
                .map(response -> response);
    }

    @NonNull
    public static Observable<PriceModel> getPrice() {
        return ApiFactory.getApiService()
                .requestPrice()
                .retry(RETRY_COUNT_FOR_REQUEST)
                .map(response -> response);
    }
}
