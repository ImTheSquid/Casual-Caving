package CasualCaving;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static CasualCaving.CasualCaving.*;
import static CasualCaving.Frame.j;

/**
 * This section creates and manages the timer that controls all of the fading and physics calculations through the game
 */

//TODO Completely dismantle class to remove clutter

class TimerControl {
    private Player p;
    private boolean IO=false;//whether to fade in(false) or out(true) the logo
    private boolean logo=true;//done with the logo?
    private int lWait=0;//Deals with first wait of logo
    private Crowd cr;
    TimerControl(Crowd c,Player p){cr=c; this.p=p;}
    private ActionListener fadeListen=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(lWait<50){
                lWait++;
                return;
            }
            switch(phase) {
                case 0:
                    if (logo) {
                        if (!IO) {
                            logoA += 0.01;
                            if (logoA >= 1) {
                                logoA = 1;
                                fade.stop();
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                IO = true;
                                fade.start();
                            }
                            j.repaint();
                        } else {
                            logoA -= 0.01;
                            if (logoA <= 0) {
                                logoA = 0;
                                fade.stop();
                                logo = false;
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                fade.start();
                            }
                            j.repaint();
                        }
                    } else {
                        loadA -= 0.01;
                        if (loadA <= 0) {
                            loadA = 0;
                            fade.stop();
                            phase++;
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            //fade.start();
                        }
                        j.repaint();
                    }
                    break;
                case 2:
                    if((subPhase>=1&&subPhase<=5)&&cr.getCrowdrPosInt()<50&&!pause){
                        crowdrV=10;
                    }
                    if(fadeTime==100&&subPhase==5){
                        acf[0]-=0.01;
                        if(acf[0]<=0){
                            acf[0]=0;
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        j.repaint();
                    }
                    if(subPhase==6){
                        if(acf[0]<1&&!Level1.getReadyToFade6()) {
                            acf[0] += 0.01;
                            if (acf[0] >= 1) {
                                acf[0] = 1;
                                fade.stop();
                            }
                        }else{
                            acf[0] -= 0.01;
                            if (acf[0] <= 0) {
                                acf[0] = 0;
                                fade.stop();
                                subPhase++;
                            }
                        }
                        j.repaint();
                    }
                    if(subPhase==7){
                        if(levelEnd) {
                            acf[0] += 0.01;
                            if (acf[0] >= 1) {
                                acf[0] = 1;
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                levelEnd = false;
                            }
                        }else{
                            acf[0] -= 0.01;
                            if (acf[0] <= 0) {
                                acf[0] = 0;
                                phase++;
                                subPhase=0;
                                p.setPlayerX(100);
                            }
                        }
                        j.repaint();
                    }
                    break;
                case 3:
                    if(subPhase==6&&!qeChoice){
                        qe+=0.01;
                        if(qe>=1){
                            qe=1;
                            fade.stop();
                        }
                        j.repaint();
                    }else if(subPhase==6){
                        qe-=0.01;
                        if(qe<=0){
                            qe=0;
                            l2b6FadeDone=true;
                        }
                        if(l2b6FadeDone){
                            acf[1]-= 0.01;
                            if (acf[1] <= 0) {
                                acf[1] = 0;
                                p.setPlayerX(100);
                                acf[1]=1;
                                if(choice){
                                    subPhase=8;
                                }else{
                                    subPhase=7;
                                }
                            }
                        }
                        j.repaint();
                    }
                    if(subPhase==7){
                        acf[1] -= 0.01;
                        if (acf[1] <= 0) {
                            acf[1] = 0;
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            phase=-1;
                        }
                        j.repaint();
                    }
                    if(subPhase==8){
                        if(levelEnd) {
                            acf[1] += 0.01;
                            if (acf[1] >= 1) {
                                acf[1] = 1;
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                levelEnd = false;
                            }
                        }else{
                            acf[1] -= 0.01;
                            if (acf[1] <= 0) {
                                acf[1] = 0;
                                phase++;
                                subPhase=0;
                                p.setPlayerX(100);
                            }
                        }
                        j.repaint();
                    }
                    break;
            }
        }
    };
    private Timer fade=new Timer(20,fadeListen);

    Timer getFade() {
        return fade;
    }
}
