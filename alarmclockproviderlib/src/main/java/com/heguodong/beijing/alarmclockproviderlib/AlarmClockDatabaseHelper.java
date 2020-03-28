package com.heguodong.beijing.alarmclockproviderlib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.heguodong.beijing.alarmclockbaselib.Constants;

/**
 * Created by 何国栋 on 2020/3/25.
 */

public class AlarmClockDatabaseHelper extends SQLiteOpenHelper {

    private volatile static AlarmClockDatabaseHelper  alarmClockDatabaseHelper = null;

    public AlarmClockDatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION, null);
    }

    public static AlarmClockDatabaseHelper getInstance(Context context){

        if (alarmClockDatabaseHelper == null){
            synchronized (AlarmClockDatabaseHelper.class){
                if (alarmClockDatabaseHelper == null){
                    alarmClockDatabaseHelper = new AlarmClockDatabaseHelper(context);
                }
            }
        }
        return alarmClockDatabaseHelper ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //新建数据表{包名，ID，时间，频率，其他}
        db.execSQL("create table " + Constants.DATABASE_TABLE_NAME + "(" + Constants.ID + " integer primary key autoincrement, "+
                Constants.PACKAGE_NAME +" varchar , "+
                Constants.ALARM_ID +" int,"+
                Constants.DATE_TIME +"  varchar,"+
                Constants.FREQUENCY +" int,"+
                Constants.REMARKS +" varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated stub
    }
}
