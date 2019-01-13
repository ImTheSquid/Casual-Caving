package CasualCaving;

import java.util.ArrayList;

/**
 * This class is used for unique ID generation for all entities except for the player, who always has an ID of 0
 */

class UniqueIDGenerator {
    private Hash hash;
    private ArrayList<Integer> knownHashes=new ArrayList<>();
    UniqueIDGenerator(Hash hash){this.hash=hash;}

    String generateHash(){
        Integer input=(int)(Math.random()*123867)+4;//Can be any random number generator
        while(knownHashes.contains(input)){
            input=(int)(Math.random()*123867)+4;
        }
        knownHashes.add(input);
        return hash.sha512(input.toString());
    }
}
