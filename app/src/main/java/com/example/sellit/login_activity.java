package com.example.sellit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_activity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    EditText editTextEmail;
    TextInputEditText editTextPassword;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.log_Email);
        editTextPassword = (TextInputEditText) findViewById(R.id.log_password);

        mAuth=FirebaseAuth.getInstance();

        findViewById(R.id.loginbtn).setOnClickListener(this);

        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new
                                Intent(login_activity.this,signup_activity.class);
                        startActivity(i);
                    }
                }
        );
    }




    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

//
//        if(password.length()<6 || password.length()>15){
//            editTextPassword.setError("Password should be of 6-15 characters");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        String upperCaseChars = "(.*[A-Z].*)";
//        if (!password.matches(upperCaseChars ))
//        {
//            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        String lowerCaseChars = "(.*[a-z].*)";
//        if (!password.matches(lowerCaseChars ))
//        {
//            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        String numbers = "(.*[0-9].*)";
//        if (!password.matches(numbers))
//        {
//            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
//        if (!password.matches(specialChars ))
//        {
//            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
//            editTextPassword.requestFocus();
//            return;
//        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Intent intent = new Intent(login_activity.this, drawer_activity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(login_activity.this,drawer_activity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginbtn:
                userLogin();
                break;
        }

    }
}