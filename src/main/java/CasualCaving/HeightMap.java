package CasualCaving;

import java.awt.*;

/**
 * This class controls height mapping in the game. A 2D array is sent by each subphase of all levels to this class. Example: {{0,1}} means that starting at x=0; the ground is at y=1.
 * There can be more than one "ground level" for each x value.
 * There are some exceptions. If "-1" is put into the second value for an entry into the array, it will assume that it is the normal height (y=620). If "-2" is entered, it will register as a void.
 */

class HeightMap {
    private int[][] heights={};
    HeightMap(){}
    void setHeights(int[][] in){
        heights=in;
    }
    HeightReturn onGround(Rectangle r){
        if(heights.length==0){
            Frame.co.printErr("No array found for height map. NULL POINTER EXCEPTION");
            return new HeightReturn(false);
        }
        boolean singleHeight = heights.length == 1;
        for (int i=0;i<heights.length;i++) {
            if ((r.getX() >= heights[i][0]) || (singleHeight)) {//Checks to see whether there is an object below the entity
                if ((heights.length > i + 1 && r.getX() + r.getWidth() <= heights[i + 1][0]) && !singleHeight) {
                    if (heights[i][1] == -1) {
                        if ((int) (r.getY()) + (int) (r.getHeight()) >= 620) {
                            return new HeightReturn(true, 620);
                        }
                    } else if (heights[i][1] == -2) {
                        return new HeightReturn(false);
                    } else if (r.getY() + r.getHeight() >= heights[i][1]) {
                        return new HeightReturn(true, heights[i][1]);
                    }
                    if (heights[i][1] == -1) {
                        if ((int) (r.getY()) + (int) (r.getHeight()) >= 620) {
                            return new HeightReturn(true, 620);
                        }
                    } else if (heights[i][1] == -2) {
                        return new HeightReturn(false);
                    } else if (r.getY() + r.getHeight() >= heights[i][1]) {
                        return new HeightReturn(true, heights[i][1]);
                    }
                } else if (singleHeight) {
                    if (heights[i][1] == -1) {
                        if ((int) (r.getY()) + (int) (r.getHeight()) >= 620) {
                            return new HeightReturn(true, 620);
                        }
                    } else if (heights[i][1] == -2) {
                        return new HeightReturn(false);
                    } else if (r.getY() + r.getHeight() >= heights[i][1]) {
                        return new HeightReturn(true, heights[i][1]);
                    }
                } else if (heights.length > i + 1 && r.getX() + r.getWidth() >= heights[i + 1][0]) {
                    if (heights[i + 1][1] == -1) {
                        if ((int) (r.getY()) + (int) (r.getHeight()) >= 620) {
                            return new HeightReturn(true, 620);
                        }
                    } else if (heights[i + 1][1] == -2) {
                        return new HeightReturn(false);
                    } else if (r.getY() + r.getHeight() >= heights[i + 1][1]) {
                        return new HeightReturn(true, heights[i + 1][1]);
                    }
                }
            }
        }
        return new HeightReturn(false);
    }
}
