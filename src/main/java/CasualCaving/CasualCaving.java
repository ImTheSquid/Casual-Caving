package CasualCaving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

/**
 * This is the main control class and interfaces with all of the other classes to make the game work
 */

public class CasualCaving extends JPanel{
    private StringDraw sd=new StringDraw();
    private Hash hash=new Hash();
    private HeightMap heightMap=new HeightMap();
    private UniqueIDGenerator uniqueIDGenerator=new UniqueIDGenerator(hash);
    private Crowd crowd=new Crowd();
    private BattleHandler battleHandler=new BattleHandler(this);
    private Player p=new Player(battleHandler,heightMap);
    private TimerControl tc=new TimerControl(crowd,p);
    private CavingLoader cl=new CavingLoader();
    private Level1 l1=new Level1(tc.getFade(),p,crowd,heightMap);
    private Level2 l2=new Level2(tc.getFade(),p,heightMap);
    private Level3 l3=new Level3(tc.getFade(),p,uniqueIDGenerator,heightMap);
    private Object[] la={l1,l2,l3};
    private Timer fade=tc.getFade();
    static int phase=0;//Changes what is drawn on screen
    static int subPhase=0;//Changes phase of level
    static float logoA=0;//Logo alpha
    static float loadA=1;//Load alpha
    static float titleA=0;//Title alpha
    private ImageIcon lunan=cl.getLunan();
    private ImageIcon load=cl.getLoad();
    private ImageIcon title=cl.getTitle();
    static int crowdrV=0;
    static boolean titleReady=false;
    static final int size=20;//Default font size
    static final Font constantia=new Font("constantia",Font.PLAIN,size);
    static final Font cTitle=new Font(constantia.getFontName(),Font.PLAIN,size*4);
    private static final Font c2=new Font(constantia.getFontName(),Font.PLAIN,size*2);
    private static final Font consolas=new Font("consolas",Font.PLAIN,size);
    private TitleScreen titleScreen=new TitleScreen(constantia,cTitle,c2);
    private GameOver gameOver=new GameOver(cTitle);
    static boolean gameStart=false;
    static final float gravity=0.5f;
    private boolean debug=false;
    static boolean hasChainsaw=false;
    static boolean hasWood=false;
    private Rectangle startButton;
    private Rectangle exitButton=new Rectangle((Frame.panelX/2)-105,(Frame.panelY/2)+20,210,40);
    private Rectangle quitButton;
    static Set<Integer> key=new HashSet<>();//Stores current keyboard presses
    static boolean pause=false;//Pause var
    static final Integer[][] selector={{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7,8},{0,1}};//Used for selecting subphases for different levels, as well as determining the number of subphases in a level
    private boolean debugUnlocked=false;
    private JPanel passwords=new JPanel();
    private JPasswordField pass=new JPasswordField(10);
    static Boolean[][] firstRun=new Boolean[3][9];
    static volatile float[] acf={1,1,1};//Alpha Composite Float values for all levels
    static int fadeTime=0;
    static boolean levelEnd=true;
    static float qe=0;
    static boolean qeChoice=false;
    static boolean l2b6FadeDone=false;
    static boolean choice=false;
    static boolean lights=true;
    private boolean fadeSave=false;
    CasualCaving(){
        fade.start();
        JLabel label=new JLabel("Enter Debug Password:");
        passwords.add(label);
        passwords.add(pass);
        for(int i=0;i<firstRun.length;i++){
            for(int j=0;j<firstRun[i].length;j++){
                firstRun[i][j]=false;
            }
        }
    }

    Object[] getLa() {
        return la;
    }

    public void paintComponent(Graphics g){//Base draw
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0, Frame.panelX, Frame.panelY);
        draw(g);
    }

    private void reset(){
        crowd.reset();
        p.reset();
        l1.reset();
        l2.reset();
        l3.reset();
        subPhase=0;
        qeChoice=false;
        crowdrV=0;
        crowd.crowdrPosReset();
        hasChainsaw=false;
        hasWood=false;
        levelEnd=true;
        for(int i=0;i<firstRun.length;i++){
            for(int j=0;j<firstRun[i].length;j++){
                firstRun[i][j]=false;
            }
        }
        qe=0;
        l2b6FadeDone=false;
        lights=true;
    }

    private void draw(Graphics g){//Draws the scene
        Graphics2D g2d=(Graphics2D)g;
        switch (phase) {
            case -1://Game over
                gameOver(g);
                break;
            case 0://Introduction
                intro(g, g2d);
                break;
            case 1://Title screen
                title(g);
                break;
            case 2://Level 1
                l1.level1(g,g2d);
                break;
            case 3://Level 2
                l2.level2(g,g2d);
                break;
            case 4://Level 3
                l3.level3(g,g2d);
                break;
        }
        if(debug&&phase>1){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.25f));
            g.setColor(Color.black);
            g.fillRect(0,0,10+Math.max(g.getFontMetrics(consolas).stringWidth("Level:"+(phase-2)+" Subphase: "+subPhase),g.getFontMetrics(consolas).stringWidth("Player X:"+p.getPlayerX()+" Y:"+p.getPlayerY())),10+g.getFontMetrics(consolas).getHeight()*2);
            g.setFont(consolas);
            g.setColor(Color.white);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g.drawString("Player X:"+p.getPlayerX()+" Y:"+(p.getPlayerY()+p.getPlayerHitbox().getHeight()),5,25);
            g.drawString("Level:"+(phase-2)+" Subphase:"+subPhase,5,25+g.getFontMetrics(consolas).getHeight());
        }else if(debug&&phase==1){
            g.setFont(consolas);
            g.setColor(Color.red);
            g.drawString("DEBUG MODE ACTIVE",5,25);
        }
        if(!Frame.j.isActive()&&phase>1&&!debug){
            pause=true;
        }
        if (phase > 1&&!pause) {//Deals with pause implementation
            if(acf[phase-2]<1&&acf[phase-2]>0){
                fade.start();
            }
            p.playerHandle(g);
        }else if(phase>1){//Draws the pause menu screen
            fade.stop();
            p.drawPlayer(g);
            AlphaComposite p=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
            g.setColor(Color.gray);
            g2d.setComposite(p);
            g.fillRect(0,0, Frame.panelX, Frame.panelY);
            g.fillRect((Frame.panelX/2)-105,(Frame.panelY/2),210,40);
            g.setColor(Color.white);
            g.setFont(cTitle);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            g.drawString("Pause Menu", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Pause Menu")/2,250);
            g.setFont(c2);
            g.drawString("Exit to Title", Frame.panelX/2-g.getFontMetrics(c2).stringWidth("Exit to Title")/2, Frame.panelY/2+33);
            g.setFont(constantia);
            g.drawString("Press 'Esc' to reenter the game...", Frame.panelX/2-g.getFontMetrics(constantia).stringWidth("Press \"Esc\" to reenter the game...")/2,300);
        }
        repaint();
    }

    private void gameOver(Graphics g){
        if(gameOver.isRunnable())gameOver.startFade();
        gameOver.draw(g);
        if(gameOver.isComplete()){
            phase=1;
            fade.start();//TO BE REMOVED LATER AND REPLACED
        }
    }

    private void intro(Graphics g,Graphics2D g2d){//Handles drawing the intro animation
        AlphaComposite ls=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,loadA);
        g2d.setComposite(ls);
        g.drawImage(load.getImage(),0,0,null);
        g.setColor(Color.white);
        g.setFont(constantia);
        g.drawString("Press 'S' to skip",0, Frame.panelY-(size*2)-5);
        AlphaComposite ac=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,logoA);
        g2d.setComposite(ac);
        g.drawImage(lunan.getImage(), Frame.panelX/2-(lunan.getIconWidth()/2)-15,30,null);
    }

    private void title(Graphics g){//Handles drawing the title screen
        titleScreen.draw(g);
        reset();
    }

    void mDecode(Point p){//Gets mouse movements from Frame
        switch(phase){
            case 1:
                if(titleA>0) {
                    if (titleScreen.getStartButton().contains(p)) {
                        gameStart = true;

                        fade.start();
                    }
                    if(titleScreen.getQuitButton().contains(p)){
                        System.exit(0);
                    }
                }
                break;
        }
        if(phase>1&&exitButton.contains(p)&&pause){
            int result=JOptionPane.showConfirmDialog(null,"Are you sure you want to quit? ALL PROGRESS WILL BE LOST!","Are You Sure?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(result==0) {
                phase = 1;
                pause = false;
                gameStart = false;
                titleReady = false;
                fade.start();
                repaint();
            }
        }
    }


    void kPress(int k){//Gets key presses from Frame
        key.add(k);
        if(key.contains(KeyEvent.VK_ESCAPE)){
            pause();
        }
        if(key.contains(KeyEvent.VK_SPACE)||key.contains(KeyEvent.VK_UP)||key.contains(KeyEvent.VK_W)){
            p.jump();
        }
        if(key.contains(KeyEvent.VK_ENTER)&&!gameStart&&phase==1){
            gameStart=true;
            if(!debug) {
                fade.start();
            }else{
                phase=2;
                repaint();
            }
        }
        if(key.contains(KeyEvent.VK_S)&&phase==0){
            phase=1;
            repaint();
        }
        if(key.contains(KeyEvent.VK_BACK_SPACE)){
            key.remove(KeyEvent.VK_BACK_SPACE);
            if(!debugUnlocked){
                String entry="";
                String[] o=new String[]{"OK","Cancel"};
                pass.setText("");
                int op= JOptionPane.showOptionDialog(null,passwords,"Input Debug Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,o,o[0]);
                if(op==0){
                    char[] p=pass.getPassword();
                    for (char c : p) {
                        entry += c;
                    }
                }
                final String dP="3E6A980D2E3ABDE035EE8AA31B7E22F9397F8F738511F774B4E50029AA08B732A0C849BEA3F486AA6EAC50ED452868E0E3C00D9AE5A4535AECAFB1961BDCBCA6";
                if(hash.sha512(entry).toUpperCase().equals(dP)){
                    debugUnlocked=true;
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Password","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (debug) {
                debug = false;
            } else if(debugUnlocked){
                debug = true;
            }
            repaint();
        }
        if(key.contains(KeyEvent.VK_L)&&phase>1&&debug){
            ArrayList<Integer> x=new ArrayList<>();
            for(int i=0;i<selector.length;i++){
                x.add(i);
            }
            try {phase = (int) JOptionPane.showInputDialog(null, "Select Level", "Debug Level Selector", JOptionPane.QUESTION_MESSAGE, null, x.toArray(), x.toArray()[0])+2;}
            catch(Exception ignored){}
            key.remove(KeyEvent.VK_L);
            subPhase=0;
            acf[phase-2]=1;
            reset();
            repaint();
        }
        if(key.contains(VK_P)&&phase>1&&debug){
            ArrayList<Integer> x=new ArrayList<>();//Converts from 2d to 1d for use in the dialogue box
            Collections.addAll(x, selector[phase - 2]);
            try{subPhase=(int)JOptionPane.showInputDialog(null,"Select Subphase","Debug Subphase Selector",JOptionPane.QUESTION_MESSAGE,null,x.toArray(),x.toArray()[0]);}
            catch (Exception ignored){}
            key.remove(VK_P);
            acf[phase-2]=1;
            repaint();
        }
        if(key.contains(VK_C)){
            key.remove(VK_C);
            System.setOut(Frame.ps);
            System.setErr(Frame.ps);
            Frame.jTextArea.setText("");
            Frame.console.setVisible(true);
            Frame.console.setLocation(Frame.j.getLocationOnScreen());
            Frame.co.print("Output Redirected");
        }
        if(key.contains(VK_K)&&debug){
            phase=-1;
            key.remove(VK_K);
        }
        if(key.contains(VK_ESCAPE)&&phase==1){
            System.exit(0);
        }
    }
    void kRelease(int k){
        key.remove(k);
        if(k==KeyEvent.VK_SPACE||k==KeyEvent.VK_W||k==KeyEvent.VK_UP){
            p.jumpEnd();
        }
    }

    private void pause(){
        if(!pause){
            fadeSave=fade.isRunning();
        }else{
            if(fadeSave)fade.start();
        }
        pause = !pause;
    }
}
