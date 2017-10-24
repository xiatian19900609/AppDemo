package com.xinfu.demo24;

import android.os.Parcel;
import android.os.Parcelable;

public class V4_2IndicatorBean implements Parcelable {
    public String date;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
    }

    public V4_2IndicatorBean() {
    }

    protected V4_2IndicatorBean(Parcel in) {
        this.date = in.readString();
    }

    public static final Parcelable.Creator<V4_2IndicatorBean> CREATOR = new Parcelable.Creator<V4_2IndicatorBean>() {
        @Override
        public V4_2IndicatorBean createFromParcel(Parcel source) {
            return new V4_2IndicatorBean(source);
        }

        @Override
        public V4_2IndicatorBean[] newArray(int size) {
            return new V4_2IndicatorBean[size];
        }
    };
}
