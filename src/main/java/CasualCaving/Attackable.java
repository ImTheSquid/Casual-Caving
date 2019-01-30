package CasualCaving;

import java.awt.*;

public class Attackable {
    Rectangle hitbox;
    Attackable(){}
    void setHitbox(Rectangle hitbox){this.hitbox=hitbox;}
    Rectangle getHitbox(){return hitbox;}
}
