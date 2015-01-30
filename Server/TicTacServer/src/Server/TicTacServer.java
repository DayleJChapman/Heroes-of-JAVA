package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
/**
 *
 * @author mkeller1025
 */
public class TicTacServer{
    public static boolean busy=false;
    public static int port = 6002, sport=6001;
    public static DatagramSocket socket;
    public static ArrayList<InetAddress> player1=new ArrayList();
    public static ArrayList<InetAddress> player2=new ArrayList();
    public static ArrayList<Long> ltplayer1=new ArrayList();
    public static ArrayList<Long> ltplayer2=new ArrayList();
    public static ArrayList<Boolean> igplayer1=new ArrayList();
    public static ArrayList<Boolean> igplayer2=new ArrayList();
    public static ArrayList<String> log=new ArrayList();
    public static int c=0;
    public TicTacServer(){
    }
    public static void main(String[] args) throws SocketException, IOException {
        socket = new DatagramSocket(port);
        (new Thread(new RecieveThread())).start();
        (new Thread(new Leaver())).start();
        Window.bo();
        while(true){
            if((player1.size()==player2.size()) && player1.size()==c+1){
                //(new Thread(new Game())).start();
                //c++;
            }
        }
    }
    
    public static void printAll(){
        log.add("player 1:");
        for(int c=0; c<player1.size(); c++){
            log.add(String.valueOf(player1.get(c)));
        }
        log.add(String.valueOf("player 2:"));
        for(int c=0; c<player2.size(); c++){
            log.add(String.valueOf(player2.get(c)));
        }
        log.add(String.valueOf("ltplayer 1:"));
        for(int c=0; c<ltplayer1.size(); c++){
            log.add(String.valueOf(ltplayer1.get(c)));
        }
        log.add(String.valueOf("ltplayer 2:"));
        for(int c=0; c<ltplayer2.size(); c++){
            log.add(String.valueOf(ltplayer2.get(c)));
        }
        log.add(String.valueOf("igplayer 1:"));
        for(int c=0; c<igplayer1.size(); c++){
            log.add(String.valueOf(igplayer1.get(c)));
        }
        log.add(String.valueOf("igplayer 2:"));
        for(int c=0; c<igplayer2.size(); c++){
            log.add(String.valueOf(igplayer2.get(c)));
        }
        log.add(String.valueOf("-----------"));
    }
    
    public static String getIpAddress() throws MalformedURLException, IOException {
      URL myIP = new URL("http://checkip.amazonaws.com/");
      BufferedReader in = new BufferedReader(
                           new InputStreamReader(myIP.openStream())
                          );
      return "Public IP: "+in.readLine();
    }
    
    public static String ip() throws UnknownHostException{
        return "Local IP: " + InetAddress.getLocalHost().toString();
    }
}
