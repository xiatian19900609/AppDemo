package com.xinfu.demo18;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/30
 *     desc   :
 * </pre>
 */
public class Info implements Parcelable {
    public String url;
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
    }

    public Info(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
