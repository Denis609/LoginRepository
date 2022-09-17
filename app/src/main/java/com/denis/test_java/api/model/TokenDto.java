package com.denis.test_java.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TokenDto implements Parcelable {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private String expiresIn;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @SerializedName("scope")
    private String scope;

    protected TokenDto(Parcel in) {
        accessToken = in.readString();
        refreshToken = in.readString();
    }

    public static final Creator<TokenDto> CREATOR = new Creator<TokenDto>() {
        @Override
        public TokenDto createFromParcel(Parcel in) {
            return new TokenDto(in);
        }

        @Override
        public TokenDto[] newArray(int size) {
            return new TokenDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(accessToken);
        parcel.writeString(refreshToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
