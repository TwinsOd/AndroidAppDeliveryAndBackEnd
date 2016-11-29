package com.example.twins.nicolinska.data;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface ApiService {

    @FormUrlEncoded
    @POST("/app2/create_order.php")
    Observable<ResponseBody> setOrder(@FieldMap Map<String, String> map);

}
