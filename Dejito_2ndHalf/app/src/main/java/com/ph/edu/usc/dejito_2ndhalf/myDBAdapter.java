package com.ph.edu.usc.dejito_2ndhalf;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBAdapter {
    myDBHelper mydbhelper;

    public myDBAdapter(Context context) {
        mydbhelper = new myDBHelper(context);
    }

    public long insertData(String name, String pass){
        SQLiteDatabase sqLiteDatabase = mydbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mydbhelper.NAME, name);
        contentValues.put(mydbhelper.MyPassword, pass);

        long id = sqLiteDatabase.insert(mydbhelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = mydbhelper.getWritableDatabase();
        String[] columns = {mydbhelper.UID, mydbhelper.NAME, mydbhelper.MyPassword};
        Cursor cursorn = db.query(mydbhelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while(cursorn.moveToNext()){
            @SuppressLint("Range") int cid = cursorn.getInt(cursorn.getColumnIndex(mydbhelper.UID));
            @SuppressLint("Range") String name = cursorn.getString(cursorn.getColumnIndex(mydbhelper.NAME));
            @SuppressLint("Range") String password = cursorn.getString(cursorn.getColumnIndex(mydbhelper.MyPassword));
            stringBuffer.append(cid + " " + name + " " + password + " \n");
        }
        return stringBuffer.toString();
    }

    public int deleteData(String uname){
        SQLiteDatabase db = mydbhelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(myDBHelper.TABLE_NAME, mydbhelper.NAME + " = ?", whereArgs);
        return count;
    }

    public int updateName(String oldName, String newName){
        SQLiteDatabase db = mydbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mydbhelper.NAME, newName);

        String[] whereArgs = {oldName};

        int count = db.update(myDBHelper.TABLE_NAME, contentValues, mydbhelper.NAME + " = ?", whereArgs);
        return count;
    }

    static class myDBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String MyPassword = "Password";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                                        + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + NAME + " VARCHAR(255), "
                                        + MyPassword + " VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        private Context context;

        public myDBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
