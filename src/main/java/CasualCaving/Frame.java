package CasualCaving;

import javax.swing.*;
import java.awt.event.*;

public class Frame {
    private static CasualCaving c;
    final static int panelX=1280;
    final static int panelY=720;
    static JFrame j;
    public static void main(String[] args) {
        CavingLoader cl=new CavingLoader();
        ImageIcon titleIcon=cl.getTitleIcon();
        c=new CasualCaving();
        j=new JFrame("Casual Caving");
        j.setResizable(false);
        j.setSize(panelX,panelY);
        j.setIconImage(titleIcon.getImage());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setFocusable(true);
        j.setContentPane(c);
        j.setVisible(true);
        j.addKeyListener(new kl());
        j.addMouseListener(new ml());
    }

    public static class kl implements KeyListener{
        public void keyTyped(KeyEvent e){}
        public synchronized void keyPressed(KeyEvent e){
            c.kPress(e.getKeyCode());
        }
        public synchronized void keyReleased(KeyEvent e){ c.kRelease(e.getKeyCode()); }
    }

    public static class ml implements MouseListener{
        public void mouseClicked(MouseEvent e){
            c.mDecode(e.getPoint());
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }

}
