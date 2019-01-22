package CasualCaving;

import java.awt.*;

import static CasualCaving.CasualCaving.cTitle;

public class LevelEnd implements Runnable{
    private int nextLevel;
    private float aC=0;
    private boolean fadeStart=true;
    Thread level=new Thread(this);
    LevelEnd(int nextLevel){
        this.nextLevel=nextLevel;
    }

    void startFade(){
        if(!level.isAlive())level.start();
        else{
            fadeStart=true;
        }
    }

    @Override
    public void run() {
        while(true){

        }
    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,aC));
        String s="Part "+nextLevel;
        g.drawString(s,Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth(s)/2,300);
    }
}
