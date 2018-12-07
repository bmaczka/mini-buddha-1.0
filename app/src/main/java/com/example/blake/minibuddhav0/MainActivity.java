package com.example.blake.minibuddhav0;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    //step 1: define object
    private Button loginButton, registerButton;
    private EditText emailText, passwordText, userText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //step 2: connect object w layout widget
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        userText = findViewById(R.id.userText);

        //step 3b attach the listener to the object
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        userText.setOnClickListener(this);

    }




    private void registerUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FirebaseUser user = mAuth.getCurrentUser()

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent LoginActivityIntent = new Intent(MainActivity.this, ThreeGoodThings.class);
                            startActivity(LoginActivityIntent);
                        } else {

                        }

                        // ...
                    }
                });
    }

    public void onClick(View view) {
        String password, email, username;
        password = passwordText.getText().toString().toUpperCase();
        email = emailText.getText().toString().toUpperCase();
        username = userText.getText().toString().toUpperCase();

        if(email.length() < 6){
            Toast.makeText(this, "Too short!",Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 8){
            Toast.makeText(this, "Too short!",Toast.LENGTH_SHORT).show();
        }
        else if(view == registerButton){
            registerUser(email, password);
            Toast.makeText(this, "Succesfully registered",Toast.LENGTH_SHORT).show();
        }
        else if(view == loginButton){
            login(email, password);
            final User user = new User(email);
            user.setUser(username);
        }

    }
}
