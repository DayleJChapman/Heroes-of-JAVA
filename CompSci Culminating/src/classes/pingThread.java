package classes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class pingThread implements Runnable{
public static boolean s=false;
    @Override
    public void run() {
        try {
            while(true){
                if(s==true){
                    new Thread(new SendThread(null,"p")).start();
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(pingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void shutdown(){
        s=false;
    }
    public static void start(){
        s=true;
    }
    
}
