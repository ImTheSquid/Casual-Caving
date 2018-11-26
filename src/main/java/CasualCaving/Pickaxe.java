package CasualCaving;

class Pickaxe {
    private BattleHandler battleHandler;
    private Player p;
    Pickaxe(BattleHandler battleHandler,Player p){
        this.battleHandler=battleHandler;
        this.p=p;
    }

    void attack(float velocity){
        final int swingDamage=10;
        final int swingRange=20;
        float[] stats={swingDamage,swingRange,velocity};
        battleHandler.attack(stats,p,"0");
    }
}
