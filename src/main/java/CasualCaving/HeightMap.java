package CasualCaving;

import java.awt.*;

public class HeightMap {
    private int[][] heights;
    HeightMap(){}
    void setHeights(int[][] in){
        heights=in;
    }
    int[][] getHeights() {
        return heights;
    }
    boolean onGround(Rectangle r){
        for (int[] height : heights) {
            if (height[0] > r.x && height[0] < r.x + r.width) {//Checks to see whether there is an object below the entity
                if (r.y + r.height == height[1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
