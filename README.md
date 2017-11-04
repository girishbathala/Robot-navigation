# Robot-navigation
## (Android Application using android studio)

## Desctription
An android application which has two modes of operation. First, the preset mode, in which
the user can enter the source location, source direction and sequence of actions. The robot navigates
to a final lcoation based on these sequence of actions. Second, the interatice mode, in this arrow keys
are provided for the user to navigate along different paths and directions after specifying a start 
lcoation and direction.  

## Files structure
Apk File  
RobotNavigator(All source files)  
README  
Screenshots  


## Example
Given a starting position [x,y] (0<x,y<9), initial direction faced (W, S, N, E) on 8 x 8 
square board and sequence of actions for a robot, print the outcome; direction faced and position on
the board.  
Allowed Actions:   
M: Move 1 square forward  
L: Turn left  
R: Turn right  
Note: When/if robot comes to the end of the board it cannot move beyond the board boundaries. At a
boundary, it can only turn left/right.  
Example input:  
Location: [2,3]  
Direction faced: N  
Actions: M,M,M,L,M,R,R,R  
Output:  
Location : [1,6]  
Direction faced:S  

## Usage
1. Run the application: To run the application, go to the Apk File folder and copy the file app-debug.apk
into an android phone with version 4.0 (ICS) and above. After installing the app, follow the screens
to play either of the two modes described earlier.  

2. Source Code: The entire project source code resides in the RobotNavigator folder. The Java code for 
the two modes resides in \RobotNavigator\app\src\main\java\com\example\Girish\robotnavigator and the 
corresponding code for the User Interface resides in \RobotNavigator\app\src\main\res\layout.  

3. Screenshots of the application can be found in the Screenshots folder.  
==========================================================================================================
