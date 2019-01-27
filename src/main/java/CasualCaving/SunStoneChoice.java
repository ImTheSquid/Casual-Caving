package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static CasualCaving.CasualCaving.subPhase;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_Q;

public class SunStoneChoice implements Runnable{
    private Thread choice=new Thread(this);
    private boolean qeChoice=false;
    private volatile float qe=0;
    private CasualCaving cc;
    SunStoneChoice(CasualCaving cc){
        this.cc=cc;
    }

    void startFade(){
        if(choice.isAlive())return;
        choice=new Thread(this);
        choice.start();
    }

    @Override
    public void run() {
        boolean choice=false;
        while(!qeChoice||qe>0){
            Set<Integer> key=cc.getKey();
            if(!qeChoice){
                qe+=0.01;
                if(qe>=1){
                    qe=1;
                    qeChoice=false;
                }
            }else{
                qe-=0.01;
                if(qe<=0){
                    qe=0;
                    if(choice){
                        subPhase=8;
                    }else{
                        subPhase=7;
                    }
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Key checks
            if((key.contains(VK_Q)||key.contains(VK_E))&&!qeChoice){
                qeChoice=true;
                if(key.contains(VK_E)){
                    choice=true;
                }
            }
        }
    }

    void draw(Graphics g){
        CavingLoader cl=new CavingLoader();
        ImageIcon qeSunStone=cl.getQeSunStone();
        Graphics2D g2d=(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,qe));
        g.drawImage(qeSunStone.getImage(),0,0,null);
    }
}
