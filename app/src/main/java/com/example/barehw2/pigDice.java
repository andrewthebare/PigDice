package com.example.barehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.Random;

public class pigDice extends AppCompatActivity {
    private Random rand;

    private int diceID;
    private ImageView imgView;
    private Button btnRoll = findViewById(R.id.btnRoll);

    private int toWin = 10;
    private int diceNum;
    private boolean botTurn;
    private boolean winnerFound;


    private int playerScore;
    private int botScore;

    private int bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Started!!");
        //initialize
        restart();

        //message box for score to win


    }

    private void checkWin(){
        while (!winnerFound){
            //strategy
            if(botTurn){    //bot logic

            }

            //check winner

            //change teams

        }

        //check both cases for a win
        if (!botTurn){
            if (playerScore >= toWin){
                winnerFound=true;


            }
        }

    }

    private void roll(View view){
        diceNum=rand.nextInt(6)+1;

        //display logic
        switch (diceNum){
            case 1:
                diceID = R.drawable.dice1;
                break;
            case 2:
                diceID = R.drawable.dice2;
                break;
            case 3:
                diceID = R.drawable.dice3;
                break;
            case 4:
                diceID = R.drawable.dice4;
                break;
            case 5:
                diceID = R.drawable.dice5;
                break;
            case 6:
                diceID = R.drawable.dice6;
                break;
        }
        System.out.println("I rolled a " + diceNum);

        imgView.setImageResource(diceID);
    }

    private void restart(){
        imgView = findViewById(R.id.diceView);

        botTurn=false;
        winnerFound=false;

        playerScore=0;
        botScore=0;
        bank=0;

        btnRoll.setOnClickListener(this::roll);

        System.out.println("SET VALUES!!");


    }
}