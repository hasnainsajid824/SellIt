package com.example.sellit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class signup_activity extends AppCompatActivity {
    Button login;
    private EditText emailTextView, passwordTextView,password20;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.editTextTextEmailAddress2);
        passwordTextView = findViewById(R.id.signupassowrd);
        password20=findViewById(R.id.editTextTextPassword);
        Button btn = findViewById(R.id.signupbtn);


        // Set on Click Listener on Registration button
        btn.setOnClickListener(v -> registerNewUser());
        login = findViewById(R.id.logbtn);
        login.setOnClickListener(
                v -> {
                    Intent i = new Intent(signup_activity.this,login_activity.class);
                    startActivity(i);
                }
        );
    }

    private void registerNewUser()
    {

        // Take the value of two edit texts in Strings
        String email, password,password2;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        password2=password20.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (!TextUtils.equals(password,password2)) {
            Toast.makeText(getApplicationContext(),
                            "Password Did not Match!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),
                                        "Registration successful!",
                                        Toast.LENGTH_LONG)
                                .show();


                        // if the user created intent to login activity
                        Intent intent
                                = new Intent(signup_activity.this,
                                login_activity.class);
                        startActivity(intent);
                    }
                    else {

                        // Registration failed
                        Toast.makeText(
                                        getApplicationContext(),
                                        "Registration failed!!"
                                                + " Please try again later",
                                        Toast.LENGTH_LONG)
                                .show();

                    }
                });
    }
    }
