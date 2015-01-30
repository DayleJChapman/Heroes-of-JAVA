package classes;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Main extends StateBasedGame{   
    public static int port = 6001, sport=6002;
    public static DatagramSocket socket;
    public static InetAddress server;
    public static ArrayList<String> hist=new ArrayList<>();
    public static boolean busy=false, ig=false;
    public static int player, opponant, turn=1, width=800, height=600;
    public static final String name = "Heroes of JAVA!";
    public static int mainMenu = 0;
    public static int promptScreen = 6;
    public static int connecting = 1;
    public static int gameScreen = 2;
    public static int extras = 3;
    public static int about = 4;
    public static int leaderboards = 5;
    public static String playerName="";
    
    public Main(String name){
        super(name);
        this.addState(new Menu(mainMenu));
        this.addState(new PromptScreen(promptScreen));
        this.addState(new Connecting(connecting));
        this.addState(new GameScreen(gameScreen));
        this.addState(new Extras(extras));
        this.addState(new About(about));
        this.addState(new LeaderBoards(leaderboards));
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.enterState(mainMenu);
    }
      
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException{
         AppGameContainer appgc; 
         try{
            appgc = new AppGameContainer(new Main(name),width, height, /*Fullscreen*/false);
            socket = new DatagramSocket(port);
            server=InetAddress.getByName("Markooo-PC");//Name of Server
            //enter splash
            (new Thread(new RecieveThread())).start();
            (new Thread(new pingThread())).start();
            appgc.start();            
         }catch(SlickException e){
        }  
    } 
}