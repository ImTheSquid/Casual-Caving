package CasualCaving;

import java.awt.*;

import static CasualCaving.CasualCaving.*;

public class LevelEnd implements Runnable{
    private int nextLevel;
    private volatile float aC=0;
    private volatile boolean fadeStart=false;
    private volatile boolean fadeDir=true;
    LevelEnd(int nextLevel){
        this.nextLevel=nextLevel;
    }

    void startFade(){
        if(fadeStart)return;
        aC=0;
        fadeDir=true;
        fadeStart=true;
    }

    @Override
    public void run() {
        while(fadeStart){
            if(fadeDir){
                aC+=0.01;
                if(aC>=1){
                    aC=1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fadeDir=false;
                }
            }else{
                aC-=0.01;
                if(aC<=0){
                    aC=0;
                    phase++;
                    subPhase=0;
                    fadeStart=false;
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,aC));
        String s="Part "+nextLevel;
        g.setColor(Color.white);
        g.setFont(cTitle);
        g.drawString(s,Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth(s)/2,300);
    }
}
