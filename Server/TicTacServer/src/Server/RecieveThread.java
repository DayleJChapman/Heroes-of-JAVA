package Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RecieveThread implements Runnable{
    ArrayList<String> his=new ArrayList<String>();
    ArrayList<InetAddress> adr=new ArrayList<InetAddress>();
    public void run() {
        try {
            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                TicTacServer.socket.receive(request);
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
                if(true)TicTacServer.log.add(String.valueOf(clientHost+":" + msg));
                /*if(!msg.equals("p") && !msg.equals("dc")){
                    new Thread(new SendThread(request,"g" + msg,request.getAddress(),request.getPort())).start();
                    his.add(msg);
                    adr.add(request.getAddress());
                }*/
                Thread.sleep(250);
                if (msg.equals("connect")){
                    assignPlayer(request);
                }else if(msg.startsWith("t")){
                    InetAddress to = null;
                    for (int c=0; c<TicTacServer.player1.size(); c++){
                        if(TicTacServer.player1.size()>c)
                        {if(TicTacServer.player1.get(c).equals(clientHost) && TicTacServer.ltplayer2.get(c)!=(long)0){
                            to=TicTacServer.player2.get(c);
                        }
                        }else if(TicTacServer.player2.size()>=TicTacServer.player1.size()){
                            if(TicTacServer.player2.get(c).equals(clientHost)&& TicTacServer.ltplayer1.get(c)!=(long)0){
                                to=TicTacServer.player1.get(c);
                            }
                        }
                    }
                    new Thread(new SendThread(request,"o"+msg.substring(1),to,request.getPort())).start();
                }else if("p".equals(msg)){
                    Date date = new Date();
                    String STR=String.format("Current Date/Time : %tc", date );
                    System.out.printf(STR);
                    for(int c=0; c<TicTacServer.player1.size(); c++){
                        if(!TicTacServer.player1.get(c).equals(InetAddress.getByName("127.0.0.1")))if(TicTacServer.player1.get(c).equals(request.getAddress())){
                            TicTacServer.ltplayer1.set(c, System.currentTimeMillis());
                        }
                    }
                    for(int c=0; c<TicTacServer.player2.size(); c++){
                        if(!TicTacServer.player2.get(c).equals(InetAddress.getByName("127.0.0.1")))if(TicTacServer.player2.get(c).equals(request.getAddress())){
                            TicTacServer.ltplayer2.set(c, System.currentTimeMillis());
                        }
                    }
                }else if(msg.startsWith("i")){
                    HighScore(msg.substring(1));
                }
                else if(msg.equals("retHS")){
                    new Thread(new SendThread(request,retHS(),request.getAddress(),request.getPort())).start();
                }
                else if(msg.equals("dc")){
                    for(int c=0; c<TicTacServer.player1.size(); c++){
                        if(request.getAddress().equals(TicTacServer.player1.get(c))){
                            TicTacServer.player1.set(c,InetAddress.getByName("127.0.0.1"));
                            TicTacServer.ltplayer1.set(c,(long)0);
                            TicTacServer.igplayer1.set(c,false);
                            System.out.println("Player 1 dcd");
                        }
                    }
                    for(int c=0; c<TicTacServer.player2.size(); c++){
                        if(request.getAddress().equals(TicTacServer.player2.get(c))){
                            TicTacServer.player2.set(c,InetAddress.getByName("127.0.0.1"));
                            TicTacServer.ltplayer2.set(c,(long)0);
                            TicTacServer.igplayer2.set(c,false);
                            System.out.println("Player 2 dcd");
                        }
                    }
                }
                
            } } catch (SocketException ex) {TicTacServer.log.add("Error" + ex);
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {TicTacServer.log.add("Error" + ex);
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {TicTacServer.log.add("Error" + ex);
            Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private static void printData(String in) throws Exception
   {
      TicTacServer.log.add(String.valueOf(in));
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
private static void assignPlayer(DatagramPacket request) throws Exception
   {
       if((TicTacServer.player1.size() < TicTacServer.player2.size()) || (TicTacServer.player1.size() == TicTacServer.player2.size())){
           if(request.getAddress().equals(InetAddress.getByName("127.0.0.1")))System.err.print("Oops");
           TicTacServer.player1.add(request.getAddress());
           TicTacServer.ltplayer1.add(System.currentTimeMillis());
           TicTacServer.igplayer1.add(false);
       }else{
           TicTacServer.player2.add(request.getAddress());
           TicTacServer.ltplayer2.add(System.currentTimeMillis());
           TicTacServer.igplayer2.add(false);
           if(!TicTacServer.player2.get(TicTacServer.player2.size()-1).equals(TicTacServer.player1.get(TicTacServer.player1.size()-1)) && !TicTacServer.player1.get(TicTacServer.ltplayer1.size()-1).equals("127.0.0.1") && !TicTacServer.player2.get(TicTacServer.ltplayer2.size()-1).equals("127.0.0.1") && TicTacServer.ltplayer1.get(TicTacServer.ltplayer1.size()-1)>System.currentTimeMillis()-Leaver.timeout && TicTacServer.ltplayer2.get(TicTacServer.ltplayer1.size()-1)>System.currentTimeMillis()-Leaver.timeout){
               new Thread(new SendThread(request,"1",TicTacServer.player1.get(TicTacServer.ltplayer1.size()-1),request.getPort())).start();
               new Thread(new SendThread(request,"2",TicTacServer.player2.get(TicTacServer.ltplayer1.size()-1),request.getPort())).start();
           }
       }
       
   }

private static String retHS() throws FileNotFoundException{
    ArrayList<String> name=new ArrayList();
    ArrayList<Integer> wins=new ArrayList();
    ArrayList<Integer> loss=new ArrayList();
    ArrayList<Integer> wPercent=new ArrayList();
    File f=new File("hs.txt");
    //Open High Score File
    String ret="";
    if (f.exists()){
    Scanner readFile = new Scanner(f);
       while(readFile.hasNextLine()){
           String l=readFile.nextLine();
           ret+=l+"<nl>";
       }
     readFile.close();
    }
    if (ret.length()==0)ret="NoHS";
    else ret=ret.substring(0, ret.length()-4);
    return ret;
}
private static void HighScore(String in) throws FileNotFoundException{
    ArrayList<String> name=new ArrayList();
    ArrayList<Integer> wins=new ArrayList();
    ArrayList<Integer> loss=new ArrayList();
    ArrayList<Integer> wPercent=new ArrayList();
    File f=new File("hs.txt");
    //Open High Score File
    if (f.exists()){
    Scanner readFile = new Scanner(f);
       while(readFile.hasNextLine()){
           String l=readFile.nextLine();
           name.add(l.substring(0, l.indexOf("<w>")));
           wins.add(Integer.parseInt(l.substring(l.indexOf("<w>")+3, l.indexOf("<l>"))));
           loss.add(Integer.parseInt(l.substring(l.indexOf("<l>")+3, l.indexOf("<wp>"))));
           wPercent.add(0);
       }
     readFile.close();
    }
    //Add New stuff
    //modify
    boolean a=false;
    for(int c=0; c<name.size(); c++){
        if(name.get(c).equals(in.substring(0, in.length()-1))){
            if(in.substring(in.length()-1).equals("+")){
                wins.set(c, wins.get(c)+1);
                a=true;
            }
            if(in.substring(in.length()-1).equals("-")){
                loss.set(c, loss.get(c)+1);
                a=true;
            }
        }
    }
    //add whole new player
    if(a==false){
        name.add(in.substring(0,in.length()-1));
        wins.add(0);
        loss.add(0);
        wPercent.add(0);
        if(in.substring(in.length()-1).equals("+")){
                wins.set(wins.size()-1, 1);
            }
            if(in.substring(in.length()-1).equals("-")){
                loss.set(loss.size()-1, 1);
            }
    }
    //Calculate all wps
    System.out.println();
    for(int c=0; c<name.size(); c++){
       int total=wins.get(c)+loss.get(c);
       wPercent.set(c, (int)(((double)wins.get(c)/(double)total)*(double)100));
    }
    //Sorting
    insertionSort4(name,wins, loss, wPercent);
    //Save file
        try{
            BufferedWriter WriteFile = new BufferedWriter(new FileWriter("hs.txt"));
            for(int c=0; c<name.size(); c++){
                String line=name.get(c)+"<w>"+wins.get(c)+"<l>"+loss.get(c)+"<wp>"+wPercent.get(c);
                System.out.println(line);
                WriteFile.write(line);
                WriteFile.newLine();
            }
            WriteFile.close();
       }catch(IOException e){TicTacServer.log.add("Error " + e);}
}
public static void insertionSort4( ArrayList<String> name,ArrayList<Integer> win,ArrayList<Integer> loss, ArrayList<Integer> wPercent){
         int j;
         for (int i = 0; i < name.size()-1; i++){
           j=i;
           if(wPercent.get(j).compareTo(wPercent.get(j+1))==0){ //same wPercent
               if(j>=0 && win.get(j).compareTo(win.get(j+1))==0){ //same Win
                   while(j>=0 && name.get(j).compareTo(name.get(j+1))>0){ //Ascending Name
                       Collections.swap(name, j, j+1);
                       Collections.swap(win, j, j+1);
                       Collections.swap(loss, j, j+1);
                       Collections.swap(wPercent, j, j+1);
                        j--;
                    }
               }else{
                    while(j>=0 && win.get(j).compareTo(win.get(j+1))<0){ //Desending Win
                       Collections.swap(name, j, j+1);
                       Collections.swap(win, j, j+1);
                       Collections.swap(loss, j, j+1);
                       Collections.swap(wPercent, j, j+1);
                        j--;
                    }
               }
               }
           else{
                while(j>=0 && wPercent.get(j).compareTo(wPercent.get(j+1))<0){ //Descending wPercent
                    Collections.swap(name, j, j+1);
                    Collections.swap(win, j, j+1);
                    Collections.swap(loss, j, j+1);
                    Collections.swap(wPercent, j, j+1);
                    j--;
                }
           }
        }
}
    public RecieveThread(){
    }
}