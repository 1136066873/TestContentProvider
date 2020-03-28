package com.heguodong.beijing.alarmclockresolverlib;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.heguodong.beijing.alarmclockbaselib.Constants;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 何国栋 on 2020/3/25.
 */

public class AlarmClockResolver{

    private static Context mContext;

    private static final Uri targetUri;

    private volatile static ContentResolver contentResolver = null;

    private volatile static AlarmClockResolver instance = null;

    private CopyOnWriteArrayList<AlarmClockContentListener> mAlarmClockContentListeners
            = new CopyOnWriteArrayList<>();

    private AlarmClockResolver(Context context) {
        mContext = context.getApplicationContext();
    }

    static {
        //AlarmClockDatabaseHelper.DATABASE_TABLE_NAME
        targetUri = Uri.parse("content://com.heguodong.beijing.alarmclockproviderlib.AlarmClockProvider" );//test
    }

    public static AlarmClockResolver getInstance(Context context){
        if (instance == null){
            synchronized (AlarmClockResolver.class){
                if (instance == null){
                    mContext = context.getApplicationContext();
                    contentResolver = context.getContentResolver();
                    instance = new AlarmClockResolver(mContext);
                }
            }
        }
        return instance ;
    }

    /**
     *  添加闹钟数据到数据库
     * @param contentValues
     * @return
     */
    public Uri insert(ContentValues contentValues){
        synchronized (AlarmClockResolver.class){
            try {
                return contentResolver.insert(targetUri,contentValues);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 删除指定 ID 的闹钟数据
     * @param parameterAlarmID
     * @return
     */
    public int delete(String parameterAlarmID){
        synchronized (AlarmClockResolver.class){
            try {
                return contentResolver.delete(targetUri ,
                        Constants.ALARM_ID + " = " +
                        parameterAlarmID, null);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    /**
     * 根据 AlarmID 更新/修改 闹钟数据库
     * @param parameterAlarmID
     * @param contentValues
     * @return
     */
    public int update(String parameterAlarmID,ContentValues contentValues){
        synchronized (AlarmClockResolver.class){
            try {
                return contentResolver.update(targetUri,contentValues,
                        Constants.ALARM_ID + " = " +
                                parameterAlarmID, null);

            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    /**
     * 查询数据库中所有数据
     * @return
     */
    public Cursor query(){
        synchronized (AlarmClockResolver.class){
            try {
                return contentResolver.query(targetUri,null,
                        null,null,null);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 注册
     */
    public void registerAlarmClockContentObserver(final AlarmClockContentListener contentListener){

        if (!mAlarmClockContentListeners.contains(contentListener)){
            Log.e("heguodong","请注意，contentListener ->" + contentListener.toString() + "被添加注册了");
            mAlarmClockContentListeners.add(contentListener);
        }
        contentResolver.registerContentObserver(targetUri, true,
                new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        super.onChange(selfChange, uri);
                        //uri.compareTo(targetUri)
                        //uri.compareTo(targetUri) == 0
                        //uri.getAuthority().toString() == "test"
                        if (uri.getAuthority().contains("com.heguodong.beijing.alarmclockproviderlib.AlarmClockProvider")) {
                            for (AlarmClockContentListener contentListener : mAlarmClockContentListeners){
                                contentListener.onAlarmClockContentChange();
                            }
                        }
                    }
                });
    }

    /**
     * 解注册
     */
    public void unRegisterAlarmClockContentObserver(final AlarmClockContentListener contentListener){
        if (mAlarmClockContentListeners.contains(contentListener)){
            mAlarmClockContentListeners.remove(contentListener);
            Log.e("heguodong","请注意，contentListener ->" + contentListener.toString() + "被解注册了");
        }
    }
}
