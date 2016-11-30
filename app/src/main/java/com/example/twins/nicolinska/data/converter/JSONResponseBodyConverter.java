package com.example.twins.nicolinska.data.converter;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JSONResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectReader adapter;

    JSONResponseBodyConverter(ObjectReader adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return adapter.readValue(fixJSON(value));
        } finally {
            value.close();
        }
    }

    private Reader fixJSON(ResponseBody reader) {
        String string = null;
        try {
            string = reader.string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("MyLog", "IOException = " + e.toString());
            string = "\\{\"success\":\"0\",\"message\":\"error IOException\"}";
        }
//for load price
        if (string.length() > 77) return new StringReader(string);

//for load answerServer
        String[] listStr = string.split("\\{");
        String newStr = "{" + listStr[1];
        Log.i("MyLog", "fixJSON_string = reader.toString()=  " + string);
        Log.i("MyLog", "fixJSON_newStr = reader.toString()=  " + newStr);

        return new StringReader(newStr);
    }
}
