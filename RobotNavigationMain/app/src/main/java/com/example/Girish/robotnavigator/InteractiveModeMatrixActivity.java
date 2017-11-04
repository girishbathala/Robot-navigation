/*
Author : Girish Bathala 22/04/17
This InteractiveModeMatrix activity provides a user interface of a slightly modified version 1.0
in PresetModeMatrixActivity .It implements all the desired functions based on the input source
location, and direction. A robot class is instantiated for this purpose. The user in now allowed
to interact and move the robot around with a set of keys.
 */
package com.example.malli.robotnavigator;
// Import the required classes and libraries
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class InteractiveModeMatrixActivity extends AppCompatActivity {
    Button btnHighlight,btnHighlightSrc;        // Button to keep track of the location of the bot
    ImageButton btnBack;                        // ImageButton to go back to the previous activity
    ImageButton btnUp;                          // ImageButton to navigate the bot forward
    ImageButton btnLeft;                        // ImageButton to make the bot turn left
    ImageButton btnRight;                       // ImageButton to make the bot turn right
    int resIDSrc,resID;                         // ID of the 8x8 buttons and src button
    String[][] lookUp = new String[8][8];       // The 8x8 grid for the matrix
    Robot r1 = new Robot();                     // Instantiate the robot
    Integer[] loc = new Integer[2];             // Source loaction of the bot
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the XML view
        setContentView(R.layout.activity_interactive_mode_matrix);
        // Create a lookup for the buttons
        for (int indy = 1; indy <=8; indy++){
            for (int indx = 1; indx <=8; indx ++){
                lookUp[indx-1][indy-1] = "btn" + Integer.toString(indy) + Integer.toString(indx);
            }
        }
        // Get Intent which started the activity
        Intent intent = getIntent();
        // Extract the string from the previous activity
        Bundle extras = intent.getExtras();
        String SrcLoc = extras.getString("EXTRA_SRC_LOC");
        String SrcDir = extras.getString("EXTRA_SRC_DIR");
        // Get source location of the bot
        String[] locArr = SrcLoc.split(" ");
        Integer src_x = Integer.parseInt(locArr[0]);
        Integer src_y = Integer.parseInt(locArr[1]);
        // Convert array of  directions to corresponding enum
        Directions D1 = new Directions(SrcDir);
        direction_enum src_dir = D1.convertDirToEnum();
        // Set the source location and direction
        r1.set_values(src_x,src_y,src_dir);
        // Highlight the source locationin GREEN
        resIDSrc = getResources().getIdentifier(lookUp[src_x-1][src_y-1], "id", getPackageName());
        btnHighlightSrc= (Button)findViewById(resIDSrc);
        btnHighlightSrc.setBackgroundColor(Color.GREEN);
        // ID to highlight the current location of the bot
        btnHighlight= (Button)findViewById(resIDSrc);
        // Action to take when the up button is pressed
        btnUp = (ImageButton)findViewById(R.id.imageButtonUp);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHighlight.setBackgroundColor(Color.YELLOW);
                loc = r1.moveForward();
                resID = getResources().getIdentifier(lookUp[loc[0]-1][loc[1]-1], "id", getPackageName());
                btnHighlight= (Button)findViewById(resID);
                btnHighlight.setBackgroundColor(Color.RED);
                btnHighlightSrc.setBackgroundColor(Color.GREEN);
            }
        });
        // Action to take when the Left button is pressed
        btnLeft = (ImageButton)findViewById(R.id.imageButtonLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHighlight.setBackgroundColor(Color.YELLOW);
                loc = r1.leftTurn();
                resID = getResources().getIdentifier(lookUp[loc[0]-1][loc[1]-1], "id", getPackageName());
                btnHighlight= (Button)findViewById(resID);
                btnHighlight.setBackgroundColor(Color.RED);
                btnHighlightSrc.setBackgroundColor(Color.GREEN);
            }
        });
        // Action to take when the Right button is pressed
        btnRight = (ImageButton)findViewById(R.id.imageButtonRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHighlight.setBackgroundColor(Color.YELLOW);
                loc = r1.rightTurn();
                resID = getResources().getIdentifier(lookUp[loc[0]-1][loc[1]-1], "id", getPackageName());
                btnHighlight= (Button)findViewById(resID);
                btnHighlight.setBackgroundColor(Color.RED);
                btnHighlightSrc.setBackgroundColor(Color.GREEN);
            }
        });
        // Go back to the previous activity
        btnBack = (ImageButton)findViewById(R.id.imageButtonRetry);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractiveModeMatrixActivity.this,InteractiveModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
