package classes;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class About extends BasicGameState{
     public About(int state){}
    public static Image aboutP;
    @Override
    public int getID() {return 4;}

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        aboutP=new Image("res/about.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        g.drawImage(Menu.back[Menu.backG],0,0);
        g.drawImage(aboutP, 100,100);
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
            sbg.enterState(3);
        }
    } 
}
