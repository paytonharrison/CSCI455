package com.example.csci455_tts;

import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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

        //server button
        Button testServerButton = (Button) findViewById(R.id.server);
        testServerButton.setOnClickListener(this);
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
           case R.id.server:
               testServer();
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

        //QUICK FIX
        Intent connectingRobot = new Intent(this, NetworkClient.class);
        // extra stuff 4 construc bb
        //String SERVER_IP = getLocalIpAddress();
        //connectingRobot.putExtra("SERVER_PORT", 5000);
        //connectingRobot.putExtra("SERVER_IP","SERVER_IP");
        startActivity(connectingRobot);
    }

    //method to start robot movement
    public void testMove() {

        Log.v("**Log**", "Move Button Pressed");
        Log.w("**Log**", "Move Button Pressed");
        Intent movingRobot = new Intent(this, MoveActivity.class);
        startActivity(movingRobot);
    }

    //method to have phone as server
    public void testServer() {

        Log.v("**Log**", "Server Button Pressed");
        Intent serverRobot = new Intent(this, NetworkServer.class);
        startActivity(serverRobot);
    }


    //Hunter's code for getting IP from D2L, week 6
    public static String getLocalIpAddress() {
        String ipAddress = "Unable to Fetch IP..";
        try {
            Enumeration en;
            en = NetworkInterface.getNetworkInterfaces();
            while ( en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface)en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        ipAddress=inetAddress.getHostAddress().toString();
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return ipAddress;
    }
}
