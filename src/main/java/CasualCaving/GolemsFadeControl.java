package CasualCaving;

import javax.swing.*;
import java.awt.*;

class GolemsFadeControl implements Runnable{
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] sunGolems=cl.getSunGolems();
    private Thread fadeTime=new Thread(this);
    private volatile float isolsiAlpha=0;
    private volatile float hematusAlpha=0;
    private volatile float igneoxAlpha=0;
    private volatile int igneoxSeq=0;
    private int golemSeq=0;
    private boolean threadExit=true;
    private Player p;
    GolemsFadeControl(){
    }

    void start(Player p){
        this.p=p;
        p.setTurnAround();
        p.toggleMovement();
        fadeTime.start();
    }

    boolean isRunnable(){
        return !fadeTime.isAlive()&&golemSeq<3;
    }

    @Override
    public void run() {
        while(threadExit) {
            sleep(15);
            switch (golemSeq) {
                case 0:
                    isolsiAlpha += 0.01;
                    if (isolsiAlpha >= 1) {
                        isolsiAlpha = 1;
                        sleep(500);
                        golemSeq++;
                    }
                    break;
                case 1:
                    hematusAlpha += 0.01;
                    if (hematusAlpha >= 1) {
                        hematusAlpha = 1;
                        sleep(2000);
                        golemSeq++;
                    }
                    break;
                case 2:
                    igneoxAlpha+=0.01;
                    if(igneoxAlpha>0.75)igneoxSeq=3;
                    else if(igneoxAlpha>0.5)igneoxSeq=2;
                    else if(igneoxAlpha>0.25)igneoxSeq=1;
                    else igneoxSeq=0;
                    if(igneoxAlpha>=1){
                        igneoxAlpha=1;
                        golemSeq++;
                    }
                    break;
                case 3:
                    threadExit=false;
                    p.setTurnAround();
                    p.toggleMovement();
                    break;
            }
        }
    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,isolsiAlpha));
        g.drawImage(sunGolems[0][0].getImage(),0,0,null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,hematusAlpha));
        g.drawImage(sunGolems[1][0].getImage(),0,0,null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,igneoxAlpha));
        g.drawImage(sunGolems[2][igneoxSeq].getImage(),0,0,null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void reset(){
        isolsiAlpha=0;
        hematusAlpha=0;
        igneoxAlpha=0;
        igneoxSeq=0;
        golemSeq=0;
        threadExit=true;
    }
}
