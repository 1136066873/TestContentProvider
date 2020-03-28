package com.heguodong.beijing.alarmclockproviderlib;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import static com.heguodong.beijing.alarmclockbaselib.Constants.DATABASE_TABLE_NAME;
import static com.heguodong.beijing.alarmclockbaselib.Constants.PACKAGE_NAME;

/**
 * Created by 何国栋 on 2020/3/25.
 *  参考：https://blog.csdn.net/jordanhgl/article/details/83768987
 */

public class AlarmClockProvider extends ContentProvider {

    private SQLiteDatabase mDb;
    private AlarmClockDatabaseHelper mDbHelper = null;

    private static final int ALL_MESSAGES = 1;
    private static final int SPECIFIC_MESSAGE = 2;

    // Here's the public URI used to query for RSS items.
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.heguodong.beijing.alarmclockproviderlib.AlarmClockProvider/allalarms");

    // Set up our URL matchers to help us determine what an
    // incoming URI parameter is.
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI("com.heguodong.beijing.alarmclockproviderlib.AlarmClockProvider", "allalarms", ALL_MESSAGES);
        URI_MATCHER.addURI("com.heguodong.beijing.alarmclockproviderlib.AlarmClockProvider", "allalarms/#", SPECIFIC_MESSAGE);
    }

    /**
     * constructor
     */
    public AlarmClockProvider() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        try {
            mDbHelper = new AlarmClockDatabaseHelper(getContext());
            mDb = mDbHelper.getWritableDatabase();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // We won't bother checking the validity of params here, but you should!

        // SQLiteQueryBuilder is the helper class that creates the
        // proper SQL syntax for us.
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();

        // Set the table we're querying.
        qBuilder.setTables(DATABASE_TABLE_NAME);

        // If the query ends in a specific record number, we're
        // being asked for a specific record, so set the
        // WHERE clause in our query.
        if((URI_MATCHER.match(uri)) == SPECIFIC_MESSAGE){
            qBuilder.appendWhere("_id=" + uri.getLastPathSegment());
            Log.i("TestContentProvider", "_id=" + uri.getLastPathSegment());
        }

        // Make the query.
        Cursor c = qBuilder.query(mDb,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        Log.i("TestContentProvider", "get records");
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;

    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ALL_MESSAGES:
                return "vnd.android.cursor.dir/rssitem"; // List of items.
            case SPECIFIC_MESSAGE:
                return "vnd.android.cursor.item/rssitem"; // Specific item.
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // NOTE Argument checking code omitted. Check your parameters! Check that
        // your row addition request succeeded!

        long rowId = -1;
        rowId = mDb.insert(DATABASE_TABLE_NAME, PACKAGE_NAME, values);
        Uri newUri = Uri.withAppendedPath(CONTENT_URI, ""+rowId);
        Log.i("TestContentProvider", "saved a record " + rowId + " " + newUri);
        // Notify any listeners and return the URI of the new row.
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // NOTE Argument checking code omitted. Check your parameters!
        int rowCount = mDb.delete(DATABASE_TABLE_NAME, selection, selectionArgs);

        // Notify any listeners and return the deleted row count.
        getContext().getContentResolver().notifyChange(uri, null);
        return rowCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // NOTE Argument checking code omitted. Check your parameters!
        int updateCount = mDb.update(DATABASE_TABLE_NAME, values, selection, selectionArgs);

        // Notify any listeners and return the updated row count.
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
