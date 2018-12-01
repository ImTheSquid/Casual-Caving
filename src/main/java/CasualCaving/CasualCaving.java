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
    private Hash hash=new Hash();
    private HeightMap heightMap=new HeightMap();
    private UniqueIDGenerator uniqueIDGenerator=new UniqueIDGenerator(hash);
    private Crowd crowd=new Crowd();
    private BattleHandler battleHandler=new BattleHandler(this);
    private TimerControl tc=new TimerControl(crowd,battleHandler);
    private CavingLoader cl=new CavingLoader();
    private Player p=new Player(battleHandler);
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
    private static final int size=20;//Default font size
    static final Font constantia=new Font("constantia",Font.PLAIN,size);
    static final Font cTitle=new Font(constantia.getFontName(),Font.PLAIN,size*4);
    private static final Font c2=new Font(constantia.getFontName(),Font.PLAIN,size*2);
    private static final Font consolas=new Font("consolas",Font.PLAIN,size);
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
    static final Integer[][] selector={{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7,8},{0}};//Used for selecting subphases for different levels, as well as determining the number of subphases in a level
    private boolean debugUnlocked=false;
    private JPanel passwords=new JPanel();
    private JPasswordField pass=new JPasswordField(10);
    static Boolean[][] firstRun=new Boolean[3][9];
    static float[] acf={1,1,1};//Alpha Composite Float values for all levels
    static int fadeTime=0;
    static boolean onObject=false;
    static int newGround=0;
    static boolean levelEnd=true;
    static float qe=0;
    static boolean qeChoice=false;
    static boolean l2b6FadeDone=false;
    static boolean choice=false;
    static float gameOverFade=0;
    static boolean goIO=false;//Game over in-out
    static boolean lights=true;
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
        subPhase=0;
        qeChoice=false;
        crowdrV=0;
        crowd.crowdrPosReset();
        hasChainsaw=false;
        hasWood=false;
        levelEnd=true;
        newGround=0;
        onObject=false;
        for(int i=0;i<firstRun.length;i++){
            for(int j=0;j<firstRun[i].length;j++){
                firstRun[i][j]=false;
            }
        }
        qe=0;
        l2b6FadeDone=false;
        gameOverFade=0;
        goIO=false;
        lights=true;
    }

    private void draw(Graphics g){//Draws the scene
        Graphics2D g2d=(Graphics2D)g;
        switch (phase) {
            case -1://Game over
                gameOver(g,g2d);
                break;
            case 0://Introduction
                intro(g, g2d);
                break;
            case 1://Title screen
                title(g, g2d);
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
            repaint();
        }
    }

    private void gameOver(Graphics g,Graphics2D g2d){
        fade.start();
        AlphaComposite z=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,gameOverFade);
        g2d.setComposite(z);
        g.setColor(Color.white);
        g.setFont(cTitle);
        g.drawString("Game Over", Frame.panelX/2-g.getFontMetrics(cTitle).stringWidth("Game Over")/2,300);
        if(key.contains(VK_ENTER)){
            goIO=true;
            gameOverFade=0;
        }
        if(gameOverFade==0&&goIO) {
            titleReady = false;
            gameStart = false;
            phase = 1;
            subPhase = 0;
            fade.start();
            titleA = 0;
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

    private void title(Graphics g,Graphics2D g2d){//Handles drawing the title screen
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
        String version="Casual Caving 0.0.9  ";
        g.drawString(version, Frame.panelX-g.getFontMetrics(constantia).stringWidth(version)-10, Frame.panelY-(size*2)-5);
        g.setFont(cTitle);
        g.drawString("Start",(Frame.panelX-g.getFontMetrics(cTitle).stringWidth("Start"))/2,(Frame.panelY/2)+g.getFontMetrics(cTitle).getHeight()/4);
        g.setColor(new Color(196,0,0));
        g.fillRect(Frame.panelX/2-50, Frame.panelY-110,100,50);
        quitButton=new Rectangle(Frame.panelX/2-50, Frame.panelY-80,100,50);
        g.setColor(Color.white);
        g.setFont(c2);
        g.drawString("Quit",(Frame.panelX-g.getFontMetrics(c2).stringWidth("Quit"))/2, Frame.panelY-g.getFontMetrics(c2).getHeight()-25);
        reset();
    }

    static void drawString(Graphics g,String text,int x,int y,int x2,Font f,Color c){
        String temp="";
        int z=0;
        for(int i=0;i<text.length();i++){
            if((g.getFontMetrics(f).stringWidth(temp.substring(z))>x2-x)){
                for(int p=i-1;p>0;p--){
                    if(temp.charAt(p)==' '){
                        temp=temp.substring(0,p+1)+'~'+temp.substring(p+1);
                        z=i;
                        break;
                    }
                }
            }
            temp+=text.charAt(i);
        }
        for(String line:temp.split("~")){
            g.setFont(f);
            g.setColor(c);
            g.drawString(line,x,y+=g.getFontMetrics().getHeight());
        }
    }


    void mDecode(Point p){//Gets mouse movements from Frame
        switch(phase){
            case 1:
                if(titleA>0) {
                    if (startButton.contains(p)) {
                        gameStart = true;
                        fade.start();
                    }
                    if(quitButton.contains(p)){
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
            fade.start();
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
                int op=JOptionPane.showOptionDialog(null,passwords,"Input Debug Password",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,o,o[0]);
                if(op==0){
                    char[] p=pass.getPassword();
                    for (char c : p) {
                        entry += c;
                    }
                }
                final String dP="e09dd51b8cb7a1d48ca0f563b8fdc693";
                if(hash.md5(entry).equals(dP)){
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
            System.out.println(Frame.console.isVisible());
            Frame.jTextArea.setText("");
            Frame.console.setVisible(true);
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
        pause = !pause;
    }
}
