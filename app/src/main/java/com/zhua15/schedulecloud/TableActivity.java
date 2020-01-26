package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableActivity extends Activity {

    Button delayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        delayButton = findViewById(R.id.delay);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //if not doctor
        //delayButton.setVisibility(View.INVISIBLE);
        //else
        delayButton.setVisibility(View.VISIBLE);
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
        Intent myIntent = new Intent(TableActivity.this, delayPop.class);
        startActivity(myIntent);
    }
}
