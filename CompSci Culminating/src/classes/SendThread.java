package classes;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendThread implements Runnable{

String r="";
InetAddress to=null;
DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
    public SendThread(DatagramPacket request,String r){
        if(request==null){
            request = new DatagramPacket(new byte[1024], 1024);
        }
        this.request=request;
        this.r=r;
        this.to=Main.server;
    }
    
    @Override
    public void run(){
        boolean d=false;
    try {
        while(d==false){
            if(Main.busy==false){
                //sending
                Main.busy=true;
                if(request.equals(null)){
                    request = new DatagramPacket(new byte[1024], 1024);
                }
                
                String Message = r + "<t>" + System.currentTimeMillis() + "\n";
                String m=r;
                request =new DatagramPacket(Message.getBytes(), Message.length(),to,Main.sport);
                Main.socket.send(request);
                //checking
                if(r.equals("p")){
                    Main.busy=false;
                            d=true;
                }else if(r.equals("dc")){
                    Main.busy=false;
                            d=true;
                }else{
                    Thread.sleep(100);
                System.out.println("Sent: "+r);
                
                d=true;
                /*do{
                    int y=Main.hist.size()-1;
                    if(!Main.hist.isEmpty()){
                        if(Main.hist.get(y).equals("g"+m)){
                            Main.busy=false;
                            d=true;
                            ad=true;
                            System.out.println("Packet was not lost!");
                            //Main.hist.clear();
                        }
                    }
                }while(ad==false);*/
            }
            if(d==false)Thread.sleep(200);
        }}
    } catch (InterruptedException ex) {} catch (IOException ex) {
        Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}