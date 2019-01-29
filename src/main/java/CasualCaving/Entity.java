package CasualCaving;

class Entity extends Attackable{
    private int health;
    private int maxHealth;
    Entity(int startHealth, int maxHealth){
        health=startHealth;
        this.maxHealth=maxHealth;
    }

    int getHealth() {
        return health;
    }

    void setHealth(int health) {
        if(health>maxHealth)return;
        this.health = health;
    }
}
