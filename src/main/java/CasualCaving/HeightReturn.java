package CasualCaving;

class HeightReturn {
    private boolean onGround;
    private int groundLevel;
    HeightReturn(boolean in){
        onGround=in;
    }

    HeightReturn(boolean in, int height){
        onGround=in;
        groundLevel=height;
    }

    boolean isOnGround() {
        return onGround;
    }

    int getGroundLevel() {
        return groundLevel;
    }
}
