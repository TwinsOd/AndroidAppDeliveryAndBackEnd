package com.example.twins.nicolinska.data;

import com.example.twins.nicolinska.Model.AnswerServer;
import com.example.twins.nicolinska.Model.PriceModel;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface ApiService {

    @FormUrlEncoded
    @POST("/app/your_name.php")
    Observable<AnswerServer> requestOrder(@FieldMap Map<String, String> map);

    @POST("/app/price.json")
    Observable<PriceModel> requestPrice();
}
