package com.xinfu.demo2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/02
 *     desc   :
 * </pre>
 */
public class UseInfo implements Parcelable {
    public String url;
    public String des;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.des);
    }

    public UseInfo() {
    }

    protected UseInfo(Parcel in) {
        this.url = in.readString();
        this.des = in.readString();
    }

    public static final Creator<UseInfo> CREATOR = new Creator<UseInfo>() {
        @Override
        public UseInfo createFromParcel(Parcel source) {
            return new UseInfo(source);
        }

        @Override
        public UseInfo[] newArray(int size) {
            return new UseInfo[size];
        }
    };
}
