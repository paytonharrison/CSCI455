package com.example.csci455_tts;

import android.net.Network;
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

        //first button - talk
        Button testTalkingButton = (Button) findViewById(R.id.testTalk);
        testTalkingButton.setOnClickListener(this);

        //second button - network
        Button testConnectionButton = (Button) findViewById(R.id.connect);
        testConnectionButton.setOnClickListener(this);

        //third button - movement
        Button testMoveButton = (Button) findViewById(R.id.move);
        testMoveButton.setOnClickListener(this);
    }

    public void onClick(View view) {
       switch(view.getId()) {
           case R.id.testTalk:
               testTalking();
               break;
           case R.id.connect:
               testConnection();
               break;
           case R.id.move:
               testMove();
               break;
       }
    }

    //method to start TalkActivity class
    public void testTalking() {

        Log.v("**Log**", "Talk Button Pressed");
        Intent talkingRobot = new Intent(this, TalkActivity.class);
        startActivity(talkingRobot);
    }

    //method to start NetworkConnectionThread class
    public void testConnection() {

        Log.v("**Log**", "Connect Button Pressed");
        Intent connectingRobot = new Intent(this, NetworkConnectionThread.class);
        startActivity(connectingRobot);
    }

    //method to start robot movement
    public void testMove() {

        Log.v("**Log**", "Move Button Pressed");
        Intent movingRobot = new Intent(this, MoveActivity.class);
        startActivity(movingRobot);
    }
}
