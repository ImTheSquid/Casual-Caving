package CasualCaving;

import javax.swing.*;
import java.awt.*;

import static CasualCaving.CasualCaving.*;
import static java.awt.event.KeyEvent.VK_E;

class Level1 {
    private CasualCaving cc;
    private StringDraw sd=new StringDraw();
    private HeightMap heightMap;
    private Timer fade;
    private CavingLoader cl=new CavingLoader();
    private Player p;
    private Crowd crowd;
    private ImageIcon cart=cl.getCart();
    private ImageIcon boss=cl.getBoss();
    private ImageIcon bossCrowd=cl.getCrowd();
    private ImageIcon[] crowdr=cl.getCrowdr();
    private ImageIcon bridge=cl.getBridge();
    private ImageIcon[] tents=cl.getTents();
    private ImageIcon[][] levels=cl.getLevels();
    private ImageIcon[][] harold=cl.getHarold();
    private int crowdrMax=1;
    private boolean logVisible=true;
    private ImageIcon log=cl.getLog();
    private boolean bridgeBuilt=false;
    private Rectangle logHitbox=new Rectangle(Frame.panelX-320,520,log.getIconWidth(),log.getIconHeight());
    private LevelEnd l2=new LevelEnd(2);
    Level1(Timer f,Player p,Crowd crowd,HeightMap heightMap,CasualCaving casualCaving){fade=f;this.p=p;this.crowd=crowd;this.heightMap=heightMap;cc=casualCaving;}
    void reset(){
        crowdrMax=1;
        logVisible=true;
        bridgeBuilt=false;
    }
    void level1(Graphics g, Graphics2D g2d){//Handles drawing level 1
        p.startPhysics();
        if(subPhase==2||subPhase==6){
            if(subPhase==2){
                if(logVisible){
                    int[][] x={{0,-1},{(int)logHitbox.getX(),520},{(int)logHitbox.getX()+(int)logHitbox.getWidth(),-1}};
                    heightMap.setHeights(x);
                }else{
                    int[][] x={{0,-1}};
                    heightMap.setHeights(x);
                }
            }else{
                int[][] x={{0,530},{15+tents[0].getIconWidth(),-1}};
                heightMap.setHeights(x);
            }
        }else{
            int[][] x={{0,-1}};
            heightMap.setHeights(x);
        }
        if(subPhase<5){
            acf[0]=1;
        }
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,acf[0]);
        g2d.setComposite(z);
        if(subPhase<7) {
            g.drawImage(levels[phase - 2][subPhase].getImage(), 0, 0, null);
        }
        else{
            /*g.setFont(cTitle);
            g.setColor(Color.white);
            g.drawString("Part 2", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Part 2")/2,300);*/
        }
        switch(subPhase){
            case 0: l1b1(g);
                break;
            case 1: l1b2(g);
                break;
            case 2: l1b3(g);
                break;
            case 3: l1b4(g);
                break;
            case 4: l1b5(g);
                break;
            case 5: l1b6(g);
                break;
            case 6: l1b7(g);
                break;
            case 7: l1end(g);
                break;
        }
    }

    //Code for all of the subphases int level 1
    private void l1b1(Graphics g){
        sd.drawString(g,"Alright guys, you know what to do, we're looking for a precious yellow gem located in a nearby cave, now go!",200,60,400,constantia,Color.white);
        g.drawImage(boss.getImage(),50,210,null);
        g.drawImage(bossCrowd.getImage(),boss.getIconWidth()+80,350,null);
        crowd.crowdrPosReset();
    }

    private void l1b2(Graphics g){
        if(crowdrMax==1) {
            if (p.getPlayerX() > Frame.panelX / 2) {
            }
            g.drawImage(crowdr[crowd.getCrowdrPos()].getImage(), crowd.getCrowdrPosInt(), 350, null);
            g.drawImage(cart.getImage(), crowd.getCrowdrPosInt() + 50 + crowdr[crowd.getCrowdrPos()].getIconWidth(), 350, null);
        }
    }

    private void l1b3(Graphics g){
        Rectangle cartHB;
        if(crowdrMax<2){
            crowdrMax=2;
            crowd.crowdrPosReset();
            //fade.start();
        }
        if(crowdrMax==2) {
            g.drawImage(crowdr[crowd.getCrowdrPos()].getImage(), crowd.getCrowdrPosInt(), 350, null);
            g.drawImage(cart.getImage(), crowd.getCrowdrPosInt() + 50 + crowdr[crowd.getCrowdrPos()].getIconWidth(), 350, null);
        }
        cartHB=new Rectangle(crowd.getCrowdrPosInt()+50+crowdr[crowd.getCrowdrPos()].getIconWidth(),350,cart.getIconWidth(),cart.getIconHeight());
        if(logVisible) {
            g.drawImage(log.getImage(), Frame.panelX - 300, 520, null);
        }
        if(p.getPlayerX()>=400&&p.getPlayerX()<=650&&!hasChainsaw&&crowdrV==0&&logVisible){
            g.setColor(Color.white);
            g.setFont(constantia);
            g.drawString("Press 'E' to grab chainsaw",(int)cartHB.getX(),(int)cartHB.getY());
        }
        if(p.getPlayerX()>=800&&hasChainsaw&&logVisible){
            g.setColor(Color.white);
            g.setFont(constantia);
            g.drawString("Press 'E' to cut",(int)logHitbox.getX(),520);
        }
        if(key.contains(VK_E)){
            if(p.getPlayerX()>=400&&p.getPlayerX()<=650&&!hasChainsaw&&crowdrV==0&&logVisible){
                hasChainsaw=true;
            }else if(p.getPlayerX()>=800&&hasChainsaw){
                logVisible=false;
                hasChainsaw=false;
                hasWood=true;
            }
        }
        if(logVisible&&subPhase==2&&p.getPlayerHitbox().intersects(logHitbox)){
            if(!((p.getPlayerX()>logHitbox.getX())||heightMap.onGround(p.getPlayerHitbox()).isOnGround())) {
                p.setPlayerX((float)(logHitbox.getX()-harold[0][0].getIconWidth()));
            }
        }
        if(crowdrV==0&&logVisible&&crowd.getCrowdrPosInt()>0){
            sd.drawString(g,"Hey, we won't be able to get the cart over that log, you should use some tools.",170,200,400,constantia,Color.white);
        }
    }

    private void l1b4(Graphics g){
        if(crowdrMax<3&&!logVisible){
            crowdrMax=3;
            fade.stop();
            crowd.crowdrPosReset();
        }
        if(!bridgeBuilt){
            if(p.getPlayerX()>440){
                p.setPlayerX(440);
            }
        }else{
            g.drawImage(bridge.getImage(),540,590,null);
            //fade.start();
        }
        if(p.getPlayerX()>325&&!bridgeBuilt&&hasWood){
            sd.drawString(g,"Press 'E' to place bridge",600,500,800,constantia,Color.white);
        }
        if(p.getPlayerX()>325&&!bridgeBuilt&&hasWood&&key.contains(VK_E)){
            bridgeBuilt=true;
            hasWood=false;
        }
        if(crowdrMax==3) {
            g.drawImage(crowdr[crowd.getCrowdrPos()].getImage(), crowd.getCrowdrPosInt(), 350, null);
            g.drawImage(cart.getImage(), crowd.getCrowdrPosInt() + 50 + crowdr[crowd.getCrowdrPos()].getIconWidth(), 375, null);
        }
    }

    private void l1b5(Graphics g){
        if(crowdrMax<4){
            crowdrMax=4;
            fade.start();
            crowd.crowdrPosReset();
        }
        if(crowdrMax==4) {
            g.drawImage(crowdr[crowd.getCrowdrPos()].getImage(), crowd.getCrowdrPosInt(), 350, null);
            g.drawImage(cart.getImage(), crowd.getCrowdrPosInt() + 50 + crowdr[crowd.getCrowdrPos()].getIconWidth(), 350, null);
        }
    }

    private void l1b6(Graphics g){
        fade.stop();
        if(crowdrMax<5){
            crowdrMax=5;
            //fade.start();
            crowd.crowdrPosReset();
        }
        if(crowdrMax==5) {
            g.drawImage(crowdr[crowd.getCrowdrPos()].getImage(), crowd.getCrowdrPosInt(), 350, null);
            g.drawImage(cart.getImage(), crowd.getCrowdrPosInt() + 50 + crowdr[crowd.getCrowdrPos()].getIconWidth(), 350, null);
        }
        if(crowdrV==0&&crowd.getCrowdrPosInt()>0){
            sd.drawString(g,"This looks like a good place to camp. Let's put our stuff down.",150,270,400,constantia,Color.white);
            if(fadeTime<100){
                fadeTime++;
                try{Thread.sleep(5);}catch(InterruptedException e){e.printStackTrace();}
            }else{
                cc.fadeOut();
                if(brightness==0){
                    subPhase++;
                    cc.fadeIn();
                }
            }
        }
        if(acf[0]==0){
            subPhase++;
        }
    }

    private void l1b7(Graphics g){
        if(!firstRun[0][6]){
            firstRun[0][6]=true;
            fade.start();
            p.setPlayerX(400);
        }
        g.drawImage(tents[3].getImage(),350,350,null);//Aqua lavender red mint orange (0,1,2,3,4)
        g.drawImage(tents[1].getImage(),568,530,null);
        p.drawPlayer(g);
        g.drawImage(tents[0].getImage(),15,410,null);
        Rectangle aqua=new Rectangle(15,530,tents[0].getIconWidth(),tents[0].getIconHeight()-120);
        if(p.getPlayerHitbox().intersects(aqua)&&p.getPlayerY()==360){
            p.setPlayerX((float)(aqua.getX()+aqua.getWidth()));
        }
        g.drawImage(tents[2].getImage(),1000,-20,null);
        g.drawImage(tents[4].getImage(),900,430,null);
        if(p.getPlayerX()>1000){
            //fade.start();
            cc.fadeOut();
            if(brightness==0){
                subPhase++;
            }
        }
    }

    private void l1end(Graphics g){
        l2.startFade();
        l2.draw(g);
        if(!firstRun[0][7]){
            firstRun[0][7]=true;
            acf[0]=0;
            levelEnd=true;
            p.setPlayerX(100);
            p.setVelocityX(0);
        }
        //fade.start();

    }
}
