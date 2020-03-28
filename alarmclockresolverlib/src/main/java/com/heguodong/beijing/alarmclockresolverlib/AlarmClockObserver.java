package com.heguodong.beijing.alarmclockresolverlib;


import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Created by 何国栋 on 2020/3/28.
 */

public class AlarmClockObserver extends ContentObserver {

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public AlarmClockObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        //TODO
        Log.e("heguodong3","------AlarmClockObserver------onChange");
    }
}
