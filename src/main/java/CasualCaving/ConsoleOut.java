package CasualCaving;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ConsoleOut {
    ConsoleOut(){}

    void print(String in,boolean error){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        if(error){
            System.err.println("["+dateTimeFormatter.format(now)+"][Casual Caving/ERROR]:"+in);
        }else{
            System.out.println("["+dateTimeFormatter.format(now)+"][Casual Caving/INFO]:"+in);
        }
    }
}
