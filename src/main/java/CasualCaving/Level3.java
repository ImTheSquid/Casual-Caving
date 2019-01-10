package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;
import static CasualCaving.Frame.j;

class Level3 {
    private CavingLoader cavingLoader=new CavingLoader();
    private Timer fade;
    private Player p;
    private UniqueIDGenerator uniqueIDGenerator;
    private HeightMap heightMap;
    private final int[][] heights={{}};
    private ImageIcon[][] levels=cavingLoader.getLevels();
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

    void level3(Graphics g, Graphics2D g2d){
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,acf[2]);
        g2d.setComposite(z);
        g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
        switch(subPhase){
            case 0: l3b1(g);
                break;
            case 1: l3b2(g);
                break;
        }
        j.repaint();
    }

    private void l3b1(Graphics g){
        int[][] hm={{0,-1}};
        heightMap.setHeights(hm);
    }

    private void l3b2(Graphics g){
        int[][] hm={{0,-1}};
        heightMap.setHeights(hm);

    }
}
