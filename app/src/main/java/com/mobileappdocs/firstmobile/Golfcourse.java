package com.mobileappdocs.firstmobile;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michaelHahn on 5/10/15.
 */
public class Golfcourse implements Parcelable {
    // Golf course attributes
    String  name = "None";
    String  address = "None";
    int holes =18;
    boolean isPublic;

    // Constructor for course
    Golfcourse(String name) {
        this.name = name;
    }
    // Text representation of the class
    public String toString () {
        return name;
    }

    // Parcelable implementation
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }
    public static final Parcelable.Creator<Golfcourse> CREATOR
            = new Parcelable.Creator<Golfcourse>() {
        public Golfcourse createFromParcel(Parcel in) {
            return new Golfcourse(in);
        }
        public Golfcourse[] newArray(int size) {
            return new Golfcourse[size];
        }
    };
    private Golfcourse(Parcel in) {
        name = in.readString();
    }
}
