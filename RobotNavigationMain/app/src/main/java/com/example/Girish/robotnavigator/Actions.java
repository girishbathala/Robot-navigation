/*
Author : Girish Bathala 22/04/17
The Actions class is used to convert the user entered action sequence string into it's corresponding
enum values for easy understanding and representation. A string is passed to the class through an
instance of the class (object) and the method convertActionsToEnum is used to perform the conversion
 */
package com.example.malli.robotnavigator;

public class Actions {
    private String actions;
    public Actions(String input) {
        actions = input;
    }
    public actions_enum[] convertActionsToEnum(){

        char[] ch=actions.toCharArray();
        actions_enum[] ActionsArray = new actions_enum[ch.length];
        for(int i=0;i<ch.length;i++){
            if(ch[i] == 'M')  ActionsArray[i]= actions_enum.move ;
            else if(ch[i] == 'L') ActionsArray[i]= actions_enum.leftT ;
            else if(ch[i] == 'R') ActionsArray[i]= actions_enum.rightT;;
        }
        return ActionsArray;
    }
}
