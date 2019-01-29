package CasualCaving;

import javax.swing.*;

import static CasualCaving.CasualCaving.*;

/**
 * This section controls the crowd for level 1
 */

class Crowd implements Runnable{
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[] crowdr=cl.getCrowdr();
    private ImageIcon cart=cl.getCart();
    private int crowdrPos=-1*crowdr[getCrowdrPos()].getIconWidth()-50-cart.getIconWidth();
    private boolean firstCrowdrFrame=false;
    private boolean crowdrFrameDir=true;
    private volatile int crowdrFrame=1;
    private int crowdrFrameWait=0;
    private Thread crowdPhys=new Thread(this);
    private boolean bridgeBuilt=false;
    Crowd(){}

    int getCrowdrPosInt(){
        return crowdrPos;
    }

    void crowdrPosReset(){
        crowdrPos=-1*crowdr[getCrowdrPos()].getIconWidth()-50-cart.getIconWidth();
    }

    void startCalc(){
        if(!crowdPhys.isAlive())crowdPhys.start();
    }

    void reset(){
        crowdPhys=new Thread(this);
        bridgeBuilt=false;
        firstCrowdrFrame=false;
        crowdrFrameDir=true;
        crowdrFrame=1;
        crowdrFrameWait=0;
    }

    public void run(){
        while((phase==2&&subPhase<=5)||crowdrV>0) {
            if((subPhase>=1&&subPhase<=5)&&crowdrPos<50&&!pause){
                if(subPhase==3){
                    if(bridgeBuilt)crowdrV=10;
                }else {
                    crowdrV = 10;
                }
            }
            crowdrPos += crowdrV;
            crowdrV -= gravity;
            frameCalc();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void continueBridge(){
        bridgeBuilt=true;
    }

    private void frameCalc(){
        final int crowdrFrameWaitMax=10;
        if(!firstCrowdrFrame) {
            crowdrFrame = 1;
            firstCrowdrFrame=true;
        }
        if(crowdrFrameDir){
            if(crowdrFrame==3){
                crowdrFrameDir=false;
            }else {
                if(crowdrFrameWait<crowdrFrameWaitMax){
                    crowdrFrameWait++;
                }else {
                    crowdrFrame++;
                    crowdrFrameWait=0;
                }
            }
        }else{
            if(crowdrFrame==1){
                crowdrFrameDir=true;
            }else{
                if(crowdrFrameWait<crowdrFrameWaitMax){
                    crowdrFrameWait++;
                }else {
                    crowdrFrame--;
                    crowdrFrameWait=0;
                }
            }
        }
    }

    int getCrowdrPos(){//Handles crowd animation
        if(crowdrV==0){
            return 0;
        }else{
            return crowdrFrame;
        }
    }
}
