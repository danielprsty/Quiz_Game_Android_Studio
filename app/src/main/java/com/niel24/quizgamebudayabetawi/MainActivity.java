package com.niel24.quizgamebudayabetawi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niel24.quizgamebudayabetawi.Common.Common;
import com.niel24.quizgamebudayabetawi.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    MaterialEditText edtNewName,edtNewNim,edtNewUserName,edtNewPassword;
    MaterialEditText edtUserName,edtPassword;
    MediaPlayer audioBackground;

    Button btnSignUp,btnLogin;

    FirebaseDatabase database;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioBackground = MediaPlayer.create(this,R.raw.ondel);
        audioBackground.setLooping(true);
        audioBackground.setVolume(1,1);
        audioBackground.start();

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUserName = (MaterialEditText)findViewById(R.id.edtUserName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignUpDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtUserName.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void signIn(String user, String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(user).exists())
                {
                    if (!user.isEmpty()) {
                        User login = snapshot.child(user).getValue(User.class);
                        if (login.getPassword().equals(pwd))
                        {
                            Intent menuActivity;
                            menuActivity = new Intent(MainActivity.this,Menu.class);
                            Common.currentUser = login;
                        startActivity(menuActivity);
                        finish();
                    }
                        else
                            Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Please enter your user name", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "User is not Exist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Create Your Account");
        alertDialog.setMessage("Please fill full information");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        edtNewName = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewName);
        edtNewNim = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewNim);
        edtNewUserName = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewPassword = (MaterialEditText) sign_up_layout.findViewById(R.id.edtNewPassword);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ondel1);



        alertDialog.setPositiveButton("CREATE ACCOUNT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                User user = new User(edtNewName.getText().toString(),
                        edtNewNim.getText().toString(),
                        edtNewUserName.getText().toString(),
                        edtNewPassword.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child(user.getUserName()).exists())
                            Toast.makeText(MainActivity.this,"User already exist !", Toast.LENGTH_SHORT).show();
                        else
                        {
                            users.child(user.getUserName())
                                    .setValue(user);
                            Toast.makeText(MainActivity.this,"User registration success!", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();



    }
}