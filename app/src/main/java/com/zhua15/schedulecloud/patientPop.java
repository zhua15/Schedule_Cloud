package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class patientPop extends Activity {

    String username;
    String password;
    String name;
    EditText codeView;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_pop);

        loadData();
        codeView = findViewById(R.id.codeView);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        name = intent.getStringExtra("name");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (0.75 * width), (int) (0.25 * height));
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
    public void onConfirm(View view) {
        Person person = new Person(username,password,"patient",codeView.toString());
        arrayList.add(person);
        saveData();
        startActivity(new Intent(patientPop.this, LoginActivity.class));
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("people list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("people list", null);
        java.lang.reflect.Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        if (arrayList == null)
            arrayList = new ArrayList();
    }
}
