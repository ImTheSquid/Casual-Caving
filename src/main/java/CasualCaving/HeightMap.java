package CasualCaving;

import java.awt.*;

/**
 * This class controls height mapping in the game. A 2D array is sent by each subphase of all levels to this class. Example: {{0,1}} means that starting at x=0; the ground is at y=1.
 * There can be more than one "ground level" for each x value.
 * There are some exceptions. if "-1" is put into the second value for an entry into the array, it will assume that it is the normal height (y=620). If "-2" is entered, it will register as a void.
 */

class HeightMap {
    private int[][] heights={};
    HeightMap(){}
    void setHeights(int[][] in){
        heights=in;
    }
    boolean onGround(Rectangle r){
        if(heights.length==0){
            Frame.co.print("No array found for height map. NULL POINTER EXCEPTION",true);
            return false;
        }
        boolean singleHeight = heights.length == 1;
        for (int[] height : heights) {
            if ((height[0] > r.getX() && height[0] < r.getX() + r.getWidth())||(singleHeight)) {//Checks to see whether there is an object below the entity
                System.out.println("HEIGHT:"+height[1]);
                if(height[1]==-1){
                    if(r.getY()+r.getHeight()>620){
                        return true;
                    }
                }else if(height[1]==-2){
                    return false;
                }else if(r.getY() + r.getHeight() == height[1]){
                    return true;
                }
            }
        }
        return false;
    }
}
