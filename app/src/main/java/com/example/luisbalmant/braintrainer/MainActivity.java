package com.example.luisbalmant.braintrainer;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout start_LinearLayout;

    TextView timer_TextView;
    TextView challenges_TextView;
    TextView messages_TextView;
    TextView score_TextView;

    Button playAgain_Button;
    Button inGridLayout00;
    Button inGridLayout01;
    Button inGridLayout10;
    Button inGridLayout11;

    Random rand;

    ArrayList<Integer> answers = new ArrayList<>();

    int locationOfCorrectAnswer;
    int total;
    int score = 0;
    int timesTouch = 0;
    int newScore;
    int performance;

    /*******************************************************
     ******** LinearLayout initital for Start the game.*****
     *******************************************************/
    public void startGame_Button(View view) {
        start_LinearLayout.setVisibility(View.INVISIBLE);
        controlTimer();
    }

    public void playAgain_Button(View view) {
        controlTimer();
        challenges_TextView();
        score = 0;
        timesTouch = 0;
        score_TextView.setText("0/0");
        messages_TextView.setText("");
        playAgain_Button.setVisibility(view.INVISIBLE);
        inGridLayout00.setEnabled(true);
        inGridLayout01.setEnabled(true);
        inGridLayout10.setEnabled(true);
        inGridLayout11.setEnabled(true);
    }

    /*****************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFindViewByIds();
        challenges_TextView();
    }

    public void challenges_TextView() {
        rand = new Random();
        int x = rand.nextInt(20) + 1; // Gives number between 1 - 20
        int y = rand.nextInt(20) + 2; // Gives number between 2 - 20
        total = x + y;
        challenges_TextView.setText(Integer.toString(x) + " + " + Integer.toString(y));
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(x + y);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == x + y) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        inGridLayout00.setText(Integer.toString(answers.get(0)));
        inGridLayout01.setText(Integer.toString(answers.get(1)));
        inGridLayout10.setText(Integer.toString(answers.get(2)));
        inGridLayout11.setText(Integer.toString(answers.get(3)));
    }

    public void dropIn(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            messages_TextView.setText("Correct!");

        } else {

            messages_TextView.setText("Wrong!");

        }

        challenges_TextView();
        timesTouch++;
        newScore = score * 100;
        performance = (int) Math.floor(newScore / timesTouch);
        score_TextView.setText(Integer.toString(score) + "/" + Integer.toString(timesTouch));

        // Change color of buttons every click.

        String[] colorsOne = {"#2962ff", "#00bfa5", "#ff6d00", "#aa00ff", "#c62828", "#2196f3", "#00bcd4"};
        String[] colorsTwo = {"#d4e157", "#ff9800", "#bcaaa4", "#90a4ae", "#ff3d00", "#00e676", "#00e5ff"};
        Random random = new Random();
        int myRandString1 = random.nextInt(colorsOne.length);
        int myRandString2 = random.nextInt(colorsTwo.length);
        int myRandString3 = random.nextInt(colorsOne.length);
        int myRandString4 = random.nextInt(colorsTwo.length);

        // My Buttons

        inGridLayout00.setBackgroundColor(Color.parseColor(colorsOne[myRandString1]));
        inGridLayout01.setBackgroundColor(Color.parseColor(colorsTwo[myRandString2]));
        inGridLayout10.setBackgroundColor(Color.parseColor(colorsTwo[myRandString3]));
        inGridLayout11.setBackgroundColor(Color.parseColor(colorsOne[myRandString4]));

    }

    public void controlTimer() {
        new CountDownTimer(31000, 1000) {
            public void onTick(long millisecondsUntilDone) {
                // Counter is counting down (30 seconds - every second)
                timer_TextView.setText(String.valueOf(millisecondsUntilDone / 1000));

            }

            public void onFinish() {

                // Counter is finished! (after 30 seconds and start one sound)
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mplayer.start();
                playAgain_Button.setVisibility(View.VISIBLE);
                messages_TextView.setText("Score: " + (Integer.toString(score) + "/" + Integer.toString(timesTouch)
                                                    + "\nPerformance: " + performance + "%"));
                challenges_TextView.setText("");
                timer_TextView.setText("");
                score_TextView.setText("");
                inGridLayout00.setText("");
                inGridLayout00.setEnabled(false);
                inGridLayout01.setText("");
                inGridLayout01.setEnabled(false);
                inGridLayout10.setText("");
                inGridLayout10.setEnabled(false);
                inGridLayout11.setText("");
                inGridLayout11.setEnabled(false);
            }
        }.start();
    }

    public void myFindViewByIds() {
        //LinearLayout
        start_LinearLayout = findViewById(R.id.start_LinearLayout);

        //TextView
        timer_TextView = findViewById(R.id.timer_TextView);
        challenges_TextView = findViewById(R.id.challenges_TextView);
        messages_TextView = findViewById(R.id.messages_TextView);
        score_TextView = findViewById(R.id.score_TextView);

        //Buttons
        playAgain_Button = findViewById(R.id.playAgain_Button);
        inGridLayout00 = findViewById(R.id.inGridLayout00);
        inGridLayout01 = findViewById(R.id.inGridLayout01);
        inGridLayout10 = findViewById(R.id.inGridLayout10);
        inGridLayout11 = findViewById(R.id.inGridLayout11);
    }
}
