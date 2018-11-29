package CasualCaving;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    Hash(){}

    String md5(String in){
        StringBuilder x=new StringBuilder();
        byte[] d={};
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.update(in.getBytes());
            d=messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for(byte b:d){
            x.append(String.format("%02x", b & 0xff));
        }
        return x.toString();
    }
}
