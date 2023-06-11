package com.example.sellit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_splash_screen extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the splash screen layout
        setContentView(R.layout.activity_splash_screen);

        // Delay the transition to the next activity
        new Handler().postDelayed(() -> {
            // Start your main activity here
            Intent intent = new Intent(Activity_splash_screen.this, login_activity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}
