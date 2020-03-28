package com.heguodong.beijing.testcontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.heguodong.beijing.alarmclockresolverlib.AlarmClockContentListener;
import com.heguodong.beijing.alarmclockresolverlib.AlarmClockContentValuesBuilder;
import com.heguodong.beijing.alarmclockresolverlib.AlarmClockResolver;

public class MainActivity extends AppCompatActivity implements AlarmClockContentListener, View.OnClickListener {

    private static final String TAG = "heguodong_app1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图数据
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        //注册闹钟数据库数据变化的监听
        AlarmClockResolver.getInstance(this).registerAlarmClockContentObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //先查一遍
        Log. e(TAG, "------------------- app1 初始化时，首先查询一下数据库中数据 -------------------" );
        if (AlarmClockResolver.getInstance(this).query() != null){
            queryMyDatabase(AlarmClockResolver.getInstance(this).query());
        }else {
            Log. e(TAG, "请注意，数据库是空的！" );
        }
        Log. e(TAG, "------------------- app1 初始化时，查询数据结束 -------------------" );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                addData();
                break;
            case R.id.delete:
                deleteData();
                break;
            case R.id.update:
                updateData();
                break;
        }
    }

    /**
     *  添加数据
     */
    private void addData() {
        ContentValues values1 = new AlarmClockContentValuesBuilder()
                .packageName(this.getPackageName())
                .alarmID(1)
                .datetime(20200327)
                .frequency(5)
                .remarks("备注信息：这是在 app1 中添加的数据")
                .build();

        AlarmClockResolver.getInstance(this).insert(values1);
        //AlarmClockResolver.getInstance(this).update("1",values1);
        //AlarmClockResolver.getInstance(this).delete("1");
    }

    /**
     *  删除数据
     */
    private void deleteData() {
        ContentValues values1 = new AlarmClockContentValuesBuilder()
                .packageName(this.getPackageName())
                .alarmID(1)
                .datetime(20200327)
                .frequency(5)
                .remarks("备注信息：这是在 app1 中添加的数据")
                .build();

        //AlarmClockResolver.getInstance(this).insert(values1);
        //AlarmClockResolver.getInstance(this).update("1",values1);
        AlarmClockResolver.getInstance(this).delete("1");
    }

    /**
     *  修改数据
     */
    private void updateData() {
        ContentValues values1 = new AlarmClockContentValuesBuilder()
                .packageName(this.getPackageName())
                .alarmID(1)
                .datetime(20200327)
                .frequency(5)
                .remarks("备注信息：这是在 app1 中修改后的数据")
                .build();

        //AlarmClockResolver.getInstance(this).insert(values1);
        AlarmClockResolver.getInstance(this).update("1",values1);
        //AlarmClockResolver.getInstance(this).delete("1");
    }

    /**
     *  打印数据库中所有数据
     */
    private void queryMyDatabase( Cursor cursor) {
        //打印 Cursor 中数据
        while(cursor.moveToNext()){//类似于iterator.hasNext()与iterator.next()的结合
            int id = cursor.getInt(0);
            String packageName = cursor.getString(1);
            int alarmID = cursor.getInt(2);
            int datetime = cursor.getInt(3);
            int frequency = cursor.getInt(4);
            String remarks = cursor.getString(5);

            Log. e(TAG, "id:" + id + ",packageName:" + packageName + ",alarmID:" + alarmID + ",datetime:" + datetime + ",frequency:" + frequency + ",remarks:" + remarks);
        }
        cursor.close();
    }

    @Override
    public void onAlarmClockContentChange() {
        Log.e(TAG,"\n\n\n\n\n\n.\n\n\n\n\n\n");
        Log.e(TAG,"---------------------- 在 app1 中监测到数据发生变化，接下来重新遍历数据 ----------------------");
        queryMyDatabase(AlarmClockResolver.getInstance(this).query());
        Log.e(TAG,"---------------------- 数据发生变化后，在 app1 中遍历数据结束 ----------------------" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AlarmClockResolver.getInstance(this).unRegisterAlarmClockContentObserver(this);
    }
}
