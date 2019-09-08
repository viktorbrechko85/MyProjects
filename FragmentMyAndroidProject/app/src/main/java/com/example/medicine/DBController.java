package com.example.medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DBController implements DbControllerInterface {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDb";
    private static final String PATIENTTABLE_NAME = "Table1";
    private static final String THERAPYTABLE_NAME = "Table2";
    
    public static final String COLUMN_ID = "_id";
    public static final String PATCOLUMN_NAME = "name";
    public static final String PATCOLUMN_DIAGNOS = "";
    public static final String PATCOLUMN_FVDATE = "";

    public static final String THERCOLUMN_NAME = "name";
    public static final String THERCOLUMN_DATECONSULT = "";


    // конструктор суперкласса
    final String LOG_TAG = "myLogs";
    private final Context mCtx;


    private DBHelper mDBHelper;
    private SQLiteDatabase db;

    public DBController(Context mCtx) {
        this.mCtx = mCtx;

    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        db = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    

    public Cursor selectRow(String selectQuery, String[] selArgs){
        Cursor cursor;
        try{
            open();
            cursor = db.rawQuery(selectQuery, selArgs);
        }finally {

        }
        return cursor;
    }

    public Cursor selectTable(String tableName, String[] columns, String selection, String[] selArgs, String groupby, String having, String orderby){
        Cursor cursor;
        try{
            open();
            cursor = db.query(PATIENTTABLE_NAME, columns, selection, selArgs, groupby, having, orderby);
        }finally {

        }
        return cursor;
    }

    public long insertRow(String tableName, ContentValues values){
        long res = 0;
        try {
            open();
            db.beginTransaction();
            res =  db.insert(tableName, null, values);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            close();
        }
        return res;
    }

    public int updateRow(String tableName, ContentValues values, String whereClause, String[] whereArgs){
        int res = 0;
        try {
            open();
            db.beginTransaction();
            res =  db.update(tableName, values, whereClause, whereArgs);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            close();
        }
        return res;
    }

    public void deleteRow(String tableName){
        try {
            open();
            db.beginTransaction();
            db.delete(tableName, null, null);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            close();
        }
    }

    public void deleteRowCond(String tableName, String whereClause, String[] whereArgs){
        try {
            open();
            db.beginTransaction();
            db.delete(tableName, whereClause, whereArgs);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            close();
        }
    }

    @Override
    public Cursor getPatientsForCursor(int countField) {
        String selectQuery = null;
        switch(countField){
            case 1:
                selectQuery = "SELECT " + COLUMN_ID + ", " + PATCOLUMN_NAME + "  FROM " + PATIENTTABLE_NAME;
                break;
            case 2:
                selectQuery = "SELECT " + COLUMN_ID + ", " + PATCOLUMN_NAME + "," + PATCOLUMN_DIAGNOS + "  FROM " + PATIENTTABLE_NAME;
                break;
            case 3:
                selectQuery = "SELECT " + COLUMN_ID + ", " + PATCOLUMN_NAME + "," + PATCOLUMN_DIAGNOS + "," + PATCOLUMN_FVDATE + "  FROM " + PATIENTTABLE_NAME;
                break;
            default:
                selectQuery = "SELECT " + COLUMN_ID + ", " + PATCOLUMN_NAME + "," + PATCOLUMN_DIAGNOS + "," + PATCOLUMN_FVDATE + "  FROM " + PATIENTTABLE_NAME;
                break;
        }

        Cursor cursor = selectRow(selectQuery, null);
      //  close();
        return cursor;
    }

    
    @Override
    public long addPatient(Patient patient) {
        ContentValues values = new ContentValues();
        values.put("name", patient.getFIO());
        values.put("email", patient.getEmail());
      

        //long patient_id = db.insert(PATIENTTABLE_NAME, null, values);
        long patient_id = insertRow(PATIENTTABLE_NAME, values);
        return patient_id;
    }



    @Override
    public long addTherapy(Therapy therapy) {
        ContentValues values = new ContentValues();
        values.put("therapy", therapy.getThreatment());
      
        long therapy_id = insertRow(THERAPYTABLE_NAME, values);//db.insert(THERAPYTABLE_NAME, null, values);
        return therapy_id;
    }

    

    
    

    @Override
    public Map<Integer, Integer> getUniqPatTherapy(int patient_id) {
        open();
        Map<Integer, Integer> result = new HashMap<>();
        String selectQuery = "SELECT _id, patient_Id FROM " + PATTHERAPYTABLE_NAME + " WHERE patient_Id = " + patient_id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                result.put(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        close();
        return result;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("PRAGMA foreign_keys=on;");
            db.execSQL("create table " +  PATIENTTABLE_NAME + " ("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");

           
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch(oldVersion) {
                case 1:
                    //upgrade logic from version 2 to 3
                    
                    
                case 2:
 
                case 3:
                    //upgrade logic from version 3 to 4

                    break;
                case 4:

                    break;
                default:
                    throw new IllegalStateException(
                            "onUpgrade() with unknown oldVersion " + oldVersion);
            }
        }
    }
}
