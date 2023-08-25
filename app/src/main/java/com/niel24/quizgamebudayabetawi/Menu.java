package com.niel24.quizgamebudayabetawi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menu extends AppCompatActivity implements View.OnClickListener {


    public CardView card1, card2, card3, card4, card5, card6;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

            Intent i;
            switch (v.getId()) {
                case R.id.c1:
                    i = new Intent(this, Start.class);
                    startActivity(i);
                    break;
                case R.id.c2:
                    i = new Intent(Menu.this, Done.class);
                    startActivity(i);
                    break;

                case R.id.c3:
                    i = new Intent(Menu.this, HighScore.class);
                    startActivity(i);
                    break;
                case R.id.c4:
                    i = new Intent(this, Answer.class);
                    startActivity(i);
                    break;

                default:
                    break;
            }
        }
    }
