package classes;
import static classes.Menu.mouse;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class Extras extends BasicGameState{
    public Extras(int state){}
    public Image background, logo, about, lb, back;
    @Override
    public int getID() {return 3;}

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        logo = new Image("res/logo.png");
        about = new Image("res/aboutButton.png");
        lb = new Image("res/lbButton.png");
        back = new Image("res/backButton.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        g.drawImage(Menu.back[Menu.backG],0,0);
        g.drawImage(logo, 130, 20);
        g.setColor(Color.lightGray);
        g.drawImage(about, 300, 300);
        g.drawImage(lb, 300, 375);
        g.drawImage(back, 300, 450);
        
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
        int xPos=Mouse.getX(), yPos=Mouse.getY();
        mouse = "X: "+xPos+" Y: "+yPos;
        if(in.isMousePressed(0)){
            if(300<=xPos && 500>=xPos && 250<=yPos && 300>=yPos){
                sbg.enterState(4);
            }
            if(300<=xPos && 500>=xPos && 175<=yPos && 225>=yPos){
                sbg.enterState(5);//LEADERBOARDS
                new Thread(new SendThread(null,"retHS")).start();
            }
            if(300<=xPos && 500>=xPos && 100<=yPos && 150>=yPos){
                sbg.enterState(0);
            }
        }
    }
}