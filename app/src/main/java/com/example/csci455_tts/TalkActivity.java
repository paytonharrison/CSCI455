package com.example.csci455_tts;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TalkActivity extends AppCompatActivity implements View.OnClickListener {

    EditText talkText;
    TTS tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_talk);
        talkText = (EditText) findViewById(R.id.talkText);
        Button talkButton = (Button) findViewById(R.id.makeTalk);
        talkButton.setOnClickListener(this);
        tts = new TTS(this);
        tts.start();

    }

    public void onClick(View v) {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.makeTalk:
                String input = talkText.getText().toString();
                Message sendMsg = tts.handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("TT", input);
                sendMsg.setData(b);
                tts.handler.sendMessage(sendMsg);
                break;
        }
    }
}
