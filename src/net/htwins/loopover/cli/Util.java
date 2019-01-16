package net.htwins.loopover.cli;

public class Util {
    public static void flushTerminals() {
        System.out.flush();
        System.err.flush();
        sleep(10);
    }
    
    public static void sleep(int millis) {
        try { Thread.sleep(10); } catch(InterruptedException e) {}
    }
}
