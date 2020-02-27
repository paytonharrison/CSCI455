package com.example.csci455_tts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        Button headButton = (Button) findViewById(R.id.moveHead);
        headButton.setOnClickListener(this);

        Button BodyButton = (Button) findViewById(R.id.moveBody);
        BodyButton.setOnClickListener(this);

        Button driveButton = (Button) findViewById(R.id.makeDrive);
        driveButton.setOnClickListener(this);

        //copied from TalkActivity.java
        //tts = new TTS(this);
        //tts.start();
    }

    public void onClick(View v) {

    }
}
