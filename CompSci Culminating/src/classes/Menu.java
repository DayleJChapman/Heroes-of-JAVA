package classes;
import java.awt.image.BufferedImage;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Menu extends BasicGameState{
    public Menu(int state){}
    public Image playButton, optionsButton, exitButton, logo;
    public static String mouse="";
    public static Image[] back;
    public static int backG = 0;
    public static long lu=0;
    
    @Override
    public int getID() {return 0;}

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        back = new Image[]{
             new Image("res/backGif/tmp-0.gif"),
             new Image("res/backGif/tmp-1.gif"),
             new Image("res/backGif/tmp-2.gif"),
             new Image("res/backGif/tmp-3.gif"),
             new Image("res/backGif/tmp-4.gif"),
             new Image("res/backGif/tmp-5.gif"),
             new Image("res/backGif/tmp-6.gif"),
             new Image("res/backGif/tmp-7.gif"),
             new Image("res/backGif/tmp-8.gif"),
             new Image("res/backGif/tmp-9.gif"),
             new Image("res/backGif/tmp-10.gif"),
             new Image("res/backGif/tmp-11.gif"),
             new Image("res/backGif/tmp-12.gif"),
             new Image("res/backGif/tmp-13.gif"),
             new Image("res/backGif/tmp-14.gif"),
             new Image("res/backGif/tmp-15.gif"),
             new Image("res/backGif/tmp-16.gif"),
             new Image("res/backGif/tmp-17.gif"),
             new Image("res/backGif/tmp-18.gif"),
             new Image("res/backGif/tmp-19.gif"),
             new Image("res/backGif/tmp-20.gif"),
             new Image("res/backGif/tmp-21.gif"),
             new Image("res/backGif/tmp-22.gif"),
             new Image("res/backGif/tmp-23.gif"),
             new Image("res/backGif/tmp-24.gif"),
             new Image("res/backGif/tmp-25.gif"),
             new Image("res/backGif/tmp-26.gif"),
             new Image("res/backGif/tmp-27.gif"),
             new Image("res/backGif/tmp-28.gif"),
             new Image("res/backGif/tmp-29.gif")
                     };
            playButton = new Image("res/playButton.png");
            optionsButton = new Image("res/optionsButton.png");
            exitButton = new Image("res/exitButton.png");
            logo = new Image("res/logo.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        g.drawImage(Menu.back[Menu.backG],0,0);
        g.drawImage(playButton, 300,300);
        g.drawImage(optionsButton, 300, 375);
        g.drawImage(exitButton, 300, 450);
        g.drawImage(logo, 130, 20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input in = gc.getInput();
        if(System.currentTimeMillis()>Menu.lu+66){
                //update bckground
                if(Menu.backG==29)Menu.backG=0;
                else Menu.backG++;
                Menu.lu=System.currentTimeMillis();
            }
        if(in.isControlPressed(2)){
            System.out.println("IN");
        }
        int xPos=Mouse.getX(), yPos=Mouse.getY();
        mouse = "X: "+xPos+" Y: "+yPos;
        if(in.isMousePressed(0)){
            if(300<=xPos && 500>=xPos && 250<=yPos && 300>=yPos){
                sbg.enterState(6);
            }
            if(300<=xPos && 500>=xPos && 175<=yPos && 225>=yPos){
                sbg.enterState(3);
            }
            if(300<=xPos && 500>=xPos && 100<=yPos && 150>=yPos){
                System.exit(0);
            }
        }
        if(in.isKeyDown(Input.KEY_ENTER)){
            sbg.enterState(6);
        }
    }
}