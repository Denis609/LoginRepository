package com.denis.test_java.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDto implements Parcelable {
    @SerializedName("roleId")
    private String roleId;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @SerializedName("permissions")
    private List<String> permissions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected UserDto(Parcel in) {
        roleId = in.readString();
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<UserDto> CREATOR = new Creator<UserDto>() {
        @Override
        public UserDto createFromParcel(Parcel in) {
            return new UserDto(in);
        }

        @Override
        public UserDto[] newArray(int size) {
            return new UserDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(roleId);
        parcel.writeString(username);
        parcel.writeString(email);
    }
}
