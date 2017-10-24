package com.xinfu.demo24;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/23
 *     desc   :
 * </pre>
 */
public class CalenderBen implements Parcelable {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
    }

    public CalenderBen() {
    }

    protected CalenderBen(Parcel in) {
        this.date = in.readString();
    }

    public static final Parcelable.Creator<CalenderBen> CREATOR = new Parcelable.Creator<CalenderBen>() {
        @Override
        public CalenderBen createFromParcel(Parcel source) {
            return new CalenderBen(source);
        }

        @Override
        public CalenderBen[] newArray(int size) {
            return new CalenderBen[size];
        }
    };
}
