package com.heguodong.beijing.alarmclockresolverlib;

import android.content.ContentValues;
import com.heguodong.beijing.alarmclockbaselib.Constants;

/**
 * Created by 何国栋 on 2020/3/26.
 */

public class AlarmClockContentValuesBuilder {

    private String packageName ;
    private int alarmID ;
    private double datetime ;
    private double frequency ;
    private String remarks ;

    public AlarmClockContentValuesBuilder packageName(String packageName){
        this.packageName = packageName ;
        return this;
    }

    public AlarmClockContentValuesBuilder alarmID(int alarmID){
        this.alarmID = alarmID ;
        return this;
    }

    public AlarmClockContentValuesBuilder datetime(double datetime){
        this.datetime = datetime ;
        return this;
    }

    public AlarmClockContentValuesBuilder frequency(double frequency){
        this.frequency = frequency ;
        return this;
    }

    public AlarmClockContentValuesBuilder remarks(String remarks){
        this.remarks = remarks ;
        return this;
    }

    public ContentValues build(){
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put(Constants.PACKAGE_NAME ,this.packageName );
        contentValues.put(Constants.ALARM_ID ,this.alarmID );
        contentValues.put(Constants.DATE_TIME ,this.datetime );
        contentValues.put(Constants.FREQUENCY ,this.frequency );
        contentValues.put(Constants.REMARKS ,this.remarks );
        return contentValues;
    }
}
