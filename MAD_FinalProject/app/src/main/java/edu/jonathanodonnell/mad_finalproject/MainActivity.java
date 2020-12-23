package edu.jonathanodonnell.mad_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;
    Button bBlue, bRed, bYellow, bGreen;

    int[] gameSequence = new int[120];
    int arrayIndex = 0;
    int sequenceCount = 4, n = 0;
    View view1;

    CountDownTimer ct = new CountDownTimer(6000, 1500) {

        public void onTick(long millisUntilFinished) {

            oneButton();

        }

        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));

            Intent Play = new Intent(view1.getContext(), play_screen.class);
            startActivity(Play);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBlue = findViewById(R.id.btn);
        bRed = findViewById(R.id.btn2);
        bYellow = findViewById(R.id.btn3);
        bGreen = findViewById(R.id.btn4);
    }




        public void doPlay(View view) {
            flashButton(bBlue);
            ct.start();

        }

        private void oneButton() {
            n = getRandom(sequenceCount);

            Toast.makeText(view1.getContext(), "Number = " + n, Toast.LENGTH_SHORT).show();

            switch (n) {
                case 1:
                    flashButton(bBlue);
                    gameSequence[arrayIndex++] = BLUE;
                    break;
                case 2:
                    flashButton(bRed);
                    gameSequence[arrayIndex++] = RED;
                    break;
                case 3:
                    flashButton(bYellow);
                    gameSequence[arrayIndex++] = YELLOW;
                    break;
                case 4:
                    flashButton(bGreen);
                    gameSequence[arrayIndex++] = GREEN;
                    break;
                default:
                    break;
            }
        }

        private void flashButton(Button button) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    button.setPressed(true);
                    button.invalidate();
                    button.performClick();
                    Handler handler1 = new Handler();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            button.setPressed(false);
                            button.invalidate();


                        }
                    };
                    handler1.postDelayed(r1, 400);

                }
            };
            handler.postDelayed(r, 400);
        }
    }
