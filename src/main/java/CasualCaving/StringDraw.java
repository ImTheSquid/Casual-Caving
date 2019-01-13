package CasualCaving;

import java.awt.*;

class StringDraw {
    StringDraw(){}
    void drawString(Graphics g,String in,int x,int y,int x2,Font f,Color c){
        String temp="";
        int z=0;
        for(int i=0;i<in.length();i++){
            if((g.getFontMetrics(f).stringWidth(temp.substring(z))>x2-x)){
                for(int p=i-1;p>0;p--){
                    if(temp.charAt(p)==' '){
                        temp=temp.substring(0,p+1)+'~'+temp.substring(p+1);
                        z=i;
                        break;
                    }
                }
            }
            temp+=in.charAt(i);
        }
        g.setFont(f);
        for(String line:temp.split("~")){
            g.setColor(c);
            g.drawString(line,x,y+=g.getFontMetrics().getHeight());
        }
    }

    private int countChars(String in,char target){
        int count=0;
        for(int i=0;i<in.length();i++){
            if(in.charAt(i)==target)count++;
        }
        return count;
    }
}
