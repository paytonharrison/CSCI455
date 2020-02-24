package com.example.csci455_tts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button testTalkingButton = (Button) findViewById(R.id.testTalk);
        testTalkingButton.setOnClickListener(this);
    }

    public void onClick(View view) {
       switch(view.getId()) {
           case R.id.testTalk:
               testTalking();
               break;
           //case R.id.firstButton:
           //    testTalking();
           //    break;
       }
    }

    public void testTalking() {

        Log.v("**Log**", "Button Pressed");
        Intent talkingRobot = new Intent(this, TalkActivity.class);
        startActivity(talkingRobot);
    }
}
