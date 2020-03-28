package com.heguodong.beijing.alarmclockresolverlib;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 何国栋 on 2020/3/26.
 */
/*

public class AlarmClockResolver2 extends ContentResolver {

    private Context mContext;

    private volatile static AlarmClockResolver2 contentResolver = null;

    private AlarmClockResolver2(Context context) {
        super(context);
        this.mContext = context;
    }

    public static AlarmClockResolver2 getInstance(Context context){

        if (contentResolver == null){
            synchronized (AlarmClockResolver.class){
                if (contentResolver == null){
                    contentResolver = new AlarmClockResolver2(context);
                }
            }
        }
        return contentResolver ;
    }

    */
/**
     *  添加闹钟数据到数据库
     * @param contentValues
     * @return
     *//*

    public long insert(ContentValues contentValues){
        synchronized (AlarmClockResolver.class){
            SQLiteDatabase writableDatabase = null;
            try {
                writableDatabase = AlarmClockDatabaseHelper.getInstance(mContext).getWritableDatabase();
                return writableDatabase.insert( AlarmClockDatabaseHelper.DATABASE_TABLE_NAME,null,contentValues);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            } finally {
                if (writableDatabase != null)
                    writableDatabase.close();
            }
        }
    }

    */
/**
     * 删除指定的闹钟数据
     * @param parameterAlarmID
     * @return
     *//*

    public int delete(String parameterAlarmID){
        synchronized (AlarmClockResolver.class){
            SQLiteDatabase writableDatabase = null;
            try {
                writableDatabase = AlarmClockDatabaseHelper.getInstance(mContext).getWritableDatabase();
                return writableDatabase.delete(AlarmClockDatabaseHelper.DATABASE_TABLE_NAME ,
                        AlarmClockDatabaseHelper.ALARM_ID + " = " + parameterAlarmID, null);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            } finally {
                if (writableDatabase != null)
                    writableDatabase.close();
            }
        }
    }

    */
/**
     * 根据 AlarmID 更新/修改 闹钟数据库
     * @param parameterAlarmID
     * @param contentValues
     * @return
     *//*

    public int update(String parameterAlarmID,ContentValues contentValues){
        synchronized (AlarmClockResolver.class){
            SQLiteDatabase writableDatabase = null;
            try {
                writableDatabase = AlarmClockDatabaseHelper.getInstance(mContext).getWritableDatabase();
                return writableDatabase.update(AlarmClockDatabaseHelper.DATABASE_TABLE_NAME ,contentValues,
                        AlarmClockDatabaseHelper.ALARM_ID + " = " + parameterAlarmID, null);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            } finally {
                if (writableDatabase != null)
                    writableDatabase.close();
            }
        }
    }

    */
/**
     * 查询数据库中所有数据
     * @return
     *//*

    public Cursor query(){
        synchronized (AlarmClockResolver.class){
            SQLiteDatabase writableDatabase = null;
            Cursor cursor = null;
            try {
                writableDatabase = AlarmClockDatabaseHelper.getInstance(mContext).getWritableDatabase();
                cursor = writableDatabase.query(AlarmClockDatabaseHelper.DATABASE_TABLE_NAME, null,
                        null, null, null, null, null, null);
                return cursor;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
*/
/*                if (writableDatabase != null )
                    writableDatabase.close();
                if (cursor != null && !cursor.isClosed())
                    cursor.close();*//*

            }
        }
    }
}
*/
