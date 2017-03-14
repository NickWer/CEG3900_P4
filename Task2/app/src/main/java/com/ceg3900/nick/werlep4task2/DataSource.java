package com.ceg3900.nick.werlep4task2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.stream.Stream;

/**
 * Stupid class to send data from one activity to another. Originally was a parcel but I tried using
 * parcels and it was a completely awful experience.
 * Created by nick on 3/13/17.
 */

public class DataSource {
    public static Stream<String> dataStream;

}