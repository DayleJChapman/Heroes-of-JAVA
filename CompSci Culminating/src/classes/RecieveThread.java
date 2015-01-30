package classes;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RecieveThread implements Runnable{
    public static boolean d=true;
    @Override
    public void run() {
        try {
            while (d==true) {
                DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                Main.socket.receive(request);
                InetAddress clientHost = request.getAddress();//ip
                int clientPort = request.getPort();//port
                //get msg
                byte[] buf = request.getData();
                ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                InputStreamReader isr = new InputStreamReader(bais);
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine(); //incoming line, itself
                //msg got
                //printData(line);
                String msg=getMsg(line);
                Main.hist.add(msg);
                System.out.println(msg);
                if (msg.contains("<w>")){
                    LeaderBoards.hs(msg);
                }else if("1".equals(msg)){
                    //player is player 1
                    Main.player=1;
                    Main.opponant=2;
                    Main.ig=true;
                }else if(msg.equals("2")){
                    //play is player 2
                    Main.player=2;
                    Main.opponant=1;
                    Main.ig=true;
                }else if(msg.contains("ougo")){
                    //opponent ends turn
                    Main.turn = Main.player;
                }else if(msg.equals("olc")){
                    //opponent disconnects
                    Main.ig=false;
                    new Thread(new SendThread(null,"dc")).start();
                    
                }else if(msg.startsWith("om")){
                    //display enemy's movement
                    msg=msg.substring(2);
                    String temp[]=msg.split(",");
                    for(int x=0; x<GameScreen.unitList.size(); x++){
                        if(GameScreen.unitList.get(x).getX()==Integer.parseInt(temp[0]) && GameScreen.unitList.get(x).getY()==Integer.parseInt(temp[1])){
                            GameScreen.unitList.get(x).setX(Integer.parseInt(temp[2]));
                            GameScreen.unitList.get(x).setY(Integer.parseInt(temp[3]));
                            GameScreen.unitList.get(x).setPos();
                            BoardMatrix.board[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 0;
                        }
                    }
                }else if(msg.startsWith("oa")){
                    //take damage
                    msg=msg.substring(2);
                    String temp[]=msg.split(",");
                    for(int x=0; x<GameScreen.unitList.size(); x++){
                        if(GameScreen.unitList.get(x).getX()==Integer.parseInt(temp[0]) && GameScreen.unitList.get(x).getY()==Integer.parseInt(temp[1])){
                            GameScreen.unitList.get(x).setHp(Integer.parseInt(temp[2]));
                        }
                    }
                }
                else if(msg.startsWith("oS")){
                    msg=msg.substring(2);
                    GameScreen.opponantScore=Integer.parseInt(msg);
                }else if(msg.equals("oOpponant RQ'd")){
                    new Thread(new SendThread(null,"i"+Main.playerName+"+")).start();
                    new Thread(new SendThread(null,"dc")).start();
                }
                
            } } catch (SocketException ex) {
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private static void printData(String in) throws Exception
   {
      System.out.println(in);
   }
public static String getMsg(String line) throws Exception
    {
    return line.substring(0, line.indexOf("<t>"));
}
private static long getTime(String line) throws Exception
    {
        String temp[]=new String(line).split("<t>");
        return Long.parseLong(temp[1]);
    }
    public RecieveThread(){
    }
    public static void shutdown(){
        d=false;
    }
    public static void start(){
        d=true;
    }
}