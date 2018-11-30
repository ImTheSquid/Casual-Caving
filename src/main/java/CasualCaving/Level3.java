package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static CasualCaving.CasualCaving.*;
import static CasualCaving.Frame.j;

public class Level3 {
    private Timer fade;
    private Player p;
    private UniqueIDGenerator uniqueIDGenerator;
    private HeightMap heightMap;
    private final int[][] heights={{}};
    private ArrayList<BlueGolem> testSet=new ArrayList<>();
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
        j.repaint();
    }

    private void l3b1(Graphics g){
        int[][] hm={{0,-1}};
        heightMap.setHeights(hm);
        if(testSet.size()==0){
            testSet.add(new BlueGolem(0,0,uniqueIDGenerator.generateHash(),heightMap));
        }
        testSet.get(0).golemAI(g);
    }
}
