package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static CasualCaving.CasualCaving.*;
import static CasualCaving.Frame.j;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_Q;

class Level2 {
    private StringDraw sd=new StringDraw();
    private HeightMap heightMap;
    private Player p;
    private CavingLoader cl=new CavingLoader();
    private ImageIcon[][] levels=cl.getLevels();
    private ImageIcon[] foregrounds=cl.getForegrounds();
    private ImageIcon minersl2b1=cl.getMinersl2b1();
    private ImageIcon ls=cl.getLs();
    private ImageIcon qeSunStone=cl.getQeSunStone();
    private ImageIcon sparks=cl.getSparks();
    private ImageIcon[] l2b3Variation=cl.getL2b3Variation();
    private int l2b8wait=0;
    private int sparkWait=0;//Sparks l2b2
    private int rope=0;
    private Timer fade;
    private LevelEnd l3=new LevelEnd(3);
    private CasualCaving cc;
    Level2(Timer f,Player p,HeightMap heightMap,CasualCaving cc){
        fade=f;
        this.p=p;
        this.heightMap=heightMap;
        this.cc=cc;
    }
    void reset(){
        l2b8wait=0;
        sparkWait=0;
        rope=0;
    }
    void level2(Graphics g, Graphics2D g2d){
        if(subPhase!=2){
            int[][] x={{0,-1}};
            heightMap.setHeights(x);
        }else{
            int[][] x={{0,-1},{1084,-2}};
        }
        if(subPhase!=1){
            lights=true;
        }
        Set<Integer> nD=new HashSet<>();//no draw
        nD.add(2);
        nD.add(1);
        nD.add(7);
        nD.add(8);
        if(!nD.contains(subPhase)){
            AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,acf[1]);
            g2d.setComposite(z);
            g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
        }else if(subPhase==7&&l2b8wait==100){
            AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,acf[1]);
            g2d.setComposite(z);
            g.drawImage(levels[phase-2][5].getImage(),0,0,null);
        }
        switch (subPhase){
            case 0: l2b1(g);
                break;
            case 1: l2b2(g);
                break;
            case 2: l2b3(g);
                break;
            case 5: l2b6(g);
                break;
            case 6: l2b7(g,g2d);
                break;
            case 7: l2b8(g);
                break;
            case 8: l2end(g);
                break;
        }
        j.repaint();
    }

    private void l2b1(Graphics g){
        p.drawPlayer(g);
        g.drawImage(foregrounds[0].getImage(),0,0,null);
    }

    private void l2b2(Graphics g){
        if(sparkWait<35){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(sparkWait<5){
            sparkWait++;
            lights=false;
        }
        if(sparkWait<10){
            g.drawImage(sparks.getImage(),0,0,null);
            sparkWait++;
            lights=false;
        }else if(sparkWait<25){
            g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
            g.drawImage(minersl2b1.getImage(), 0, 0, null);
            sparkWait++;
            lights=true;
        }else if(sparkWait<35){
            sparkWait++;
            lights=false;
        }else{
            g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
            g.drawImage(minersl2b1.getImage(), 0, 0, null);
            lights=true;
        }
    }

    private void l2b3(Graphics g){
        Rectangle cart=new Rectangle(420,202,301,256);
        switch(rope){
            case 0:
                g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
                break;
            case 1:
                g.drawImage(levels[phase-2][subPhase].getImage(),0,0,null);
                if(p.getPlayerX()>900){
                    sd.drawString(g,"Press 'E' to place rope",1050,400,1280,constantia,Color.white);
                }
                if(key.contains(VK_E)&&p.getPlayerX()>900){
                    rope++;
                    p.setHasRope(false);
                    key.remove(VK_E);
                }
                break;
            case 2:
                g.drawImage(l2b3Variation[0].getImage(),0,0,null);
                if(p.getPlayerX()>900){
                    sd.drawString(g,"Press 'E' to go down",1050,400,1280,constantia,Color.white);
                }
                if(key.contains(VK_E)&&p.getPlayerX()>900){
                    phase=-1;
                    key.remove(VK_E);
                }
                break;
            case 3:
                g.drawImage(l2b3Variation[0].getImage(),0,0,null);
                if(p.getPlayerX()>900){
                    sd.drawString(g,"Press 'E' to place anchor",1050,400,1280,constantia,Color.white);
                }
                if(key.contains(VK_E)&&p.getPlayerX()>900){
                    rope++;
                    key.remove(VK_E);
                }
                break;
            case 4:
                g.drawImage(l2b3Variation[1].getImage(),0,0,null);
                if(p.getPlayerX()>900){
                    sd.drawString(g,"Press 'E' to go down",1050,400,1280,constantia,Color.white);
                }
                if(key.contains(VK_E)&&p.getPlayerX()>900){
                    subPhase++;
                    p.setPlayerX(160);
                    key.remove(VK_E);
                }
                break;
        }
        if(p.getPlayerHitbox().intersects(cart)){
            switch (rope){
                case 0:
                    sd.drawString(g,"Press 'E' to get rope",420,180,620,constantia,Color.white);
                    if(key.contains(VK_E)){
                        rope++;
                        p.setHasRope(true);
                        key.remove(VK_E);
                    }
                    break;
                case 2:
                    sd.drawString(g,"Press 'E' to get anchor",420,180,620,constantia,Color.white);
                    if(key.contains(VK_E)){
                        rope++;
                        p.setHasRope(false);
                        key.remove(VK_E);
                    }
                    break;
            }
        }
    }

    private void l2b6(Graphics g){
        Rectangle shrine=new Rectangle(805,238,213,213);
        if(p.getPlayerHitbox().intersects(shrine)) {
            sd.drawString(g, "'E' to investigate", 815, 270, 1000, constantia, Color.white);
            if(key.contains(VK_E)){
                key.remove(VK_E);
                subPhase++;
            }
        }
    }

    private void l2b7(Graphics g,Graphics2D g2d){
        if(!firstRun[1][6]){
            firstRun[1][6]=true;
            //fade.start();
            qe=0.01f;
        }
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,qe);
        g2d.setComposite(z);
        g.drawImage(qeSunStone.getImage(),0,0,null);
        if((key.contains(VK_E)||key.contains(VK_Q))&&!qeChoice){
            fade.start();
            qeChoice=true;
            if(key.contains(VK_E)){
                choice=true;
            }
        }
    }

    private void l2b8(Graphics g){
        if(!firstRun[1][7]){
            firstRun[1][7]=true;
            p.setPlayerX(722);
            p.drawPlayer(g);
            fade.stop();
            acf[1]=1;
        }
        g.drawImage(levels[phase-2][5].getImage(),0,0,null);
        g.drawImage(ls.getImage(),0,0,null);
        sd.drawString(g,"Good thing you called. I heard that stone is cursed!",389,130,520,constantia,Color.white);
        if(l2b8wait<100){
            l2b8wait++;
        }else{
            fade.start();
        }
    }

    private void l2end(Graphics g){
        if(cc.getBrightness()!=1)cc.setBrightness(1);
        l3.draw(g);
        l3.startFade();
        if(!firstRun[0][8]){
            firstRun[0][8]=true;
            acf[1]=0;
            levelEnd=true;
        }
        /*fade.start();
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,acf[1]);
        g2d.setComposite(z);
        g.setFont(cTitle);
        g.setColor(Color.white);
        g.drawString("Part 3", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Part 3")/2,300);*/
    }
}
