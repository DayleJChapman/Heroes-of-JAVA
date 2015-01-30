package Server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendThread implements Runnable{

String r="";
InetAddress to=null;
int port=0;
DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
    public SendThread(DatagramPacket request,String r, InetAddress to, int port){
        this.request=request;
        this.r=r;
        this.to=to;
        this.port=port;
    }
    
    @Override
    public void run(){
        boolean d=false;
    try {
        while(d==false){
            if(TicTacServer.busy==false){
                TicTacServer.busy=true;
                if(request==null){
                    request = new DatagramPacket(new byte[1024], 1024);
                }
                String Message = r + "<t>" + System.currentTimeMillis() + "\n";
                request =new DatagramPacket(Message.getBytes(), Message.length(),to,port);
                TicTacServer.socket.send(request);
                TicTacServer.busy=false;
                d=true;
                System.out.println("Sent: " + r + " To: " +  to);
            }
            if(d==false)Thread.sleep(150);
        }
    } catch (IOException ex) {TicTacServer.log.add("Error" + ex);
        Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) {TicTacServer.log.add("Error" + ex);
        Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}