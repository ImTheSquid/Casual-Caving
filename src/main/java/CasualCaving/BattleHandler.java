package CasualCaving;

/**
 * This class handles everything relating to battle and battle mechanics
 */

class BattleHandler{
    private CasualCaving cc;
    BattleHandler(CasualCaving cc){this.cc=cc;}
    //Melee only
    void attack(float[] stats, Object attacker,String ID){//Stats: p0:attack, p1:range, p2:velocity
        int phase=CasualCaving.phase;
        int subPhase=CasualCaving.subPhase;
        int atk=(int)stats[0];//Attack
        int rng=(int)stats[1];//Range
        float v=stats[2];//Velocity
        int x=(int)stats[3];
        int y=(int)stats[4];
        Object[] la=cc.getLa();//Gets the level array
        switch(phase-2){
            case 2:
                Level3 l3=(Level3)la[2];
                Object[] o=l3.getEntities();
                break;
        }
    }
}
