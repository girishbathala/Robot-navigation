/*
Author : Girish Bathala 22/04/17
The Directions class is used to convert the user entered direction string into it's corresponding
enum value (north,east,south and west) for easy understanding and representation. A string is passed
 to the class through an instance of the class (object) and the method convertDirToEnum is used to
 perform the conversion
 */
package com.example.malli.robotnavigator;

public class Directions {
    private String direction;
    public Directions(String input) {
        direction = input;
    }

    public direction_enum convertDirToEnum(){
        if( direction.equals("N")) return direction_enum.north;
        else if( direction.equals("E")) return direction_enum.east;
        else if( direction.equals("S")) return direction_enum.south;
        else  return direction_enum.west;
    }
}
