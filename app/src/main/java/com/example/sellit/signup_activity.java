package com.example.sellit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class signup_activity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail;
    TextInputEditText editTextPassword;

    TextInputEditText confirmTextPassword;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword= (TextInputEditText) findViewById((R.id.signupassowrd));
        confirmTextPassword = (TextInputEditText) findViewById((R.id.editTextTextPassword));

        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.signupbtn).setOnClickListener(this);
        findViewById(R.id.backlogbtn).setOnClickListener(this);
    }

    private void registerUser(){
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

        if(password.length()<6 || password.length()>15){
            editTextPassword.setError("Password should be of 6-15 characters");
            editTextPassword.requestFocus();
            return;
        }

        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            //editTextPassword.setError("Password should contain atleast one upper case alphabet");
            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
            editTextPassword.requestFocus();
            return;
        }

        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
            editTextPassword.requestFocus();
            return;
        }

        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers))
        {
            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
            editTextPassword.requestFocus();
            return;
        }

        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (!password.matches(specialChars ))
        {
            editTextPassword.setError("Password should contain at least one number, one lowercase letter, one uppercase letter, and one special character.");
            editTextPassword.requestFocus();
            return;
        }

        if (editTextPassword == confirmTextPassword)
        {
            editTextPassword.setError("Password does not Match. Please Enter Correct password.");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                     Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                     Intent intent=new Intent(signup_activity.this,drawer_activity.class);
                     intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                     finish();
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        editTextPassword.setText("");
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editTextPassword.setText("");
                        Toast.makeText(signup_activity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.signupbtn:
                registerUser();
                break;

            case R.id.backlogbtn:
                Intent intentLogin = new Intent(this, login_activity.class);
                intentLogin.addFlags(intentLogin.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLogin);
                finish();

                break;
        }
    }
}