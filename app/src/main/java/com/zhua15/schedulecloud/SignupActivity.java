package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {

    Button patient;
    Button doctor;
    EditText username;
    EditText password;
    EditText passwordConfirm;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        patient = findViewById(R.id.patient);
        doctor = findViewById(R.id.doctor);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        name = findViewById(R.id.name);
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
    public void patient(View view) {
        Intent intent = new Intent(SignupActivity.this, patientPop.class);
        intent.putExtra("username", username.getText());
        intent.putExtra("password", password.getText());
        intent.putExtra("name", name.getText());
        startActivity(intent);
    }
    public void doctor(View view) {
        Intent intent = new Intent(SignupActivity.this, doctorPop.class);
        intent.putExtra("username", username.getText());
        intent.putExtra("password", password.getText());
        intent.putExtra("name", name.getText());
        startActivity(intent);
    }

}
