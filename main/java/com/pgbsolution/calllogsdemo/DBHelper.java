package com.pgbsolution.calllogsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paran on 5/22/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=2;
    private static final String  DATABASE_NAME="dbhel";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FRIEND_TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FRIEND_TABLE);

        onCreate(sqLiteDatabase);
    }

    private static final String FRIEND_TABLE="friend_table";
    private static final String FRIEND_NAME="name";
    private static final String FRIEND_CHAT_ID="id";
    private static final String FRIEND_fileurl="fileURI";
    private static final String FRIEND_time="time";
    private static final String FRIEND_length="duration";
    private static final String FRIEND_STATE="state";

    private static final String FRIEND_TABLE_CREATE=
            "CREATE TABLE "+FRIEND_TABLE+" (" +
                    FRIEND_CHAT_ID + " TEXT PRIMARY KEY, "+
                    FRIEND_NAME + " TEXT, "+
                    FRIEND_fileurl + " TEXT, "+
                    FRIEND_time + " LONG, "+
                    FRIEND_STATE + " TEXT, "+
                    FRIEND_length + " LONG );";


    public void addEntry(CallLogCustom calllog){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FRIEND_CHAT_ID,calllog.getId());
        contentValues.put(FRIEND_NAME,calllog.getName());
        contentValues.put(FRIEND_fileurl,calllog.getFilePath());
        contentValues.put(FRIEND_time,calllog.getTimestamp());
        contentValues.put(FRIEND_length,calllog.getDuration());
        contentValues.put(FRIEND_STATE,calllog.getState());
        database.insertWithOnConflict(FRIEND_TABLE, null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        database.close();
        Log.d("added",(calllog.toString()));
    }

    public List<CallLogCustom> readCallLogs(){
        List<CallLogCustom> calls=new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();
        String selectQuery = "SELECT * FROM " + FRIEND_TABLE + ";";
        Cursor cursor=database.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                CallLogCustom call=new CallLogCustom();
                call.setId(cursor.getString(cursor.getColumnIndex(FRIEND_CHAT_ID)));
                call.setName(cursor.getString(cursor.getColumnIndex(FRIEND_NAME)));
                call.setFilePath(cursor.getString(cursor.getColumnIndex(FRIEND_fileurl)));
                call.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(FRIEND_time)));
                call.setDuration(cursor.getLong(cursor.getColumnIndex(FRIEND_length)));
                call.setState(cursor.getString(cursor.getColumnIndex(FRIEND_STATE)));
                calls.add(call);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return calls;
    }
}
