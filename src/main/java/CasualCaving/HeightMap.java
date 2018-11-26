package CasualCaving;

public class HeightMap {
    private int[][] heights;
    HeightMap(){}
    void setHeights(int[][] in){
        heights=in;
    }
    int[][] getHeights() {
        return heights;
    }
}
