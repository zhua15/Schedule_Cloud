package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends Activity {

    Button patient;
    Button doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        patient = findViewById(R.id.patient);
        doctor = findViewById(R.id.doctor);
    }
    public void patient(View view) {
        startActivity(new Intent(SignupActivity.this, patientPop.class));
    }
    public void doctor(View view) {
        startActivity(new Intent(SignupActivity.this, doctorPop.class));
    }

}
