package Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkeller1025
 */
public class Leaver implements Runnable{
    /*
    Steps:
    1. Check for time out
    2. Send "olc", look for available opponent
    3. Delete Not existing pairs
    */
    
    public final static long timeout=7000;
    @Override
    public void run() {
        while(true){
            //Check player 1 array
            for(int c=0; c<TicTacServer.ltplayer1.size(); c++){
                if(!TicTacServer.player1.get(c).equals("127.0.0.1") && TicTacServer.ltplayer1.get(c)<(System.currentTimeMillis()-timeout)){
                    try {
                        //if player from Player 1 is timed out
                        if(c<TicTacServer.player2.size()){
                            if(TicTacServer.ltplayer2.get(c)!=0 && TicTacServer.ltplayer1.get(c)!=0){
                                System.out.println("Player 1 timeout");
                                new Thread(new SendThread(null,"olc",TicTacServer.player2.get(c),TicTacServer.sport)).start();
                            }
                        }
                        TicTacServer.player1.set(c, InetAddress.getByName("127.0.0.1"));
                        TicTacServer.ltplayer1.set(c, (long)0);
                        lookfor("1"+c);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Leaver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //Check player 2 array
            for(int c=0; c<TicTacServer.ltplayer2.size(); c++){
                if(!TicTacServer.player2.get(c).equals("127.0.0.1") && TicTacServer.ltplayer2.get(c)<(System.currentTimeMillis()-timeout)){
                    try {
                        //if player from Player 2 is timed out
                        if(c<TicTacServer.player1.size()){
                            if(TicTacServer.ltplayer1.get(c)!=0 && TicTacServer.ltplayer2.get(c)!=0){
                                System.out.println("Player 2 timeout");
                                new Thread(new SendThread(null,"olc",TicTacServer.player1.get(c),TicTacServer.sport)).start();
                            }
                        }
                        TicTacServer.player2.set(c, InetAddress.getByName("127.0.0.1"));
                        TicTacServer.ltplayer2.set(c, (long)0);
                        lookfor("2"+c);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Leaver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //deleting
            //if player 1 array is bigger
            if(TicTacServer.ltplayer1.size()>TicTacServer.ltplayer2.size()){
                for(int c=0; c<TicTacServer.ltplayer2.size(); c++){
                    if(TicTacServer.ltplayer1.get(c)==0 && TicTacServer.ltplayer2.get(c)==0){
                        TicTacServer.player1.remove(c);
                        TicTacServer.player2.remove(c);
                        TicTacServer.ltplayer1.remove(c);
                        TicTacServer.ltplayer2.remove(c);
                    }
                }
                //player 2array is bigger
            }else if(TicTacServer.ltplayer2.size()>TicTacServer.ltplayer1.size()){
                for(int c=0; c<TicTacServer.ltplayer1.size(); c++){
                    if(TicTacServer.ltplayer2.get(c)==0 && TicTacServer.ltplayer1.get(c)==0){
                        System.out.println("Deleting an unused pair");
                        TicTacServer.player1.remove(c);
                        TicTacServer.player2.remove(c);
                        TicTacServer.ltplayer1.remove(c);
                        TicTacServer.ltplayer2.remove(c);
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Leaver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    

    //Check player 2 array

    private void lookfor(String in){
        boolean f=false;
        int fg=0, fn=0, tg=0, tn=0;
        //Looking for player 1
        if(in.startsWith("1")){
            tg=1;
            tn=Integer.parseInt(in.substring(1));
        //Looking for player 2
        }else{
            tg=2;
            tn=Integer.parseInt(in.substring(1));
        }
        //look for lonely player 1s
        for(int c=0; c<TicTacServer.player1.size(); c++){
            //check if player has no pair
            if(c>=TicTacServer.player2.size()){
                fg=1;
                fn=c;
                f=true;
            }
            //if he has a pair
            else if(TicTacServer.ltplayer1.get(c)!=0 && TicTacServer.ltplayer2.get(c)==0){
                fg=1;
                fn=c;
                f=true;
            }
        }
        //look for lonely player 2s
        for(int c=0; c<TicTacServer.player2.size(); c++){
            //if he has a pair
            if(TicTacServer.ltplayer2.get(c)!=0 && TicTacServer.ltplayer1.get(c)==0){
                fg=2;
                fn=c;
                f=true;
            }
        }
        //switching
        if(f==true){
            if(fg==1 && tg==1)TicTacServer.player1.set(tn, TicTacServer.player1.get(fn));
            else if(fg==1 && tg==2)TicTacServer.player2.set(tn, TicTacServer.player1.get(fn));
            else if(fg==2 && tg==1)TicTacServer.player1.set(tn, TicTacServer.player2.get(fn));
            else if(fg==2 && tg==2)TicTacServer.player2.set(tn, TicTacServer.player2.get(fn));
        }
    }
}