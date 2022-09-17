package com.denis.test.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.denis.test.api.model.TokenDto;
import com.denis.test.util.Jsons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TokenSharedPrefs {
    private static final String name = "MAIN";
    private final Gson gson = Jsons.create("dd.MM.yyyy");

    private final SharedPreferences prefs;

    public TokenSharedPrefs(Context context) {
        prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public Boolean authorized() {
        TokenDto tokenDto = get(new TypeToken<TokenDto>(){});
        return tokenDto != null;
    }

    public TokenDto authToken() {
        return get(new TypeToken<TokenDto>(){});
    }


    public void save(TokenDto data) {
        prefs.edit().putString("TOKEN", gson.toJson(data)).apply();
    }

    public TokenDto get(TypeToken<TokenDto> typeToken) {
        return gson.fromJson(prefs.getString("TOKEN", ""), typeToken.getType());
    }

    public void delete() {
        prefs.edit().remove("TOKEN").apply();
    }
}
