package com.example.twins.nicolinska.data;

import com.example.twins.nicolinska.Model.AnswerServer;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface ApiService {

    @FormUrlEncoded
    @POST("/app2/create_order.php")
    Observable<AnswerServer> setOrder(@FieldMap Map<String, String> map);

}
