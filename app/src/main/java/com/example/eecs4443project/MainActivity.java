package com.example.eecs4443project;

import static android.Manifest.permission.RECORD_AUDIO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.Manifest.permission.RECORD_AUDIO;

/**
 * EECS 4443 Project - Group Sierra
 *
 * Members: Talayeh Amiri Tokaldany, Alice Chai, Adrian Fearman, Jeff Wang
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startVoiceBtn, startTouchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ask for permission to use microphone
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO},
                PackageManager.PERMISSION_GRANTED);
        initalize();
    }

    private void initalize() {

        startVoiceBtn = (Button) findViewById(R.id.startVoice);
        startTouchBtn = (Button) findViewById(R.id.startTouch);

        startVoiceBtn.setOnClickListener(this);
        startTouchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == startVoiceBtn) {
            Intent intent = new Intent(MainActivity.this, VoiceInputActivity.class);
            startActivity(intent);
        }
        else if (v == startTouchBtn) {
            Intent intent = new Intent(MainActivity.this, TouchInputActivity.class);
            startActivity(intent);
        }
    }
}