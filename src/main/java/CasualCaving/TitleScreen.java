package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;

public class TitleScreen implements Runnable{
    private Thread titleFade=new Thread(this);
    private CavingLoader cl=new CavingLoader();
    ImageIcon title=cl.getTitle();
    private Font constantia;
    private Font cTitle;
    private Font c2;
    private Rectangle startButton;
    private Rectangle quitButton;
    TitleScreen(Font constatnia,Font cTitle,Font c2){
        this.c2=c2;
        this.constantia=constatnia;
        this.cTitle=cTitle;
    }

    Rectangle getStartButton(){return startButton;}

    Rectangle getQuitButton(){return quitButton;}

    @Override
    public void run() {

    }

    void draw(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        AlphaComposite tC=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,titleA);
        g2d.setComposite(tC);
        g.drawImage(title.getImage(),0,0,null);
        if(!gameStart) {
            g.setColor(new Color(207, 135, 31));
        }else{
            g.setColor(new Color(170,92,24));
        }
        g.fillRect((Frame.panelX/2)-100,(Frame.panelY/2)-40,200,80);
        startButton=new Rectangle((Frame.panelX/2)-100,(Frame.panelY/2),200,80);
        g.setFont(constantia);
        g.setColor(Color.white);
        g.drawString("Made by Jack Hogan and Stuart Lunn",5, Frame.panelY-(size*2)-5);
        String version="Casual Caving 0.0.10  ";
        g.drawString(version, Frame.panelX-g.getFontMetrics(constantia).stringWidth(version)-10, Frame.panelY-(size*2)-5);
        g.setFont(cTitle);
        g.drawString("Start",(Frame.panelX-g.getFontMetrics(cTitle).stringWidth("Start"))/2,(Frame.panelY/2)+g.getFontMetrics(cTitle).getHeight()/4);
        g.setColor(new Color(196,0,0));
        g.fillRect(Frame.panelX/2-50, Frame.panelY-110,100,50);
        quitButton=new Rectangle(Frame.panelX/2-50, Frame.panelY-80,100,50);
        g.setColor(Color.white);
        g.setFont(c2);
        g.drawString("Quit",(Frame.panelX-g.getFontMetrics(c2).stringWidth("Quit"))/2, Frame.panelY-g.getFontMetrics(c2).getHeight()-25);
    }
}
