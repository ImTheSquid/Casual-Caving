package CasualCaving;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static CasualCaving.CasualCaving.*;
import static java.awt.event.KeyEvent.VK_ENTER;

public class GameOver implements Runnable{
    private Thread alpha=new Thread(this);
    private boolean threadRun=true;
    private Font cTitle;
    private float gameOverFade=0;
    private boolean goIO=false;//Game over in-out
    GameOver(Font ctitle){this.cTitle=ctitle;}

    void startFade(){
        alpha.start();
    }

    boolean isRunnable(){return !alpha.isAlive();}

    boolean isComplete(){return !threadRun;}

    public void run(){
        threadRun=true;
        gameOverFade=0;
        goIO=false;
        while (threadRun) {
            if(phase==-1) {
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
                        threadRun = false;
                        titleReady = false;
                        gameStart = false;
                        subPhase = 0;
                        titleA = 0;
                    }
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void draw(Graphics g){
        if(key.contains(VK_ENTER)){
            goIO=true;
            gameOverFade=0;
        }
        Graphics2D g2d=(Graphics2D)g;
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,gameOverFade);
        g2d.setComposite(z);
        g.setColor(Color.white);
        g.setFont(cTitle);
        g.drawString("Game Over", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Game Over")/2,300);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
    }
}
