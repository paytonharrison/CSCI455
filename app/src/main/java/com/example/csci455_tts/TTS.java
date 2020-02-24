package com.example.csci455_tts;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import android.os.Handler;
import android.os.Message;

public class TTS extends Thread implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Context context;
    public Handler handler;
    private String last;
    private int result;

    TTS(Context con) {
        context = con;
        tts = new TextToSpeech(con, this);
        //initialize to anything
        last = "c";
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            result = tts.setLanguage(Locale.US);
            tts.setPitch(0);
            tts.setSpeechRate(0);
        }

        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(context, "Language or Data not working", Toast.LENGTH_LONG).show();
        }


    }

    public void run() {

        Looper.prepare();
            handler = new Handler() {
                public void handleMessage(Message msg) {
                    String response = msg.getData().getString("TT");
                    //do something with the message
                    Log.v("**SPEECH**", response);
                    speakOut(response);
                }
            };
        Looper.loop();

    }

    public void speakOut(String text) {

        if (last != text) {

            //don't keep repeating messages
            last = text;

            tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);

            while (tts.isSpeaking()) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }
            }
        }
    }
}
