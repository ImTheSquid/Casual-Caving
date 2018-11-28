package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

public class Level3 {
    private Timer fade;
    private Player p;
    private UniqueIDGenerator uniqueIDGenerator;
    private HeightMap heightMap;
    private final int[][] heights={{}};
    Level3(Timer f,Player p,UniqueIDGenerator uniqueIDGenerator,HeightMap heightMap){
        fade=f;
        this.p=p;
        this.uniqueIDGenerator=uniqueIDGenerator;
        this.heightMap=heightMap;
    }
    void reset(){

    }

    Object[] getEntities(){//Gets all entities in a certain subphase
        switch(subPhase){

        }
        return null;
    }

    public int[][] getHeightMap() {
        return heights;
    }

    void level3(Graphics g, Graphics2D g2d){
        switch(subPhase){
            case 0: l3b1(g);
                break;
            case 1:
                break;
        }
    }

    private void l3b1(Graphics g){
        int[][] hm={{0,-1}};
        heightMap.setHeights(hm);
    }
}
