package com.niel24.quizgamebudayabetawi;

import static com.niel24.quizgamebudayabetawi.Common.Common.currentUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niel24.quizgamebudayabetawi.Common.Common;
import com.niel24.quizgamebudayabetawi.Model.QuestionScore;
import com.niel24.quizgamebudayabetawi.Model.User;

public class Skor extends AppCompatActivity {

    Button btnMenu;
    TextView txtSkor;


    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");
        


        btnMenu = (Button) findViewById(R.id.btn_Menu);
        txtSkor = (TextView) findViewById(R.id.txt_TotalScore);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Skor.this,Menu.class);
                startActivity(intent);
                finish();
            }
        });


        }


    }



