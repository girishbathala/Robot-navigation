/*
Author : Girish Bathala 22/04/17
This activity provides the user interface for the version 1.0 asked in the question.
It implements all the desired functions based on the input source location, direction
and action sequence. A robot class is instantiated for this purpose.
 */
package com.example.malli.robotnavigator;
// Import the required classes and libraries
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.id.message;
public class PresetModeMatrixActivity extends AppCompatActivity {

    Button btnBack;                         // Button to go back to the previous activity
    Button btnHighlight,btnHighlightSrc;    // Button to keep track of the location of the bot
    TextView dstLocTv;                      // TextView for the final location of the bot
    TextView dstDirTV;                      // TextView for the final direction of the bot
    TextView srcLocTV;                      // TextView for the source location of the bot
    TextView srcDirTV;                      // TextView for the source direction of the bot
    String[] outputStr = new String[3];     // To retrive the final location and direction of the bot
    String[][] lookUp = new String[8][8];   // The 8x8 grid for the matrix
    int resID,resIDSrc;                     // ID of the 8x8 buttons and src button
    Integer[] loc = new Integer[2];         // Store the source location of the bot
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the XML view
        setContentView(R.layout.activity_preset_mode_matrix);
        // Create a lookup for the buttons
        for (int indy = 1; indy <=8; indy++){
            for (int indx = 1; indx <=8; indx ++){
                lookUp[indx-1][indy-1] = "btn" + Integer.toString(indy) + Integer.toString(indx);
            }
        }
        // Get Intent which started the activity
        Intent intent = getIntent();
        // Extract the string from the precious activity (aka the user entered values)
        Bundle extras = intent.getExtras();
        String SrcLoc = extras.getString("EXTRA_SRC_LOC");
        String SrcDir = extras.getString("EXTRA_DIR_FAC");
        String Act = extras.getString("EXTRA_ACT");
        // Get the source location of the bot
        String[] locArr = SrcLoc.split(" ");
        Integer src_x = Integer.parseInt(locArr[0]);
        Integer src_y = Integer.parseInt(locArr[1]);
        // Convert actions to array of  acitons to corresponding enum array
        Actions A1 = new Actions(Act);
        actions_enum actionEnum[] = A1.convertActionsToEnum();
        // Convert actions to array of  direction to corresponding enum
        Directions D1 = new Directions(SrcDir);
        direction_enum src_dir = D1.convertDirToEnum();
        // Instantiate the robot
        Robot r1 = new Robot();
        // Set the source location and direction
        r1.set_values(src_x,src_y,src_dir);
        // Highlight the source location in GREEN
        resIDSrc = getResources().getIdentifier(lookUp[src_x-1][src_y-1], "id", getPackageName());
        btnHighlightSrc= (Button)findViewById(resIDSrc);
        btnHighlightSrc.setBackgroundColor(Color.GREEN);
        // Iterate overall the actions possible and move the robot around depending on the action
        for (int k=0;k<actionEnum.length;k++){
            switch(actionEnum[k]){
                case move: loc = r1.moveForward(); break;
                case leftT: loc = r1.leftTurn(); break;
                case rightT: loc = r1.rightTurn(); break;
            }
            // Color the current location of the bot in YELLOW
            resID = getResources().getIdentifier(lookUp[loc[0]-1][loc[1]-1], "id", getPackageName());
            btnHighlight= (Button)findViewById(resID);
            btnHighlight.setBackgroundColor(Color.YELLOW);
            // To make sure the color of the source location is not overwritten
            btnHighlightSrc.setBackgroundColor(Color.GREEN);
        }
        // Color the final location of the bot in YELLOW
        btnHighlight.setBackgroundColor(Color.RED);
        // Retrieve the final location  and final direction of the bot
        outputStr = r1.printDst();
        // Display the final location, direction and source location, direction of the bot
        dstLocTv = (TextView) findViewById(R.id.tvIdDstLoc);
        srcLocTV = (TextView) findViewById(R.id.tvIdSrcLoc);
        dstDirTV = (TextView) findViewById(R.id.tvIdDstDir);
        srcDirTV = (TextView) findViewById(R.id.tvIdSrcDir);
        dstLocTv.setText("Dst Location : " +outputStr[0]+", " + outputStr[1]);
        dstDirTV.setText("Dst Direction : "+outputStr[2]);
        srcLocTV.setText("Src Location : " + Integer.toString(src_x) +", " +  Integer.toString(src_y));
        srcDirTV.setText("Src Direction : "+ src_dir.toString());
        // Button to go back to previous activity and try again.
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresetModeMatrixActivity.this,PresetModeActivity.class);
                startActivity(intent);
            }
        });
    }
}
