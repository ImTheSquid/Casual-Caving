package CasualCaving;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static CasualCaving.CasualCaving.*;

public class GameOver implements Runnable{
    private Thread alpha=new Thread(this);
    private boolean threadRun=true;
    private Font cTitle;
    GameOver(Font ctitle){this.cTitle=ctitle;}

    void startFade(){
        System.out.println("STARTED!");
        alpha.start();
    }

    boolean isRunnable(){return !alpha.isAlive();}

    public void run(){
        threadRun=true;
        gameOverFade=0;
        goIO=false;
        while (threadRun) {
            System.out.println("I'm running!");
            if (!goIO) {
                gameOverFade += 0.01;
                if (gameOverFade >= 1) {
                    gameOverFade = 1;
                    goIO = true;
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                gameOverFade -= 0.01;
                if (gameOverFade <= 0) {
                    gameOverFade = 0;
                    threadRun=false;
                    phase=0;
                }
            }
        }
        System.out.println("EXECUTION TERMINATED");
        //alpha.interrupt();
    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,gameOverFade);
        g2d.setComposite(z);
        g.setColor(Color.white);
        g.setFont(cTitle);
        g.drawString("Game Over", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Game Over")/2,300);
    }
}
