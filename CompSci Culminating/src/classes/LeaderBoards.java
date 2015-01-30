package classes;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class LeaderBoards extends BasicGameState{
    private static ArrayList<String> name,win,loss,wp;
    private static int number=0;
    private static Image hs;
     public LeaderBoards(int state){}

    @Override
    public int getID() {return 5;}

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hs=new Image("res/hs.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        g.drawImage(Menu.back[Menu.backG],0,0);
        g.setColor(Color.yellow);
        g.drawImage(hs, 100, 100);
        g.drawString("PLAYER", 140, 120);
        g.drawString("WINS", 320, 120);
        g.drawString("LOSES", 420, 120);
        g.drawString("WIN RATE",540, 120);
        //Name Starts:140
        g.setColor(Color.pink);
        int stepDown=30;
        int X=140, Y=150;
        for(int c=0; c<number;c++){
            g.drawString(name.get(c), X,Y);
            X+=180;
            g.drawString(win.get(c), X,Y);
            X+=100;
            g.drawString(loss.get(c), X,Y);
            X+=120;
            g.drawString(wp.get(c), X,Y);
            Y+=stepDown;
            X=140;
        }
    }

    public static void hs(String msg){
        System.out.println("Hs recieved!");
        name= new ArrayList<String>();
        win= new ArrayList<String>();
        loss= new ArrayList<String>();
        wp= new ArrayList<String>();
        String[] temp;
        temp=msg.split("<nl>");
        if(temp.length>10){
            for(int c=0; c<10; c++){
            number++;
            name.add(temp[c].substring(0, temp[c].indexOf("<w>")));
            win.add(temp[c].substring(temp[c].indexOf("<w>")+3, temp[c].indexOf("<l>")));
            loss.add(temp[c].substring(temp[c].indexOf("<l>")+3, temp[c].indexOf("<wp>")));
            wp.add(temp[c].substring(temp[c].indexOf("<wp>")+4));
        }
        }else{
            for(int c=0; c<temp.length; c++){
                number++;
                name.add(temp[c].substring(0, temp[c].indexOf("<w>")));
                win.add(temp[c].substring(temp[c].indexOf("<w>")+3, temp[c].indexOf("<l>")));
                loss.add(temp[c].substring(temp[c].indexOf("<l>")+3, temp[c].indexOf("<wp>")));
                wp.add(temp[c].substring(temp[c].indexOf("<wp>")+4));
            }
        }
    }
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    Input in = gc.getInput();
    if(System.currentTimeMillis()>Menu.lu+66){
                //update bckground
                if(Menu.backG==29)Menu.backG=0;
                else Menu.backG++;
                Menu.lu=System.currentTimeMillis();
            }
        if(in.isMousePressed(0) || in.isKeyDown(in.KEY_E) || in.isKeyDown(in.KEY_R) || in.isKeyDown(in.KEY_W) || in.isKeyDown(in.KEY_A) || in.isKeyDown(in.KEY_S) || in.isKeyDown(in.KEY_D)){
            number = 0;
                name= new ArrayList<String>();
                win= new ArrayList<String>();
                loss= new ArrayList<String>();
                wp= new ArrayList<String>();
            sbg.enterState(3);
        }
    }   
}