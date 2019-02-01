package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static CasualCaving.CasualCaving.*;

class Player implements Runnable{
    private HeightMap heightMap;
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] harold=cl.getHarold();
    private ImageIcon[] haroldTurn=cl.getHaroldTurn();
    private BattleHandler battleHandler;
    private Pickaxe pickaxe;
    private float playerX= Frame.panelX-200-harold[0][0].getIconWidth();
    private float playerY=360;
    private volatile float velocityX=0;//Physics stuff
    private volatile float velocityY=0;
    private volatile int frame=1;
    private volatile int playerDraw=0;
    private boolean lanternLight=false;//Determines which Harold is shown
    private boolean hasRope=false;
    private boolean frameDir=true;
    private boolean firstFrame=false;
    private int frameWait=0;
    private boolean jump=false;
    private boolean jumpEnd=false;
    private Rectangle playerHitbox=new Rectangle((int)playerX,(int)playerY,harold[0][0].getIconWidth(),harold[0][0].getIconHeight());
    private boolean turnAround=false;
    private int turnAroundPhase=0;
    private volatile boolean movement=true;
    private int health=2;
    private CasualCaving cc;
    private Thread physics=new Thread(this);
    Player(BattleHandler battleHandler,HeightMap heightMap,CasualCaving cc){
        this.battleHandler=battleHandler;
        pickaxe=new Pickaxe(battleHandler,this);
        this.heightMap=heightMap;
        this.cc=cc;
    }

    void startPhysics(){if(!physics.isAlive())physics.start();}
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

    Rectangle getPlayerHitbox() {
        return playerHitbox;
    }

    void setHasRope(boolean hasRope) {
        this.hasRope = hasRope;
    }

    public void run(){
        while(true) {
            if(movement&&(phase>=2&&cc.getBrightness()==1)) {
                playerY += velocityY;
                playerX += velocityX;
                velocityY += gravity;
            }else{
                velocityX=0;
                velocityY=0;
            }
            //Y position
            if (heightMap.onGround(playerHitbox).isOnGround()&&!jump) {
                try{
                    playerY=(float)(heightMap.onGround(playerHitbox).getGroundLevel()-playerHitbox.getHeight());
                }catch (NullPointerException ignored){}
                velocityY = 0;
                jump=false;
                jumpEnd=false;
            }
            if (velocityX > 0) {
                velocityX -= gravity;
            } else if (velocityX < 0) {
                velocityX += gravity;
            }
            frameCalc();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        //Key control
        if(movement) {
            if (key.contains(KeyEvent.VK_A) || key.contains(KeyEvent.VK_LEFT)) {
                velocityX = -10;
            }
            if (key.contains(KeyEvent.VK_D) || key.contains(KeyEvent.VK_RIGHT)) {
                velocityX = 10;
            }
        }
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
        if(((!(phase==2&&subPhase>=6))&&(!(phase==3&&subPhase==0)))&&lights) {
            drawPlayer(g);
        }
    }

    private void frameCalc(){
        final int frameWaitMax=3;
        lanternLight = (phase == 3 && subPhase > 0)||phase>3;
        if(!(pause||(phase>=3&&acf[phase-2]<1))) {//Cant find ACF
            if (velocityX == 0 || jump||jumpEnd) {
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
    }

    void drawPlayer(Graphics g){//Draws Harold and animates him
        if(turnAround){
            g.drawImage(haroldTurn[turnAroundPhase].getImage(), (int) playerX, (int) playerY, null);
            if(turnAroundPhase<1) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                turnAroundPhase = 1;
            }
        }else {
            g.drawImage(harold[playerDraw][frame].getImage(), (int) playerX, (int) playerY, null);
            turnAroundPhase=0;
        }
        playerHitbox=new Rectangle((int)playerX,(int)playerY,harold[playerDraw][frame].getIconWidth(),harold[playerDraw][frame].getIconHeight());
    }

    void drawHealth(Graphics g){
        if(phase<2)return;
        for(int i=0;i<health;i++) {
            g.drawImage(cl.getHeart().getImage(), 5+(45*i), Frame.panelY - 85, 40, 40, null);
        }
    }

    void setTurnAround(){
        turnAround=!turnAround;
    }

    void toggleMovement(){
        movement = !movement;
    }

    void jump(){
        if(pause||!movement){return;}
        jumpEnd=false;
        playerY-=2;
        if(heightMap.onGround(playerHitbox).isOnGround()) {
            velocityY = -12;
            jump=true;
        }
    }

    void jumpEnd(){
        if(pause){return;}
        jump=false;
        jumpEnd=true;
        if(velocityY<-6.0f){
            velocityY=-6.0f;
        }
    }

    void attack(){
        pickaxe.attack(velocityX);
    }

}
