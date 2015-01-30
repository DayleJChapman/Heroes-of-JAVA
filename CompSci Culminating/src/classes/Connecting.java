package classes;
import java.io.InputStream;
import org.newdawn.slick.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.*;
public class Connecting extends BasicGameState{
    public Connecting(int state){}
    @Override
    public int getID() {return 1;}
    public long time;
    Animation load, sprite;
    String text="Looking for Opponent..."; long t=System.currentTimeMillis();
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        time = System.currentTimeMillis();
        Image [] loadanim = {
                                new Image("res/LoadGif/tmp-0.gif"),
                                new Image("res/LoadGif/tmp-1.gif"),
                                new Image("res/LoadGif/tmp-2.gif"),
                                new Image("res/LoadGif/tmp-3.gif"),
                                new Image("res/LoadGif/tmp-4.gif"),
                                new Image("res/LoadGif/tmp-5.gif"),
                                new Image("res/LoadGif/tmp-6.gif"),
                                new Image("res/LoadGif/tmp-7.gif"),
                                new Image("res/LoadGif/tmp-8.gif"),
                                new Image("res/LoadGif/tmp-9.gif"),
                                new Image("res/LoadGif/tmp-10.gif"),
                                new Image("res/LoadGif/tmp-11.gif"),
                                new Image("res/LoadGif/tmp-12.gif"),
                                new Image("res/LoadGif/tmp-13.gif"),
                                new Image("res/LoadGif/tmp-14.gif"),
                                new Image("res/LoadGif/tmp-15.gif"),
                                new Image("res/LoadGif/tmp-16.gif"),
                                new Image("res/LoadGif/tmp-17.gif"),
                                new Image("res/LoadGif/tmp-18.gif"),
                                new Image("res/LoadGif/tmp-19.gif"),
                                new Image("res/LoadGif/tmp-20.gif"),
                                new Image("res/LoadGif/tmp-21.gif"),
                                new Image("res/LoadGif/tmp-22.gif"),
                                new Image("res/LoadGif/tmp-23.gif"),
                                new Image("res/LoadGif/tmp-24.gif"),
                                new Image("res/LoadGif/tmp-25.gif"),
                                new Image("res/LoadGif/tmp-26.gif"),
                                new Image("res/LoadGif/tmp-27.gif"),
                                new Image("res/LoadGif/tmp-28.gif"),
                                new Image("res/LoadGif/tmp-29.gif"),
                                new Image("res/LoadGif/tmp-30.gif"),
                                new Image("res/LoadGif/tmp-31.gif"),
                                new Image("res/LoadGif/tmp-32.gif"),
                                new Image("res/LoadGif/tmp-33.gif"),
                                new Image("res/LoadGif/tmp-34.gif"),
                                new Image("res/LoadGif/tmp-35.gif"),
                                new Image("res/LoadGif/tmp-36.gif"),
                                new Image("res/LoadGif/tmp-37.gif"),
                                new Image("res/LoadGif/tmp-38.gif"),
                                new Image("res/LoadGif/tmp-39.gif"),
                                new Image("res/LoadGif/tmp-40.gif"),
                                new Image("res/LoadGif/tmp-41.gif"),
                                new Image("res/LoadGif/tmp-42.gif"),
                                new Image("res/LoadGif/tmp-43.gif"),
                                new Image("res/LoadGif/tmp-44.gif"),
                                new Image("res/LoadGif/tmp-45.gif"),
                                new Image("res/LoadGif/tmp-46.gif"),
                                new Image("res/LoadGif/tmp-47.gif"),
                                new Image("res/LoadGif/tmp-48.gif"),
                                new Image("res/LoadGif/tmp-49.gif"),
                                new Image("res/LoadGif/tmp-50.gif"),
                                new Image("res/LoadGif/tmp-51.gif"),
                                new Image("res/LoadGif/tmp-52.gif"),
                                new Image("res/LoadGif/tmp-53.gif"),
                                new Image("res/LoadGif/tmp-54.gif"),
                                new Image("res/LoadGif/tmp-55.gif"),
                                new Image("res/LoadGif/tmp-56.gif"),
                                new Image("res/LoadGif/tmp-57.gif"),
                                new Image("res/LoadGif/tmp-58.gif"),
                                new Image("res/LoadGif/tmp-59.gif"),
                                new Image("res/LoadGif/tmp-60.gif"),
                                new Image("res/LoadGif/tmp-61.gif"),
                                new Image("res/LoadGif/tmp-62.gif"),
                                new Image("res/LoadGif/tmp-63.gif"),
                                new Image("res/LoadGif/tmp-64.gif"),
                                new Image("res/LoadGif/tmp-65.gif"),
                                new Image("res/LoadGif/tmp-66.gif"),
                                new Image("res/LoadGif/tmp-67.gif"),
                                new Image("res/LoadGif/tmp-68.gif"),
                                new Image("res/LoadGif/tmp-69.gif"),
                                new Image("res/LoadGif/tmp-70.gif"),
                                new Image("res/LoadGif/tmp-71.gif"),
                                };
        int [] duration = {42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42};

        load = new Animation(loadanim, duration, false);
        sprite = load;
        }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        Main.width = 800;
        Main.height = 600;
        g.setColor(Color.white);
        //insert animation
        sprite.draw(144,44);
        long tb=500;
        if (System.currentTimeMillis()>t+tb){
            if(text.equals("Looking for Opponent..."))text="Looking for Opponent";
            else if(text.equals("Looking for Opponent"))text="Looking for Opponent.";
            else if(text.equals("Looking for Opponent."))text="Looking for Opponent..";
            else if(text.equals("Looking for Opponent.."))text="Looking for Opponent...";
            t=System.currentTimeMillis();
            
        }
        g.drawString(text, 300, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        sprite.update(i);
        if(Main.ig==true){
            sbg.enterState(2);
        }
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            sbg.enterState(0);
            pingThread.shutdown();
            new Thread(new SendThread(null,"dc")).start();
            System.out.println("Dc sent");
            System.out.println("Ping stops now!");
        }
    }
}