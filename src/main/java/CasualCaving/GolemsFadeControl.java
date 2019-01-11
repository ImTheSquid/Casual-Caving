package CasualCaving;

import javax.swing.*;
import java.awt.*;

public class GolemsFadeControl implements Runnable{
    CavingLoader cl=new CavingLoader();
    ImageIcon[][] sunGolems=cl.getSunGolems();
    Thread fadeTime=new Thread(this);
    volatile float isolsiAlpha=0;
    volatile float hematusAlpha=0;
    volatile float igneoxAlpha=0;
    volatile int igneoxSeq=0;
    int golemSeq=0;
    boolean fadeDir=true;
    GolemsFadeControl(){
        fadeTime.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (golemSeq) {
                case 0:
                    if (fadeDir) {
                        isolsiAlpha += 0.01;
                        if (isolsiAlpha >= 1) {
                            isolsiAlpha = 1;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            fadeDir = false;
                        }
                    }
                    break;
                case 1:
                    if (fadeDir) {
                        hematusAlpha += 0.01;
                        if (hematusAlpha >= 1) {
                            hematusAlpha = 1;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            fadeDir = false;
                        }
                    }
                    break;
                case 2:
                    if(fadeDir){
                        igneoxAlpha+=0.01;
                        if(igneoxAlpha>0.75)igneoxSeq=3;
                        else if(igneoxAlpha>0.5)igneoxSeq=2;
                        else if(igneoxAlpha>0.25)igneoxSeq=1;
                        else igneoxSeq=0;
                        if(igneoxAlpha>=1){
                            igneoxAlpha=1;
                            fadeDir=false;
                        }
                    }
                    break;
                case 3:
                    fadeTime.interrupt();
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
        g2d.dispose();
    }
}
