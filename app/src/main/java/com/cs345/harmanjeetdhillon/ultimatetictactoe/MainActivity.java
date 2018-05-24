package com.cs345.harmanjeetdhillon.ultimatetictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.welcome);
        mp.start();
    }

    public void playGameButtonClicked(View view){
        mp.stop();
        Intent i = new Intent(getApplicationContext(), PlayGame.class);
        startActivity(i);
    }

    public void highScoreButtonClicked(View view){
        Context ct = getApplicationContext();
        Toast.makeText(ct,"Feature Not Yet Completed, Coming Soon!",Toast.LENGTH_LONG).show();
    }
}
