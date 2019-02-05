package CasualCaving;

class Position {
    private float x,y;
    Position(){
        x=0;
        y=0;
    }
    Position(int startX,int startY){
        x=startX;
        y=startY;
    }

    float getX(){return x;}
    float getY(){return y;}

    void setX(float x){this.x=x;}
    void setY(float y){this.y=y;}
    void setCoord(float x,float y){
        this.x=x;
        this.y=y;
    }
}
