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
    private static final String DATABASE_NAME = "Medicine";
    private static final String PATIENTTABLE_NAME = "Patients";
    private static final String THERAPYTABLE_NAME = "Therapies";
    private static final String PATTHERAPYTABLE_NAME = "PatTerapies";
    private static final String SALMEDAGTABLE_NAME = "SalesMedAgents";

    public static final String COLUMN_ID = "_id";
    public static final String PATCOLUMN_NAME = "name";
    public static final String PATCOLUMN_DIAGNOS = "diagnosis";
    public static final String PATCOLUMN_FVDATE = "DateFirstConsult";

    public static final String THERCOLUMN_NAME = "therapy";
    public static final String THERCOLUMN_DATECONSULT = "DateConsult";
    public static final String THERCOLUMN_ANALYS = "Photo";
    public static final String THERCOLUMN_MEMO = "memo";

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

    @Override
    public Cursor getAllDatesTherapy(int patient_Id){
        String selectQuery = "SELECT PTH." + COLUMN_ID + ", TH." + THERCOLUMN_DATECONSULT
                + " FROM " + THERAPYTABLE_NAME + " as TH"
                + " INNER JOIN " + PATTHERAPYTABLE_NAME + " as PTH "
                + " on TH." + COLUMN_ID + " = pth.therapy_id"
                + " WHERE PTH.PATIENT_ID = " + patient_Id;
        Cursor cur = selectRow(selectQuery, null);
       // close();
        return cur;
    }
    @Override
    public Cursor getAllTherapiesFromDate(int grp_id){
        String selectQuery = "SELECT TH." + COLUMN_ID + ", " + THERCOLUMN_NAME + ", " + THERCOLUMN_ANALYS
                + " FROM " + THERAPYTABLE_NAME + " as TH"
                + " INNER JOIN " + PATTHERAPYTABLE_NAME + " as PTH "
                + " on TH." + COLUMN_ID + " = pth.therapy_id"
                + " WHERE PTH." + COLUMN_ID + " = " + grp_id;
        Cursor cur = selectRow(selectQuery, null);
       // close();
        return cur;
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
    public int getCountPatient() {
        //open();
        String selectQuery = "SELECT * FROM " + PATIENTTABLE_NAME;
        Cursor cursor = selectTable(PATIENTTABLE_NAME, null, null, null, null, null, null);
                //db.query(PATIENTTABLE_NAME, null, null, null, null, null, null);
        int rowCount = 0;
        if (cursor.moveToFirst()) {
            rowCount = cursor.getCount();
        }
        close();
        return rowCount;
    }

    @Override
    public int getCountTherapy() {
        open();
        String selectQuery = "SELECT * FROM " + THERAPYTABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int rowCount = cursor.getCount();
        close();
        cursor.close();
        return rowCount;
    }

    @Override
    public long addPatient(Patient patient) {
        ContentValues values = new ContentValues();
        values.put("name", patient.getFIO());
        values.put("email", patient.getEmail());
        values.put("DateBirth", datetodbformat(patient.getDateBirthday()));
        values.put("diagnosis", patient.getDiagnosis());
        values.put("DateFirstConsult", datetodbformat(patient.getDateFirstVisited()));
        values.put("memo", patient.getMemo());
        values.put("phone", patient.getPhoneNumber());

        //long patient_id = db.insert(PATIENTTABLE_NAME, null, values);
        long patient_id = insertRow(PATIENTTABLE_NAME, values);
        return patient_id;
    }



    @Override
    public long addTherapy(Therapy therapy) {
        ContentValues values = new ContentValues();
        values.put("therapy", therapy.getThreatment());
        values.put("Photo", therapy.getAnalysys());
        values.put("DateConsult", datetodbformat(therapy.getDateConsult()));
        values.put("memo", therapy.getMemo());
        long therapy_id = insertRow(THERAPYTABLE_NAME, values);//db.insert(THERAPYTABLE_NAME, null, values);
        return therapy_id;
    }

    @Override
    public void addNewPatientAndFirstTherapy(Patient patient, Therapy therapy) {
        long patient_id = addPatient(patient);
        long therapy_id = addTherapy(therapy);
        ContentValues values = new ContentValues();
        values.put("patient_id", patient_id);
        values.put("therapy_id", therapy_id);
        insertRow(PATTHERAPYTABLE_NAME, values);
    }

    @Override
    public void addTherapyIntoPatient(int patient_Id, Therapy therapy) {
        long therapy_id = addTherapy(therapy);
        ContentValues values = new ContentValues();
        values.put("patient_id", patient_Id);
        values.put("therapy_id", therapy_id);
        insertRow(PATTHERAPYTABLE_NAME, values);
        //close();
    }

    @Override
    public List<Patient> getFullListsPatients(){
        open();
        List<Patient> patients = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int id;
        String selectQuery = "SELECT " + COLUMN_ID + " ,  name, email, DateBirth, diagnosis, DateFirstConsult, memo, phone FROM " + PATIENTTABLE_NAME + " order by name";
        try {
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient();
                id = Integer.parseInt(cursor.getString(0));
                patient.setId(id);
                patient.setFIO(cursor.getString(1));
                patient.setEmail(cursor.getString(2));

                Date dbirth = sdf.parse(cursor.getString(3));
                patient.setDateBirthday(dbirth);

                patient.setDiagnosis(cursor.getString(4));
                Date dFV = sdf.parse(cursor.getString(5));
                patient.setDateFirstVisited(dFV);
                patient.setMemo(cursor.getString(6));
                patient.setTherapy(getFullListTerapyFromPatient(id));
                patient.setPhoneNumber(cursor.getString(7));
                patients.add(patient);
            } while (cursor.moveToNext());
        }
        close();
        return patients;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient getPatient(int patient_id) {
        open();
        Patient patient = new Patient();
        String selectQuery = "SELECT name, email, DateBirth, diagnosis, DateFirstConsult, memo, phone FROM " + PATIENTTABLE_NAME + " where " + COLUMN_ID + "  = " + patient_id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                     patient.setId(patient_id);
                    patient.setFIO(cursor.getString(0));
                    patient.setEmail(cursor.getString(1));

                    Date dbirth = sdf.parse(cursor.getString(2));
                    patient.setDateBirthday(dbirth);

                    patient.setDiagnosis(cursor.getString(3));
                    Date dFV = sdf.parse(cursor.getString(4));
                    patient.setDateFirstVisited(dFV);
                    patient.setMemo(cursor.getString(5));
                    patient.setTherapy(getFullListTerapyFromPatient(patient_id));
                    patient.setPhoneNumber(cursor.getString(6));
                } while (cursor.moveToNext());
            }
            close();
            return patient;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updatePatient(Patient patient) {
        ContentValues values = new ContentValues();
        values.put("name", patient.getFIO());
        values.put("email", patient.getEmail());
        values.put("DateBirth", datetodbformat(patient.getDateBirthday()));
        values.put("diagnosis", patient.getDiagnosis());
        values.put("DateFirstConsult", datetodbformat(patient.getDateFirstVisited()));
        values.put("memo", patient.getMemo());
        values.put("phone", patient.getPhoneNumber());

        int result = updateRow(PATIENTTABLE_NAME, values, COLUMN_ID + " = ?", new String[] { String.valueOf(patient.getId()) });
        return result;

    }

    @Override
    public int updateTherapy(Therapy therapy) {
        ContentValues values = new ContentValues();
        values.put(THERCOLUMN_NAME, therapy.getThreatment());
        values.put(THERCOLUMN_DATECONSULT, datetodbformat(therapy.getDateConsult()));
        values.put(THERCOLUMN_ANALYS, therapy.getAnalysys());

        int result = updateRow(THERAPYTABLE_NAME, values, COLUMN_ID + " = ?", new String[] { String.valueOf(therapy.getTherapy_Id()) });
        return result;
    }

    @Override
    public void deletePatient(int patient_id) {
        Log.d(LOG_TAG, "--- delete patient_Id =  ---" + patient_id);
        deleteAllTherapyFromPatient(patient_id);
        deleteRowCond(PATIENTTABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(patient_id) });
    }

    @Override
    public void deleteAllPatients() {
        deleteAllTherapies();
        deleteRowCond(PATIENTTABLE_NAME, null, null);
    }

    @Override
    public void deleteAllTherapies() {
        deleteRowCond(THERAPYTABLE_NAME, null, null);
    }

    @Override
    public List<Therapy> getFullListTerapy() {
        open();
        List<Therapy> therapies = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_ID + " FROM " + THERAPYTABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Therapy therapy = getTherapy(Integer.parseInt(cursor.getString(0)));
                therapies.add(therapy);
            } while (cursor.moveToNext());
        }
        close();
        return therapies;
    }

    @Override
    public List<Therapy> getFullListTerapyFromPatient(int patient_id) {
        open();
        List<Therapy> therapies = new ArrayList<>();
        String selectQuery = "SELECT therapy_id FROM " + PATTHERAPYTABLE_NAME + " where patient_id = " + patient_id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Therapy therapy = getTherapy(Integer.parseInt(cursor.getString(0)));
                therapies.add(therapy);
            } while (cursor.moveToNext());
        }
        close();
        return therapies;
    }

    @Override
    public Therapy getTherapyFromConnectTable(int patTher_id) {
        open();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String selectQuery = "SELECT TH."
                    + COLUMN_ID + ", "
                    + THERCOLUMN_DATECONSULT + ", "
                    + THERCOLUMN_NAME + ","
                    + THERCOLUMN_ANALYS + " FROM "
                    + PATTHERAPYTABLE_NAME + " AS PTH "
                    + " INNER JOIN " + THERAPYTABLE_NAME + " AS TH "
                    + " WHERE PTH." + COLUMN_ID + "  = " + patTher_id;
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor != null){
                cursor.moveToFirst();
            }
            Date date = sdf.parse(cursor.getString(1));
            Therapy therapy = new Therapy(date, cursor.getString(2), cursor.getString(3));
            therapy.setTherapy_Id(Integer.parseInt(cursor.getString(0)));
            close();
            return therapy;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Therapy getTherapy(int therapy_id) {
        open();
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String selectQuery = "SELECT DateConsult, therapy, Photo  FROM " + THERAPYTABLE_NAME + " where " + COLUMN_ID + "  = " + therapy_id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        Date date = sdf.parse(cursor.getString(0));
        Therapy therapy = new Therapy(date, cursor.getString(1), cursor.getString(2));
        therapy.setTherapy_Id(therapy_id);
        close();
        return therapy;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String datetodbformat(Date date) {
        String retval = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //dlya sortirovki
        if (date == null) {
            return retval;
        }
        retval = sdf.format(date);
        return retval;
    }

    @Override
    public void deleteAllTherapyFromPatient(int patient_id) {
        List<Therapy> therapies = getFullListTerapyFromPatient(patient_id);
        for(Therapy ther1:therapies){
            deleteTherapy(ther1.getTherapy_Id());
        }
    }

    @Override
    public void deleteTherapy(int therapy_id) {
        deleteRowCond(THERAPYTABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(therapy_id) });
        deleteRowCond(PATTHERAPYTABLE_NAME, "therapy_id" + " = ?", new String[] { String.valueOf(therapy_id) });
    }

    @Override
    public void deleteTherapyFromPatient(int patient_id, int therapy_id) {
        List<Therapy> therapies = getFullListTerapyFromPatient(patient_id);
        for(Therapy ther1:therapies){
            if (therapy_id==ther1.getTherapy_Id())
                deleteTherapy(ther1.getTherapy_Id());
        }
    }

    @Override
    public Map<Integer, String> getPatientTherapy(int patient_id) {
        open();
        Map<Integer, String> result = new HashMap<>();
        String selectQuery = "SELECT patient_id, therapy_Id FROM " + PATTHERAPYTABLE_NAME + " WHERE patient_Id = " + patient_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        String strTher = "";
        if (cursor.moveToFirst()) {
            do {
                strTher = strTher + "_" + cursor.getString(1);
                result.put(Integer.parseInt(cursor.getString(0)), strTher);
            } while (cursor.moveToNext());
        }
        close();
        return result;
    }

    @Override
    public Map<Integer, Integer> getUniqPatTherapy(int patient_id) {
        open();
        Map<Integer, Integer> result = new HashMap<>();
        String selectQuery = "SELECT _id, therapy_Id FROM " + PATTHERAPYTABLE_NAME + " WHERE patient_Id = " + patient_id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                result.put(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        close();
        return result;
    }

    @Override
    public Map<Integer, Integer> getUniqPatTherapy2(int patient_id) {
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
                    + "email text,"
                    + "phone text,"
                    + "DateBirth text,"
                    + "diagnosis text,"
                    + "DateFirstConsult text,"
                    + "memo text" + ");");

            db.execSQL("create table " +  THERAPYTABLE_NAME + " ("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + "therapy text,"
                    + "Photo text,"
                    + "DateConsult text,"
                    + "memo text" + ");");

            db.execSQL("create table " +  PATTHERAPYTABLE_NAME + " ("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + "patient_id integer not null,"
                    + "therapy_id integer not null,"
                    + "FOREIGN KEY (patient_id) REFERENCES Patients(_id) ON DELETE CASCADE,"
                    + "FOREIGN KEY (therapy_id) REFERENCES Therapies(_id) ON DELETE CASCADE" + ");");

            db.execSQL("create table " +  SALMEDAGTABLE_NAME + " ("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + "name text,"
                    + "email text,"
                    + "DateVisit text,"
                    + "DateFirstConsult text,"
                    + "drug text,"
                    + "drug_count integer,"
                    + "memo text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch(oldVersion) {
                case 1:
                    //upgrade logic from version 2 to 3
                    Log.d(LOG_TAG, "--- Update 2 version ---");
                    db.beginTransaction();
                    try {
                        db.execSQL("PRAGMA foreign_keys=on;");
                        db.execSQL("create temporary table therapy_tmp ("
                                + "id integer, patient_id integer, therapy_id integer);");

                        db.execSQL("insert into therapy_tmp select " + COLUMN_ID + " , patient_id, therapy_id from PatTerapies;");
                        db.execSQL("drop table PatTerapies;");

                        db.execSQL("create table " +  PATTHERAPYTABLE_NAME + " ("
                                + COLUMN_ID + " integer primary key autoincrement,"
                                + "patient_id integer not null,"
                                + "therapy_id integer not null,"
                                + "FOREIGN KEY (patient_id) REFERENCES Patients(" + COLUMN_ID + " ) ON DELETE CASCADE,"
                                + "FOREIGN KEY (therapy_id) REFERENCES Therapies(" + COLUMN_ID + " ) ON DELETE CASCADE" + ");");

                        db.execSQL("insert into PatTerapies(patient_id, therapy_id) select  patient_id, therapy_id from therapy_tmp;");
                        db.execSQL("drop table therapy_tmp;");
                        db.setTransactionSuccessful();
                    } finally {
                        db.endTransaction();
                    }
                case 2:
                    db.execSQL("PRAGMA foreign_keys=on;");
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
