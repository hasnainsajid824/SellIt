package com.example.sellit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_activity extends AppCompatActivity {
    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(login_activity.this,drawer_activity.class);
                        startActivity(i);
                    }
                }
        );

        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(login_activity.this,signup_activity.class);
                        startActivity(i);
                    }
                }
        );
    }
}