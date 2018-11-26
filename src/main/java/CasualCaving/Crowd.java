package CasualCaving;

import javax.swing.*;

import static CasualCaving.CasualCaving.crowdrV;

/**
 * This section controls the crowd for level 1
 */

class Crowd {
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[] crowdr=cl.getCrowdr();
    private ImageIcon cart=cl.getCart();
    private int crowdrPos=-1*crowdr[getCrowdrPos()].getIconWidth()-50-cart.getIconWidth();
    private boolean firstCrowdrFrame=false;
    private boolean crowdrFrameDir=true;
    private int crowdrFrame=1;
    private int crowdrFrameWait=0;
    Crowd(){}

    int getCrowdrPosInt(){
        return crowdrPos;
    }

    void crowdrPosReset(){
        crowdrPos=-1*crowdr[getCrowdrPos()].getIconWidth()-50-cart.getIconWidth();
    }

    void setCrowdrPos(int crowdrPos) {
        this.crowdrPos = crowdrPos;
    }

    void reset(){
        firstCrowdrFrame=false;
        crowdrFrameDir=true;
        crowdrFrame=1;
        crowdrFrameWait=0;
    }
    int getCrowdrPos(){//Handles crowd animation
        final int crowdrFrameWaitMax=10;
        if(crowdrV==0){
            return 0;
        }else{
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
            return crowdrFrame;
        }
    }
}
