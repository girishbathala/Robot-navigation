// Author : Girish Bathala
// The Robot class is used to actually perform the actions on the bot. The states of the class are
// loc_x, loc_y and current_dir. The methods specified in this calss are moveForward(), turnLeft(),
// turnRight() and printDst(). These perform the core of all the operations in the version 1.0 of
// this application.
package com.example.malli.robotnavigator;
public class Robot {
    // Setting the max and min values the location of the bot can take
    int maxX = 8;
    int maxY = 8;
    int minX = 1;
    int minY = 1;
    // The locaiton of the bot at a current time instant t.
    int loc_x, loc_y;
    // The direction of the bot at a current time instant t.
    direction_enum current_dir;
    // This method is used to set the values of the above declared states.
    public void set_values (int x,int y,direction_enum dir){
        loc_x = x;
        loc_y = y;
        current_dir = dir;
    }
    // This method performs the task of moving the bot forward based on it's current location.
    // Depending on whether it is facing south, east, west or north the bot is moved along the
    // corresponding direction.
    public Integer[] moveForward(){
        switch(current_dir)
        {
            case west : if(loc_x > minX){
                loc_x --;
            }
                        break;
            case east : if(loc_x < maxX){
                loc_x ++;
            }
                break;
            case north  : if(loc_y < maxY){
                loc_y ++;
            }
                break;
            case south  : if(loc_y > minY){
                loc_y --;
            }
                break;
        }
        Integer[] temp = {loc_x,loc_y};
        return temp;
    }
    // Based on the current direction of the bot, the bot is given a new direction after turning
    // left.
    public Integer[] leftTurn(){
        if(current_dir == direction_enum.north){
            current_dir  = direction_enum.west;
        }
        else{
            current_dir = direction_enum.values()[current_dir.ordinal() - 1];
        }
        Integer[] temp = {loc_x,loc_y};
        return temp;
    }
    // Based on the current direction of the bot, the bot is given a new direction after turning
    // right.
    public Integer[] rightTurn(){
        if(current_dir == direction_enum.west){
            current_dir  = direction_enum.north;
        }
        else{
            current_dir = direction_enum.values()[current_dir.ordinal() + 1];
        }
        Integer[] temp = {loc_x,loc_y};
        return temp;
    }
    // This method is used to retrieve the final location and direction of the bot
    public String[] printDst(){
        String[] outputList = {Integer.toString(loc_x), Integer.toString(loc_y), current_dir.toString()};
        return outputList;
    }
}

