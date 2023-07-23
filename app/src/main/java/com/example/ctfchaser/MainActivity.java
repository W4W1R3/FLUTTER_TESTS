package com.example.ctfchaser;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
                button.setOnClickListener(view -> {
                    Toast.makeText(MainActivity.this, "Loading Sign Up Page." , Toast.LENGTH_LONG).show();
                    button.setClickable(false);
                         new Handler().postDelayed(() -> {
                             Intent Sn = new Intent(MainActivity.this,SignupActivity.class);
                             startActivity(Sn);
                             finish();
        },3000);
                });


    }
}