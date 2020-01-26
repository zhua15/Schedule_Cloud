package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class TableActivity extends Activity {

    Button delayButton;
    Button bookButton;
    MatrixView matrixView;
    String code;
    String type;
    String user;
    String pass;
    Person currentPerson;
    Person [][]persons;
    ArrayList<Person> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        //delayButton.setVisibility(View.INVISIBLE);//Incomplete feature

        loadData();
        loadPersonData();


        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        type = intent.getStringExtra("type");
        user = intent.getStringExtra("user");
        pass = intent.getStringExtra("pass");
        bookButton = findViewById(R.id.book);
        delayButton = findViewById(R.id.delay);
        matrixView = findViewById(R.id.matrix);
        matrixView.persons = persons;

        for (Person person : arrayList) {
            try {
                if (person.getUsername().equals(user) && person.getPassword().equals(pass))
                    currentPerson = person;
            }
            catch (Exception e) {}
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (e.getX() >= 230 && e.getX() <= 1025 && e.getY() >= 445 && e.getY() <= 1608)
        {
            int x = (int) ((e.getX() - 230)/159);
            int y = (int) ((e.getY() - 445)/65);

            persons[x][y] = currentPerson;
        }
        matrixView.persons = persons;
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        delayButton.setVisibility(View.VISIBLE);
        bookButton.setVisibility(View.VISIBLE);
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideSystemUI();
    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    public void delay(View view) {
        delayButton.setVisibility(View.INVISIBLE);
        bookButton.setVisibility(View.INVISIBLE);
        Intent myIntent = new Intent(TableActivity.this, delayPop.class);
        startActivity(myIntent);
    }
    public void book(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(persons);
        editor.putString(code, json);
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(code, null);
        java.lang.reflect.Type type = new TypeToken<Person[][]>() {}.getType();
        persons = gson.fromJson(json, type);

        if (persons == null)
            persons = new Person[5][18];
    }
    private void loadPersonData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("people list", null);
        java.lang.reflect.Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        if (arrayList == null)
            arrayList = new ArrayList();
    }
}
