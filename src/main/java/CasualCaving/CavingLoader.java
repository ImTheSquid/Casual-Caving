package CasualCaving;

import javax.swing.*;

/**
 * This class is for loading in all of the images/sounds from the Resources folder
 */

class CavingLoader {
    private final ImageIcon[][] harold={{new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Harold.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldLeft.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldLeftW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldLeftW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldLeftW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsaw.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawLeft.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawLeftW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawLeftW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldChainsawLeftW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWood.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodLeft.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodLeftW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodLeftW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/HaroldWoodLeftW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLL.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLLeft.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLLeftW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLLeftW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldLLLeftW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRope.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeW3.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeLeft.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeLeftW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeLeftW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Lantern Light/HaroldRopeLeftW3.png"))}};
    private final ImageIcon[][] haroldAttack={{new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA3.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA4.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA1Left.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA2Left.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA3Left.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Attack/HaroldA4Left.png"))}};
    private final ImageIcon titleIcon= new ImageIcon(this.getClass().getResource("/CasualCaving/Title/WindowIcon.png"));
    private final ImageIcon lunan=new ImageIcon(this.getClass().getResource("/CasualCaving/Title/LunanLogo.png"));
    private final ImageIcon load=new ImageIcon(this.getClass().getResource("/CasualCaving/Title/LoadScreen.png"));
    private final ImageIcon title=new ImageIcon(this.getClass().getResource("/CasualCaving/Title/TitleScreen.png"));
    private final ImageIcon[][] levels={{new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg3.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg4.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg5.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg6.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Lvl1Bg6Dusk.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg3.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg4.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg5.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg6.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Sun Stone.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Lvl3Bg1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Lvl3Bg2.png"))}};
    private final ImageIcon[] foregrounds={new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg1Foreground.png"))};
    private final ImageIcon minersl2b1=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Sprites/Miners1.png"));
    private final ImageIcon ls=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Sprites/Lavender Savior.png"));
    private final ImageIcon qeSunStone=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Sprites/QESunStone.png"));
    private final ImageIcon sparks=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Sprites/Spark.png"));
    private final ImageIcon[] l2b3Variation={new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg3Rope.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 2/Lvl2Bg3Anchor.png"))};
    private final ImageIcon log=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/Log.png"));
    private final ImageIcon cart=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/Cart.png"));
    private final ImageIcon boss=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/BossMan.png"));
    private final ImageIcon crowd=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/Crowd.png"));
    private final ImageIcon[] crowdr={new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/CrowdReversed.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/CrowdReversedW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/CrowdReversedW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/CrowdReversedW3.png"))};
    private final ImageIcon bridge=new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/Bridge.png"));
    private final ImageIcon[] tents={new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/AquaTent.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/LavenderTent.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/RedTent.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/MintTent.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 1/Sprites/HaroldTent.png"))};
    private final ImageIcon[][] blueGolem={{new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemStill.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW3.png"))},{new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemStillFL.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW1FL.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW2FL.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Entities/Blue Golem/BlueGolemW3FL.png"))}};
    private final ImageIcon[][] sunGolems={{new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Isolsi_Eyes.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Hematus_Eyes.png"))},
            {new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Igneox_Eyes_1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Igneox_Eyes_2.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Igneox_Eyes_3.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Level 3/Sun Golem Fade/Igneox_Eyes_4.png"))}};
    private final ImageIcon heart=new ImageIcon(this.getClass().getResource("/CasualCaving/Objects/Health_Heart.png"));
    private final ImageIcon[] haroldTurn={new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Harold Turn/HaroldTurn1.png")),new ImageIcon(this.getClass().getResource("/CasualCaving/Harold/Harold Turn/HaroldTurn2.png"))};

    CavingLoader(){}

    ImageIcon[][] getHarold(){
        return harold;
    }

    ImageIcon[][] getHaroldAttack(){return haroldAttack;}

    ImageIcon getTitleIcon(){
        return titleIcon;
    }

    ImageIcon getLunan() {
        return lunan;
    }

    ImageIcon getLoad() {
        return load;
    }

    ImageIcon getTitle() {
        return title;
    }

    ImageIcon[][] getLevels() {
        return levels;
    }

    ImageIcon[] getForegrounds() {
        return foregrounds;
    }

    ImageIcon getMinersl2b1() {
        return minersl2b1;
    }

    ImageIcon getLs() {
        return ls;
    }

    ImageIcon getQeSunStone() {
        return qeSunStone;
    }

    ImageIcon getSparks() {
        return sparks;
    }

    ImageIcon[] getL2b3Variation() {
        return l2b3Variation;
    }

    ImageIcon getLog() {
        return log;
    }

    ImageIcon getCart() {
        return cart;
    }

    ImageIcon getBoss() {
        return boss;
    }

    ImageIcon getCrowd() {
        return crowd;
    }

    ImageIcon[] getCrowdr() {
        return crowdr;
    }

    ImageIcon getBridge() {
        return bridge;
    }

    ImageIcon[] getTents() {
        return tents;
    }

    ImageIcon[][] getBlueGolem() {
        return blueGolem;
    }

    ImageIcon[][] getSunGolems(){return sunGolems;}

    ImageIcon[] getHaroldTurn(){return haroldTurn;}

    ImageIcon getHeart() {
        return heart;
    }
}

