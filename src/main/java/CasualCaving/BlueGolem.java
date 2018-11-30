package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

class BlueGolem {
    private HeightMap heightMap;
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
    private boolean frameDir=true;//Controls whether frames are played backwards or forwards
    private boolean firstFrame=true;//Controls whether to start at first frame after being still
    private int frameWait=0;//Controls how long to stay on each frame
    private final int frameWaitMax=3;
    BlueGolem(int spawnX,int spawnY,String ID,HeightMap heightMap){
        posX=spawnX;
        posY=spawnY;
        uniqueID=ID;
        this.heightMap=heightMap;
    }

    void golemAI(Graphics g){
        golemHitbox=new Rectangle(posX,posY,blueGolem[0][0].getIconWidth(),blueGolem[0][0].getIconHeight());
        golemPhysics();
        drawGolem(g);
    }
    private void golemPhysics(){
        if(heightMap.onGround(golemHitbox)){
            velY=0;
        }else{
            velY=9;
        }
        posX+=velX;
        posY+=velY;
        velY+=gravity;
    }
    private void drawGolem(Graphics g){
        int frameType=3;//3 if normal walking, 4 for attack animation
        if(!(pause||(acf[phase-2]<1))) {
            if(velX==0||!onObject){
                frame=0;
                firstFrame=false;
            }else{
                if(!firstFrame){
                    frame=1;
                    firstFrame=true;
                }
                if(attacking){
                    frameType=4;
                }
                if(frameDir){
                    if(frame<frameType){
                        if(frameWait<frameWaitMax){
                            frameWait++;
                        }else{
                            frame++;
                            frameWait=0;
                        }
                    }else{
                        frameDir=false;
                    }
                }else{
                    if(frame>1){
                        if(frameWait<frameWaitMax){
                            frameWait++;
                        }else{
                            frame--;
                            frameWait=0;
                        }
                    }else{
                        frameDir=true;
                    }
                }
            }
        }
        if(attacking) {
            golemDraw = 1;
        }else{
            golemDraw=0;
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
