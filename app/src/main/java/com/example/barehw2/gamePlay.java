package com.example.barehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class gamePlay extends AppCompatActivity {
    private Random rand;

    private int diceID;
    private ImageView imgView;
    private Button btnRoll;
    private Button btnBank;

    private TextView txtBank;
    private TextView txtP1;
    private TextView txtP2;
    private TextView txtToWin;
    private TextView txtInfo;

    private int toWin = 40;
    private int diceNum;
    private boolean botTurn;

    private int playerScore;
    private int botScore;

    private int bank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        System.out.println("Hello World");
        restart();


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
        imgView.setImageResource(diceID);

        //if not a 1, add to the bank
        if (diceNum != 1){
            bank+=diceNum;
        }
        else{
            bank = 0;
            btnRoll.setVisibility(View.INVISIBLE);
        }

        //update bank value
        txtBank.setText(Integer.toString(bank));

    }
    private void bank(View view){
        boolean winnerFound = false;

        if(botTurn){
            botScore += bank;

        }else{
            playerScore+= bank;

            //check for win
            if (playerScore >= toWin){
                //player won
            }
        }

        txtP1.setText(Integer.toString(playerScore));
        txtP2.setText(Integer.toString(botScore));

        //get ready for other person's turn
        bank=0;
        txtBank.setText(Integer.toString(bank));
        botTurn = !botTurn;
        btnRoll.setVisibility(View.VISIBLE);
        if ((botTurn)) {
            txtInfo.setText(R.string.player2turn);
            botLogic();
        } else {
            txtInfo.setText(R.string.player1turn);
        }

    }

    private void botLogic(){

    }

    private void restart(){
        rand=new Random();

        imgView = findViewById(R.id.diceView);
        btnRoll = findViewById(R.id.btnRoll);
        btnRoll.setOnClickListener(this::roll);

        btnBank=findViewById(R.id.btnBank);
        btnBank.setOnClickListener(this::bank);

        txtBank = findViewById(R.id.txtBank);
        txtP1 = findViewById(R.id.player1ScoreDisplay);
        txtP2 = findViewById(R.id.player2ScoreDisplay);
        txtToWin = findViewById(R.id.txtToWin);
        txtInfo = findViewById(R.id.infoDisplay);

        botTurn=false;

        playerScore=0;
        botScore=0;
        bank=0;
        diceNum=1;

        System.out.println(txtToWin.getText());


        txtToWin.setText(Integer.toString(toWin));

        System.out.println("SET VALUES!!");


    }

}