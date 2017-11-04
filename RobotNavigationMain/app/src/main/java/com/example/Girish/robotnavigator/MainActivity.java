/*
Author : Girish Bathala 22/04/17
This Main Activity serves as the home screen for the robot navigator application.
The user is allowed two choices through buttons to either select the preset mode game or the
interactive mode game.
 */
package com.example.malli.robotnavigator;
// Import the required classes and libraries
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
public class MainActivity extends AppCompatActivity {
    LinearLayout backgorund;       // Access the background of the linear layout for Main Activity
    Button btnPreset;              // Button to navigate to the Preset Mode Game activity
    Button btnInter;               // Button to navigate to the Interactive Mode Game activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the XML view
        setContentView(R.layout.activity_main);
        // Retieve IDs of the linear layout and buttons
        backgorund = (LinearLayout) findViewById(R.id.background);
        btnPreset = (Button) findViewById(R.id.btnPreset);
        btnInter = (Button) findViewById(R.id.btnInter);
        // Check if the preset mode game button is pressed
        //If yes, navigate to the PresetMode Activity
        btnPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this,PresetModeActivity.class);
                startActivity(mainIntent);
            }
        });
        // Check if the interactive mode game button is pressed
        // If yes, navigate to InteractiveModeActivity
        btnInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this,InteractiveModeActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
