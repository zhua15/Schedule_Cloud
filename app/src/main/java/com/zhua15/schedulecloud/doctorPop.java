package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class doctorPop extends Activity {

    TextView codeView;
    String username;
    String password;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_pop);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        name = intent.getStringExtra("name");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (0.75 * width), (int) (0.25 * height));

        codeView = findViewById(R.id.codeView);
        int i = (int) (Math.random() * 999999 + 100000);
        codeView.setText("Doctor Code:\n" + i);
    }
    public void onConfirm(View view) {

    }
}
