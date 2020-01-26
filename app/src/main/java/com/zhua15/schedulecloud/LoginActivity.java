package com.zhua15.schedulecloud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    EditText usernameBox;
    EditText passwordBox;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    int currentVideoPosition;
    ArrayList<Person> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //------------
        loadData();

        usernameBox = findViewById(R.id.username);
        passwordBox = findViewById(R.id.password);

        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo_no_words);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                mediaPlayer.setLooping(true);
                if (currentVideoPosition != 0) {
                    mediaPlayer.seekTo(currentVideoPosition);
                    mediaPlayer.start();
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        currentVideoPosition = mediaPlayer.getCurrentPosition();
        videoView.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
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
    public void login(View view) {
        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        for (Person person : arrayList) {
            try {
                if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, TableActivity.class);
                    intent.putExtra("type", person.getIdtype());
                    intent.putExtra("code", person.getIdcode());
                    intent.putExtra("user", person.getUsername());
                    intent.putExtra("pass", person.getPassword());
                    startActivity(intent);
                }
            }
            catch (Exception e) {}
        }
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
