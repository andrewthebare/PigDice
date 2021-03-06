package com.example.barehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button playButton, learnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(this::onStart);
        learnButton = (Button)findViewById(R.id.learnButton);
        learnButton.setOnClickListener(this::onLearn);
    }

    protected void onStart(View view){
        Intent intent = new Intent(this, gamePlay.class);
        startActivity(intent);
    }
    protected void onLearn(View view){
        Intent intent = new Intent(this, learnMore.class);
        startActivity(intent);
    }
}