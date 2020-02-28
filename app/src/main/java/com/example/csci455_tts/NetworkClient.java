package com.example.csci455_tts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

@SuppressLint("SetTextI18n")
public class NetworkClient extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    int SERVER_PORT = 6942;
    String SERVER_IP = "10.200.22.203";

    //TTS tts;
    //String buttonInput;

    //NetworkClient networkClient = new NetworkClient();
    //NetworkClient.Thread3 thread3 = networkClient.new Thread3(buttonInput);

    //NetworkConnectionThread(int SERVER_PORT, String SERVER_IP){
    //    SERVER_PORT = this.SERVER_PORT;
    //    SERVER_IP = this.SERVER_IP;
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.testServer);

        //tts = new TTS(this);

        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                SERVER_IP = etIP.getText().toString().trim();
                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
                Log.v("**Log**", "ph ip " + SERVER_IP);
                Log.v("**Log**", "ph port " + SERVER_PORT);
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });

    }
    private PrintWriter output;
    private BufferedReader input;
    Socket socket;

    class Thread1 implements Runnable {
        public void run() {
            //Socket socket;
            Log.v("**Log**", "ph thread1 run socket");
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                Log.v("**Log**", "ph thread1 run try1.1");
                if(socket.getOutputStream() != null) {
                    System.out.println("ph" + socket.getOutputStream());
                    output = new PrintWriter(socket.getOutputStream());
                }
                Log.v("**Log**", "ph thread1 run try1.2");
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Log.v("**Log**", "ph thread1 run try1.3");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                        Log.v("**Log**", "ph thread1 run try2");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("**Log**", "ph thread1 run catch");
            }
        }
    }
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append("server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                    //call TTS method from message !!!
                    //LEVEL 2
                    //tts.speakOut(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        //private String message;
        public String message;
        Thread3(String message) {
            this.message = message;
            Log.v("**Log**", "ph thread3 message 1: " + message);
        }

        @Override
        public void run() {

            try {
                //socket = new Socket(SERVER_IP, SERVER_PORT);
                Log.v("**Log**", "ph thread 3 in try");
                if(socket.getOutputStream() != null) {
                    System.out.println("ph" + socket.getOutputStream());
                    output = new PrintWriter(socket.getOutputStream());
                }
                //Log.v("**Log**", "ph thread1 run try1.2");
                //input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Log.v("**Log**", "ph thread1 run try1.3");
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                        //Log.v("**Log**", "ph thread1 run try2");
                    }
                });*/
                //new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("**Log**", "ph thread3 in catch");
            }

            Log.v("**Log**", "ph thread3 before output check: " + message);
            Log.v("**Log**", "ph thread3 output: " + output);

            if(output != null){

                Log.v("**Log**", "ph thread3 after output check: " + message);
                output.write(message);
                output.flush();
                runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvMessages.append("client: " + message + "\n");
                    etMessage.setText("");
                }
            });
        }}
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