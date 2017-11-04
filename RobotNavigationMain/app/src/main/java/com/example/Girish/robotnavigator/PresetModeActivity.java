/*
Author : Girish Bathala 22/04/17
This PresetModeActivity allows the users to enter the inputs for the version 1.0 of the given
problem. The source location, source direction and the action sequences to be taken are saved and
validated for wrong input formats.
 */
package com.example.malli.robotnavigator;
//Import the required classes and libraries
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PresetModeActivity extends AppCompatActivity {
    Button btnGo;           // Button to begin the game
    EditText eTSrcLoc;      // EditText to retrieve the user-entered source location
    EditText eTDirFac;      // EditText to retrieve the user-entered source direction
    EditText eTAct;         // EditText to retrieve the user-entered action sequences
    Bundle extras = new Bundle(); //extras to send the user inputs to the next activity.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Link the XML layout
        setContentView(R.layout.activity_preset_mode);
        // Create IDs for Buttons and edittexts
        btnGo = (Button)findViewById(R.id.btnGo);
        eTSrcLoc = (EditText)findViewById(R.id.etSrcLoc);
        eTDirFac = (EditText)findViewById(R.id.etDirFac);
        eTAct = (EditText)findViewById(R.id.etAct);
        // Check if the Go button is clicked.
        // If yes, retrieve all the user information, validate it and send it to next activity
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SrcLoc = eTSrcLoc.getText().toString();
                String DirFac = eTDirFac.getText().toString();
                String Act = eTAct.getText().toString();
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
                    AlertDialog alertDialog = new AlertDialog.Builder(PresetModeActivity.this).create();
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
                // VALIDATE THE ACTION SEQUENCE, IF EMPTY OR NOT "M" or "R" or "L", THROW an ERROR
                boolean actionFlag = isAction(Act);
                if(actionFlag){
                    // No problem with input action sequences format, send to the bundle.
                    extras.putString("EXTRA_ACT",Act);
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(PresetModeActivity.this).create();
                    alertDialog.setTitle("Error : Invalid Action Sequence");
                    alertDialog.setMessage( "The input format action sequence should be of the form \"A1A2A3...AN\" "+
                            "without space and valid actions allowed are \"M\",\"L\" and \"R\". ( Example: MMMRMMLM )");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
                // VALIDATE THE DIRECTION, IF EMPTY OR NOT "N" or "S"or "E" or "W". THROW an ERROR
                boolean dirFlag = isDirection(DirFac);
                if(dirFlag){
                    // No problem with input direction format, send to the bundle.
                    extras.putString("EXTRA_DIR_FAC",DirFac);
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(PresetModeActivity.this).create();
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
                // Start Next activity only if all the inputs are valid
                if(srcLocFlag && dirFlag && actionFlag){
                    // Intent to glue this activity to the next
                    Intent intent = new Intent(PresetModeActivity.this,PresetModeMatrixActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
               }
            }
        });
    }
    // Method to validate the source locations for any errors
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
    // Method to validate the action sequences for any errors
    public  static  boolean isAction(String s) {
        s = s.trim().toLowerCase();
        if (s.isEmpty()) return false;
        if (s.split("\\s+").length != 1) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.toString(s.charAt(i)).equals("m") || Character.toString(s.charAt(i)).equals("r") || Character.toString(s.charAt(i)).equals("l")) {
                continue;
            }
            else {
                return false ;
            }
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
