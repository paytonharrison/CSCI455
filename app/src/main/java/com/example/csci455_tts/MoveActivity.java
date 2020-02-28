package com.example.csci455_tts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {

    String input;
    NetworkClient networkClient = new NetworkClient();
    //NetworkClient.Thread3 thread3 = networkClient.new Thread3(input);

    //example: how to create instances of nested classes
    //Outer_Demo outer = new Outer_Demo();
    //Outer_Demo.Inner_Demo inner = outer.new Inner_Demo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        Log.w("**Log**", "ph move activity on create");

        //HEAD TURN/TILT BUTTONS
        Button headUpButton = (Button) findViewById(R.id.tiltHeadUp);
        headUpButton.setOnClickListener(this);
        Button headDownButton = (Button) findViewById(R.id.tiltHeadDown);
        headDownButton.setOnClickListener(this);
        Button headRightButton = (Button) findViewById(R.id.turnHeadRight);
        headRightButton.setOnClickListener(this);
        Button headLeftButton = (Button) findViewById(R.id.turnHeadLeft);
        headLeftButton.setOnClickListener(this);

        //BODY/WAIST BUTTONS
        Button BodyRightButton = (Button) findViewById(R.id.moveBodyRight);
        BodyRightButton.setOnClickListener(this);
        Button BodyLeftButton = (Button) findViewById(R.id.moveBodyLeft);
        BodyLeftButton.setOnClickListener(this);

        //DRIVE/TURN BUTTONS
        Button driveForwardButton = (Button) findViewById(R.id.driveForward);
        driveForwardButton.setOnClickListener(this);
        Button driveReverseButton = (Button) findViewById(R.id.driveReverse);
        driveReverseButton.setOnClickListener(this);
        Button driveRightButton = (Button) findViewById(R.id.driveRight);
        driveRightButton.setOnClickListener(this);
        Button driveLeftButton = (Button) findViewById(R.id.driveLeft);
        driveLeftButton.setOnClickListener(this);

        //STOP BUTTON
        Button stop = (Button) findViewById(R.id.stopButton);
        stop.setOnClickListener(this);


        //copied from TalkActivity.java
        //tts = new TTS(this);
        //tts.start();
    }

    public void onClick(View v) {
        //Toast.makeText(this, "onClickMove", Toast.LENGTH_SHORT).show();
        //networkClient = new NetworkClient();

        switch (v.getId()) {

            //head tilt/turn
            case R.id.tiltHeadUp:
                input = "39";
                //networkClient.setMessage(input);
                //Message sendMsg = tts.handler.obtainMessage();
                //Bundle b = new Bundle();
                //b.putString("TT", input);
                //sendMsg.setData(b);
                //tts.handler.sendMessage(sendMsg);
                Toast.makeText(this, "tilt head up " + input, Toast.LENGTH_SHORT).show();
                Log.v("**Log**", "ph thread3 message: " + input);
                Log.w("**Log**", "ph thread3 message: " + input);
                //NetworkClient.Thread3 thread3.message = input;
                //NetworkClient.Thread3 thread3 = new Intent(NetworkClient.Thread3(this.input));

                //should be creating new thread
                //NetworkClient.Thread3 thread1 = networkClient.new Thread3(input);
                //NetworkClient.Thread3 thread1 = (NetworkClient.)

                //thread1.run();
                break;

            case R.id.tiltHeadDown:
                input = "25";
                Toast.makeText(this, "tilt head down " + input, Toast.LENGTH_SHORT).show();
                Log.v("**Log**", "ph thread3 message: " + input);
                //NetworkClient.Thread3 thread2 = networkClient.new Thread3(input);
                //thread2.run();
                break;
            case R.id.turnHeadRight:
                input = "38";
                Toast.makeText(this, "turn head right " + input, Toast.LENGTH_SHORT).show();

                Log.v("**Log**", "ph thread3 message: " + input);
                //NetworkClient.Thread3 thread3 = networkClient.new Thread3(input);
                //thread3.run();
                break;
            case R.id.turnHeadLeft:
                input = "40";
                Toast.makeText(this, "turn head left " + input, Toast.LENGTH_SHORT).show();
                Log.v("**Log**", "ph thread3 message: " + input);
                //NetworkClient.Thread3 thread4 = networkClient.new Thread3(input);
                //thread4.run();
                break;

            //waist/body turn
            case R.id.moveBodyRight:
                input = "54";
                Toast.makeText(this, "move body right " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread5 = networkClient.new Thread3(input);
                //thread5.run();
                break;
            case R.id.moveBodyLeft:
                input = "52";
                Toast.makeText(this, "move body left " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread6 = networkClient.new Thread3(input);
                //thread6.run();
                break;

            //Drive/turn
            case R.id.driveForward:
                input = "116";
                Toast.makeText(this, "drive forward " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread7 = networkClient.new Thread3(input);
                //thread7.run();
                break;
            case R.id.driveReverse:
                input = "111";
                Toast.makeText(this, "drive reverse " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread8 = networkClient.new Thread3(input);
                //thread8.run();
                break;
            case R.id.driveLeft:
                input = "113";
                Toast.makeText(this, "drive left " + input, Toast.LENGTH_SHORT).show();

                //Runnable runnable = networkClient.new Thread3(input);
                //Thread thread9 = new Thread(runnable);
                //thread9.start();
                new Thread(networkClient.new Thread3(input)).start();
                //new Thread(new NetworkClient.Thread3(input)).start();
                //NetworkClient.Thread3 thread9 = networkClient.new Thread3(input);
                break;
            case R.id.driveRight:
                input = "114";
                Toast.makeText(this, "drive right " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread10 = networkClient.new Thread3(input);
                //thread10.run();
                break;

            //STOP
            case R.id.stopButton:
                input = "65";
                Toast.makeText(this, "STOP " + input, Toast.LENGTH_SHORT).show();
                //NetworkClient.Thread3 thread11 = networkClient.new Thread3(input);
                //thread11.run();
                break;
        }
    }


}
