package com.denis.test.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Jsons {
    static public Gson create(String dateFormat) {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat(dateFormat)
                .create();
    }
}
