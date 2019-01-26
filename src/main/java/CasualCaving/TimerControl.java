package CasualCaving;

import javax.swing.*;
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
    TimerControl(Player p){this.p=p;}
    private ActionListener fadeListen= e -> {
        switch(phase) {
            case 3:
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
                break;
        }
    };
    private Timer fade=new Timer(20,fadeListen);

    Timer getFade() {
        return fade;
    }
}
