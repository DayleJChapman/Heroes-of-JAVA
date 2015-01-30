package classes;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Splash extends BasicGameState {
    Image splash;
    private int elapsedTime;
    private final int DELAY=3000;
    public Splash(int State){
        
    }
    @Override
    public void init(GameContainer gc, StateBasedGame s)throws SlickException{
        splash=new Image("res/splash.jpg");
    }
    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g)throws SlickException{
        g.drawImage(splash, 0, 0);
    }
    @Override
    public void update(GameContainer gc, StateBasedGame s, int delta)throws SlickException{
        elapsedTime+=delta;
        if(elapsedTime>=DELAY)s.enterState(0);
    }
    @Override
    public int getID(){
        return 7;
    }
    
}