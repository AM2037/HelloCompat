package com.example.android.hellocompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextview;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextview = findViewById(R.id.hello_textview);

        // Restore saved instance state (text color)
        if (savedInstanceState != null) {
            mHelloTextview.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    public void changeColor(View view) {
        Random random = new Random();
        // gets another random int between 0 and 19 -- use int as index to get color name
        String colorName = mColorArray[random.nextInt(20)];
        // get int for colorname from resources
        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());
        // get int ID for actual color and assign to colorRes variable; use getTheme() for current
        // application context. To support more devices change following line:
        // int colorRes = getResources().getColor(colorResourceName, this.getTheme());
        int colorRes = ContextCompat.getColor(this, colorResourceName);
        mHelloTextview.setTextColor(colorRes);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save current text color
        outState.putInt("color", mHelloTextview.getCurrentTextColor());
    }
}
