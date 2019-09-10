package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;

import java.util.concurrent.TimeUnit;

public class PatientsActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<Cursor> */  {
    final String TAG = "myLogs";
    Button btnAdd,btnDel;
    ListView lvData;
    //DBController db;
    SimpleCursorAdapter scAdapter;
    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        Log.d(TAG, "MainActivity: onCreate()");
        // формируем столбцы сопоставления
        controller = new Controller(this);

        /*btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);*/
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
        String[] from = new String[] { DBController.PATCOLUMN_NAME, DBController.PATCOLUMN_DIAGNOS, DBController.PATCOLUMN_FVDATE };
        int[] to = new int[] { R.id.tvName, R.id.tvDiagnos, R.id.tvFVDate };
        Cursor cursor = controller.getPatientFromCursor(3);

        SimpleCursorAdapter cursorAdapter = new MySimpleCursorAdapter(this, R.layout.text, cursor, from, to, 0);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(cursorAdapter);

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cur = (Cursor)adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getApplicationContext(), PatientEdit.class);
                intent.putExtra("pat_Id", Integer.parseInt(cur.getString(0)));
                startActivity(intent);
            }
        });
    }
    public void onButtonClick(View view) {
        Intent intent = new Intent(this, PatientEdit.class);
        intent.putExtra("pat_Id", 0);
        startActivity(intent);
        //finish();
    }

    public void onButtonDelClick(View view){
        Intent intent = new Intent(this, PatientsActivityForDelete.class);
        startActivity(intent);
    }

    /*@Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAdd:
                intent = new Intent(this, PatientEdit.class);
                intent.putExtra("pat_Id", 0);
                startActivity(intent);
                break;
            case R.id.btnDel:
                intent = new Intent(this, PatientsActivityForDelete.class);
                startActivity(intent);
                break;
        }
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onStop()");
        // закрываем подключение при выходе
    }

}
