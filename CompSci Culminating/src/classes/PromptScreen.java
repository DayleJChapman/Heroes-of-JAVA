package classes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.*;
public class PromptScreen extends BasicGameState{
    public PromptScreen(int State){}
    String name ="";
    long time = System.currentTimeMillis();
    boolean moveDelay;
    
    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gc.setShowFPS(false);
        g.drawString("UserName: ",0,0);
        g.drawString(name, 80, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if(time+150<=System.currentTimeMillis()){moveDelay = true;}
        if(moveDelay==true){
        if(input.isKeyDown(Input.KEY_A)){
            name+="A";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_B)){
            name+="B";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_C)){
            name+="C";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_D)){
            name+="D";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_E)){
            name+="E";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_F)){
            name+="F";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_G)){
            name+="G";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_H)){
            name+="H";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_I)){
            name+="I";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_J)){
            name+="J";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_K)){
            name+="K";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_L)){
            name+="L";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_M)){
            name+="M";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_N)){
            name+="N";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_O)){
            name+="O";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_P)){
            name+="P";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_Q)){
            name+="Q";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_R)){
            name+="R";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_S)){
            name+="S";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_T)){
            name+="T";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_U)){
            name+="U";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_V)){
            name+="V";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_W)){
            name+="W";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_X)){
            name+="X";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_Y)){
            name+="Y";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_Z)){
            name+="Z";
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_BACK)&&name.length()>0){
            name = name.substring(0, name.length()-1);
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_ENTER)){
            Main.playerName = name;
            new Thread(new SendThread(null,"connect")).start();
            System.out.println("connect sent");
            pingThread.start();
            System.out.println("Ping starts now!");
            sbg.enterState(1);
        }
        }
    }
}
