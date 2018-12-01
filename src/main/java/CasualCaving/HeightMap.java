package CasualCaving;

import java.awt.*;

class HeightMap {
    private int[][] heights={};
    private boolean singleHeight=false;//Whether there is only one height throughout the level
    HeightMap(){}
    void setHeights(int[][] in){
        heights=in;
    }
    int[][] getHeights() {
        return heights;
    }
    boolean onGround(Rectangle r){
        if(heights.length==0){
            System.err.println("Casual Caving.ERROR: No array found for height map. NULL POINTER EXCEPTION");
            return false;
        }
        singleHeight = heights.length == 1;
        for (int[] height : heights) {
            System.out.println(r.getY());
            if ((height[0] > r.getX() && height[0] < r.getX() + r.getWidth())||(singleHeight)) {//Checks to see whether there is an object below the entity
                System.out.println("HEIGHT:"+height[1]);
                if(height[1]==-1){
                    System.out.println("EXEC");
                    if(r.getY()+r.getHeight()>620){
                        System.out.println("TRUE");
                        return true;
                    }
                }else if(r.getY() + r.getHeight() == height[1]){
                    return true;
                }
            }
        }
        return false;
    }
}
