package CasualCaving;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ConsoleOut {
    ConsoleOut(){}

    void print(String in){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        System.out.println("["+dateTimeFormatter.format(now)+"][Casual Caving/INFO]:"+in);
    }

    void printErr(String in){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        System.err.println("["+dateTimeFormatter.format(now)+"][Casual Caving/ERROR]:"+in);
    }
}
