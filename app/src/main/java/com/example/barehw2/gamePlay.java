package com.example.barehw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class gamePlay extends AppCompatActivity {
    private Random rand;
    private CountDownTimer mTimer;

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
    private boolean bust = false;

    private int bank;
    private int round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        restart();


    }

    private void roll(View view){
        roll();
    }
    private void roll(){

        //timer dice
        if (mTimer != null) {
            mTimer.cancel();
        }

        mTimer = new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {

                diceNum=rand.nextInt(6)+1;
                imgView.setRotation(-1*imgView.getRotation());

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

            }

            @Override
            public void onFinish() {

                //if not a 1, add to the bank
                if (diceNum != 1){
                    bank+=diceNum;
                }
                else{
                    bank = 0;
                    btnRoll.setVisibility(View.INVISIBLE);
                    bust = true;
                    txtInfo.setText(R.string.bust);
                }

                //update bank value
                txtBank.setText(Integer.toString(bank));

            }
        }.start();

    }

    private void bank(View view){
        bank();
    }
    private void bank(){
        boolean winnerFound = false;

        if(botTurn){
            botScore += bank;

            if (botScore >= toWin){
                winnerFound = true;
                showWinnerDialog("The AI ");
            }


        }else{
            playerScore+= bank;

            //check for win
            if (playerScore >= toWin){
                winnerFound =true;
                showWinnerDialog("You");
                //player won
            }
        }

        txtP1.setText(Integer.toString(playerScore));
        txtP2.setText(Integer.toString(botScore));

        if (!winnerFound) {
            //get ready for other person's turn
            bank = 0;
            txtBank.setText(Integer.toString(bank));

            botTurn = !botTurn;
            if ((botTurn)) {
                String x = "Round " + Integer.toString(round) + ": " + "It's the AI's turn!";
                txtInfo.setText(x);
                btnRoll.setVisibility(View.INVISIBLE);
                btnBank.setVisibility(View.INVISIBLE);

                botLogic();
            } else {
                round++;
                String x = "Round " + Integer.toString(round) + ": " + "It's your turn!";
                txtInfo.setText(x);

                btnRoll.setVisibility(View.VISIBLE);
                btnBank.setVisibility(View.VISIBLE);

            }
        }

    }

    private void botLogic(){
        bust = false;

        //define a goal value, adjust according to scale of the game
        int goal = toWin/2;
        if (goal < 20)
            goal=20;
        else if (goal > 40)
            goal = rand.nextInt(10)+20;

        botRoll(goal);
    }

    private void botRoll(int goal){
        roll();
        CountDownTimer c = new CountDownTimer(1300, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if(bank <= goal && bank + botScore < toWin && !bust){
                    botRoll(goal);
                }else{
                    bank();
                }
            }
        }.start();

    }

    private void restart(){
        showToWinDialog();
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
        round = 1;
        diceNum=1;

        txtP1.setText(Integer.toString(playerScore));
        txtP2.setText(Integer.toString(botScore));
        String x = "Round " + Integer.toString(round) + ": " + "It's your turn!";
        txtInfo.setText(x);

        System.out.println(txtToWin.getText());

        System.out.println("SET VALUES!!");


    }

    void showToWinDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.SetScore);

        final EditText input = new EditText(this);
        b.setView(input);
        b.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toWin = Integer.parseInt(input.getText().toString());
                txtToWin.setText(Integer.toString(toWin));
            }
        });

        b.show();
    }

    void showWinnerDialog(String winner){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(winner + " won!");

        final TextView txtPlayAgain = new TextView(this);
        txtPlayAgain.setText(R.string.playAgain);
        b.setView(txtPlayAgain);

        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(which);
                restart();
            }
        });

        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        b.show();

    }

}