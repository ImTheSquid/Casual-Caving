package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static CasualCaving.CasualCaving.*;

class Player {
    private HeightMap heightMap;
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] harold=cl.getHarold();
    private BattleHandler battleHandler;
    private Pickaxe pickaxe;
    private float playerX= Frame.panelX-200-harold[0][0].getIconWidth();
    private float playerY=360;
    private float velocityX=0;//Physics stuff
    private float velocityY=0;
    private int frame=1;
    private int playerDraw=0;
    private boolean onGround=true;
    private boolean lanternLight=false;//Determines which Harold is shown
    private boolean hasRope=false;
    private boolean frameDir=true;
    private boolean firstFrame=false;
    private int frameWait=0;
    private boolean jump=false;
    private Rectangle playerHitbox=new Rectangle((int)playerX,(int)playerY,harold[0][0].getIconWidth(),harold[0][0].getIconHeight());
    Player(BattleHandler battleHandler,HeightMap heightMap){
        this.battleHandler=battleHandler;
        pickaxe=new Pickaxe(battleHandler,this);
        this.heightMap=heightMap;
    }
    void reset(){
        playerX= Frame.panelX-200-harold[0][0].getIconWidth();
        playerY=360;
        velocityX=0;
        velocityY=0;
        frame=1;
        hasRope=false;
        lanternLight=false;
        frameWait=0;
        frameDir=true;
        firstFrame=false;
    }

    float getPlayerY() {
        return playerY;
    }

    float getPlayerX() {
        return playerX;
    }

    void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityX() {
        return velocityX;
    }

    Rectangle getPlayerHitbox() {
        return playerHitbox;
    }

    void setHasRope(boolean hasRope) {
        this.hasRope = hasRope;
    }

    void playerHandle(Graphics g) {//Handles everything with the main player
        if((subPhase==6&&phase==3)||(subPhase==8&&phase==3)){
            return;
        }
        int playerXMin=0;
        //If statements for setting minimum x value
        if (subPhase == 0 && phase == 2) {
            playerXMin = 800;
        }else if(subPhase==3&&phase==3){
            playerXMin=155;
        }
        //If statements for setting maximum x value
        int playerXMax=1200;
        boolean canIncrease=true;
        if(subPhase==2&&phase==3){
            playerXMax=940;
            canIncrease=false;
        }
        //If statements for setting whether going back is an option
        boolean spDec=true;//Subphase decrease allowed
        if(subPhase==0) {
            spDec=false;
        }else if(subPhase==5&&phase==3){
            spDec=false;
        }else if((subPhase==1||subPhase>=5)&&phase==2){
            spDec=false;
        }else if(subPhase==7&phase==3){
            spDec=false;
        }
        //Determines whether to let the player move
        if(acf[phase-2]<1){
            if(phase==2&&subPhase==7){
                return;
            }
            drawPlayer(g);
            return;
        }
        if (key.contains(KeyEvent.VK_A) || key.contains(KeyEvent.VK_LEFT)) {
            velocityX = -10;
        }
        if (key.contains(KeyEvent.VK_D) || key.contains(KeyEvent.VK_RIGHT)) {
            velocityX = 10;
        }
        if (!(key.contains(KeyEvent.VK_A) || key.contains(KeyEvent.VK_LEFT) && !(key.contains(KeyEvent.VK_D)) || key.contains(KeyEvent.VK_RIGHT))) {
            if (velocityX > 0) {
                velocityX -= gravity;
            } else if (velocityX < 0) {
                velocityX += gravity;
            }
        }
        playerY += velocityY;
        playerX += velocityX;
        velocityY += gravity;
        //Boundary controls
        if (playerX < playerXMin) {
            playerX = playerXMin;
        }
        //Subphase controls
        if (playerX < 40 && spDec) {
            subPhase--;
            playerX = 1000;
        }
        if(canIncrease) {
            if (playerX + harold[0][0].getIconWidth() > 1200 && subPhase < selector[phase - 2].length - 1) {
                subPhase++;
                playerX = 100;
            }
        }else if(playerX>playerXMax){
            playerX=playerXMax;
        }
        //Boundary control
        if (playerX + harold[0][0].getIconWidth() > Frame.panelX) {
            playerX = Frame.panelX - harold[0][0].getIconWidth();
        }
        //Y position
        if (heightMap.onGround(playerHitbox)&&!jump) {
            //playerY = 360;
            velocityY = 0;
            jump=false;
        }
        if(((!(phase==2&&subPhase>=6))&&(!(phase==3&&subPhase==0)))&&lights) {
            drawPlayer(g);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void drawPlayer(Graphics g){//Draws Harold and animates him
        final int frameWaitMax=3;
        lanternLight = (phase == 3 && subPhase > 0)||phase>3;
        if(!(pause||(acf[phase-2]<1))) {
            if (velocityX == 0 || !onGround) {
                frame = 0;
                firstFrame = false;
            } else {
                if (!firstFrame) {
                    frame = 1;
                    firstFrame = true;
                }
                if (frameDir) {
                    if (frame == 3) {
                        frameDir = false;
                    } else {
                        if (frameWait < frameWaitMax) {
                            frameWait++;
                        } else {
                            frame++;
                            frameWait = 0;
                        }
                    }
                } else {
                    if (frame == 1) {
                        frameDir = true;
                    } else {
                        if (frameWait < frameWaitMax) {
                            frameWait++;
                        } else {
                            frame--;
                            frameWait = 0;
                        }
                    }
                }
            }
        }
        if(velocityX>0){
            if(hasChainsaw){
                playerDraw=2;
            }else if(hasWood){
                playerDraw=4;
            }else{
                playerDraw=0;
            }
            if(lanternLight){
                if(hasRope){
                    playerDraw=8;
                }else{
                    playerDraw=6;
                }
            }
        }else if(velocityX<0){
            if(hasChainsaw){
                playerDraw=3;
            }else if(hasWood){
                playerDraw=5;
            }else{
                playerDraw=1;
            }
            if(lanternLight){
                if(hasRope){
                    playerDraw=9;
                }else{
                    playerDraw=7;
                }
            }
        }
        g.drawImage(harold[playerDraw][frame].getImage(),(int)playerX,(int)playerY,null);
        playerHitbox=new Rectangle((int)playerX,(int)playerY,harold[playerDraw][frame].getIconWidth(),harold[playerDraw][frame].getIconHeight());
    }

    void jump(){
        playerY-=2;
        if(heightMap.onGround(playerHitbox)) {
            velocityY = -12;
            jump=true;
        }
    }

    void jumpEnd(){
        jump=false;
        if(velocityY<-6.0f){
            velocityY=-6.0f;
        }
    }

    void attack(){
        pickaxe.attack(velocityX);
    }
}
