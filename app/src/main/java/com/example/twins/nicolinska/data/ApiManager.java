package com.example.twins.nicolinska.data;

import android.support.annotation.NonNull;

import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Twins on 26.07.2016.
 */

public class ApiManager {
    private static final int RETRY_COUNT_FOR_REQUEST = 0;

    private ApiManager() {}
    @NonNull
    public static Observable<ResponseBody> getVideoInfo(Map<String, String> map) {
        return ApiFactory.getApiService()
                .setOrder(map)
                .retry(RETRY_COUNT_FOR_REQUEST)
                .map(response -> response);
    }
}
