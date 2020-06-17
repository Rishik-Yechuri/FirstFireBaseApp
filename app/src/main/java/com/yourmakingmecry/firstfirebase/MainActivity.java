package com.yourmakingmecry.firstfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.functions.FirebaseFunctions;

public class MainActivity extends AppCompatActivity {
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusText = findViewById(R.id.statusText);
        FirebaseFunctions.getInstance() // Optional region: .getInstance("europe-west1")
                .getHttpsCallable("createUser")
                .call();
    }
}
