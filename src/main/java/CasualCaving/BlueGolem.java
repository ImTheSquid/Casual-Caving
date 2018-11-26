package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

class BlueGolem {
    private int posX;
    private int posY;
    private String uniqueID;
    private float velX=0;
    private float velY=0;
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] blueGolem=cl.getBlueGolem();
    private boolean onObject=false;
    private Rectangle golemHitbox;
    private int golemDraw=0;
    private int frame=1;
    private boolean attacking=false;
    private int health=100;
    BlueGolem(int spawnX,int spawnY,String ID){
        posX=spawnX;
        posY=spawnY;
        uniqueID=ID;
    }

    void golemAI(Graphics g){
        drawGolem(g);
    }
    void drawGolem(Graphics g){
        int frameWaitMax=3;
        if(!(pause||(acf[phase-2]<1))) {
            if(velX==0||!onObject){
                frame=0;
            }else{
                if(attacking){
                    frameWaitMax=4;
                }else{
                    frameWaitMax=3;
                }
            }
        }
        g.drawImage(blueGolem[golemDraw][frame].getImage(),posX,posY,null);
        golemHitbox=new Rectangle(posX,posY,blueGolem[golemDraw][frame].getIconWidth(),blueGolem[golemDraw][frame].getIconHeight());
    }
    //Links in to battle handler
    Rectangle getGolemHitbox() {
        return golemHitbox;
    }

    String getUniqueID() {
        return uniqueID;
    }
}
