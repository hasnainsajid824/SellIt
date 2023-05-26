package com.example.sellit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailTextView, passwordTextView;
    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.editTextTextEmailAddress);
        passwordTextView = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(view -> loginUserAccount());


        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(
                v -> {
                    Intent i = new Intent(login_activity.this,signup_activity.class);
                    startActivity(i);
                }
        );
    }
    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
//        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
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

        // signing existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                                "Login successful!!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
//                                    progressBar.setVisibility(View.GONE);

                                // if sign-in is successful
                                // intent to home activity
                                Intent intent
                                        = new Intent(login_activity.this,
                                        drawer_activity.class);
                                startActivity(intent);
                            }

                            else {

                                // sign-in failed
                                Toast.makeText(getApplicationContext(),
                                                "Login failed!!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
//                                    progressbar.setVisibility(View.GONE);
                            }
                        });
    }
}