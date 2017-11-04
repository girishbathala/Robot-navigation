/*
Author : Girish Bathala 22/04/17
This InteractiveModeActivity allows the users to enter the inputs for the modified version 1.0 of
the given problem. The source location and source direction to be reteived are saved and
validated for wrong input formats.
 */
package com.example.malli.robotnavigator;
// Import the required libraries
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InteractiveModeActivity extends AppCompatActivity {
    Button btnGo;                   // Button to begin the game
    EditText editTextSrcLoc;        // EditText to retrieve the user-entered source location
    EditText editTextSrcDir;        // EditText to retrieve the user-entered source direction
    Bundle extras = new Bundle();   // To transmit the user entered strings to the next activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Link the corresponding XML file
        setContentView(R.layout.activity_interactive_mode);
        // Set IDs for the  buttons and the Edittexts
        btnGo = (Button)findViewById(R.id.btnGo);
        editTextSrcDir = (EditText) findViewById(R.id.editTextSrcDir);
        editTextSrcLoc = (EditText) findViewById(R.id.editTextSrcLoc);
        // As soon as go is clicked, the user entered data is read and validated for errors.
        // If no errors are found, the game is started
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to glue this activity to the next
                Intent intent = new Intent(InteractiveModeActivity.this,InteractiveModeMatrixActivity.class);
                String SrcLoc = editTextSrcLoc.getText().toString();
                String SrcDir = editTextSrcDir.getText().toString();
                // VALIDATE THE SOURCE LOCATION, IF EMPTY OR NOT INTEGER, THROW an ERROR
                boolean srcLocFlag = isInteger(SrcLoc,10);
                if (srcLocFlag){
                    // No problem with input source location format, send to the bundle.
                    String[] locArr = SrcLoc.trim().split("\\s+");
                    Integer src_x = Integer.parseInt(locArr[0]);
                    Integer src_y = Integer.parseInt(locArr[1]);
                    extras.putString("EXTRA_SRC_LOC",SrcLoc);
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(InteractiveModeActivity.this).create();
                    alertDialog.setTitle("Error : Invalid Source Location");
                    alertDialog.setMessage( "The input format should be of the form \"i j\" "+
                            "where i and j are integers between 0 and 9. ( Example: 8 2)");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                boolean dirFlag = isDirection(SrcDir);
                if(dirFlag){
                    // No problem with input source direciton format, send to the bundle.
                    extras.putString("EXTRA_SRC_DIR",SrcDir);
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(InteractiveModeActivity.this).create();
                    alertDialog.setTitle("Error : Invalid Direction");
                    alertDialog.setMessage( "The input format of direction should be of the form \"D\" "+
                            "where valid directions 'D' allowed are \"N\",\"E\",\"W\" and \"S\". ( Example: N )");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
                intent.putExtras(extras);
                // Start Next activity only if all the inputs are valid
                if(srcLocFlag && dirFlag){
                    startActivity(intent);
                }
            }
        });
    }
    // Method to validate the source location for any errors
    public static boolean isInteger(String s, int radix) {
        s = s.trim();
        if(s.isEmpty()) return false;
        if(s.length() != 3) return  false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(s.charAt(i) == ' ') continue;
            if(Character.digit(s.charAt(i),radix) < 0) return false;
            if(Integer.parseInt(s.valueOf(s.charAt(i))) > 8 && Integer.parseInt(s.valueOf(s.charAt(i))) < 1 ) return  false;
        }
        return true;
    }
    // Method to validate the source direction for any errors
    public  static boolean isDirection(String s){
        s = s.trim().toLowerCase();
        if (s.isEmpty()) return false;
        if (s.length() != 1) return false;
        if(s.equals("n") == false && s.equals("w") == false && s.equals("s") == false && s.equals("e") == false ) return false;
        return true;
    }
}
