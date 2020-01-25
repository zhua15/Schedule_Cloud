package com.zhua15.schedulecloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

    EditText usernameBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //------------
        usernameBox = findViewById(R.id.username);
        passwordBox = findViewById(R.id.password);

    }
    public void login(View view) {
        String username = usernameBox.toString();
        String password = passwordBox.toString();


    }
}
