package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static CasualCaving.CasualCaving.*;

public class Logo implements Runnable{
    private Thread logoFade=new Thread(this);
    private CavingLoader cl=new CavingLoader();
    private ImageIcon load=cl.getLoad();
    private ImageIcon lunan=cl.getLunan();
    private boolean IO=false;//whether to fade in(false) or out(true) the logo
    private boolean logo=true;//done with the logo?
    Logo(){}
    void startFade(){logoFade.start();}
    public void run(){
        while(loadA>0||logo){
            if (logo) {
                if (!IO) {
                    logoA += 0.01;
                    if (logoA >= 1) {
                        logoA = 1;
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        IO = true;
                    }
                } else {
                    logoA -= 0.01;
                    if (logoA <= 0) {
                        logoA = 0;
                        logo = false;
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else {
                loadA -= 0.01;
                if (loadA <= 0) {
                    loadA = 0;
                    phase++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            try{Thread.sleep(20);}
            catch(InterruptedException e){e.printStackTrace();}
        }
    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,loadA));
        g.drawImage(load.getImage(),0,0,null);
        g.setColor(Color.white);
        g.setFont(constantia);
        g.drawString("Press 'S' to skip",0, Frame.panelY-(size*2)-5);
        AlphaComposite ac=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,logoA);
        g2d.setComposite(ac);
        g.drawImage(lunan.getImage(), Frame.panelX/2-(lunan.getIconWidth()/2)-15,30,null);
    }
}
