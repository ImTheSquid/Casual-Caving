package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Frame {
    private static CasualCaving c;
    final static int panelX=1280;
    final static int panelY=720;
    static JFrame j;
    static JFrame console;
    static ConsoleOut co=new ConsoleOut();
    static JTextArea jTextArea;
    static PrintStream ps;
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
        console=new JFrame("Casual Caving Console");
        console.setResizable(true);
        console.setMinimumSize(new Dimension(600,600));
        console.setVisible(false);
        jTextArea=new JTextArea(24,100);
        JScrollPane jsp=new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
        ps=new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                jTextArea.append(String.valueOf((char)b));
            }
        });
        console.add(jsp);
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
