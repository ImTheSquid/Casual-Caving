package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

public class Level3 {
    private Timer fade;
    private Player p;
    private UniqueIDGenerator uniqueIDGenerator;
    private final int[][] heightMap={{}};
    Level3(Timer f,Player p,UniqueIDGenerator uniqueIDGenerator){fade=f;this.p=p;this.uniqueIDGenerator=uniqueIDGenerator;}
    void reset(){

    }

    Object[] getEntities(){//Gets all entities in a certain subphase
        switch(subPhase){

        }
        return null;
    }

    public int[][] getHeightMap() {
        return heightMap;
    }

    void level3(Graphics g, Graphics2D g2d){
        switch(subPhase){
            case 0:
                break;
        }
    }
}
