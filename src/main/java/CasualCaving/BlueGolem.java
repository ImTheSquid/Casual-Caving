package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

class BlueGolem extends Entity{
    private HeightMap heightMap;
    private int posX;
    private int posY;
    private String uniqueID;
    private float velX;
    private float velY=0;
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] blueGolem=cl.getBlueGolem();
    private Rectangle golemHitbox=new Rectangle(posX,posY,blueGolem[0][0].getIconWidth(),blueGolem[0][0].getIconHeight());
    private int golemDraw=0;
    private int frame=1;
    private boolean attacking=false;
    private boolean frameDir=true;//Controls whether frames are played backwards or forwards
    private boolean firstFrame=true;//Controls whether to start at first frame after being still
    private int frameWait=0;//Controls how long to stay on each frame
    private final int frameWaitMax=3;
    BlueGolem(int spawnX,int spawnY,String ID,HeightMap heightMap){
        super(10,10);
        posX=spawnX;
        posY=spawnY;
        uniqueID=ID;
        this.heightMap=heightMap;
        velX=10;
        super.setHitbox(golemHitbox);
    }

    void golemAI(Graphics g){
        if(posX<0){
            posX=0;
            velX*=-1;
        }else if(posX+golemHitbox.getWidth()>Frame.panelX){
            posX=(int)(Frame.panelX-golemHitbox.getWidth());
            velX*=-1;
        }
        golemHitbox=new Rectangle(posX,posY,blueGolem[0][0].getIconWidth(),blueGolem[0][0].getIconHeight());
        golemPhysics();
        drawGolem(g);
    }
    private void golemPhysics(){
        if(heightMap.onGround(golemHitbox).isOnGround()){
            velY=0;
        }else{
            velY=5;
        }
        if(!pause) {
            posX += velX;
            posY += velY;
            velY += gravity;
        }
    }
    private void drawGolem(Graphics g){
        int frameType=3;//3 if normal walking, 4 for attack animation
        if(!(pause||(acf[phase-2]<1))) {
            System.out.println("EXEC");
            if(velX==0||!heightMap.onGround(golemHitbox).isOnGround()){
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
        if(velX>0) {
            golemDraw = 0;
        }else{
            golemDraw=1;
        }
        System.out.println(frame);
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
