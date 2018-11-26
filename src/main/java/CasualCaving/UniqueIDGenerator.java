package CasualCaving;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This class is used for unique ID generation for all entities except for the player, who always has an ID of 0
 */

class UniqueIDGenerator {
    private ArrayList<Integer> knownHashes=new ArrayList<>();
    UniqueIDGenerator(){}

    String generateHash(){
        Integer input=(int)(Math.random()*123867)+4;//Can be any random number generator
        while(knownHashes.contains(input)){
            input=(int)(Math.random()*123867)+4;
        }
        knownHashes.add(input);
        String data=input.toString();
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            byte hash[]=messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
