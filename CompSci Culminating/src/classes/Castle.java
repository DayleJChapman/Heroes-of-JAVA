package classes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.*;
public class Castle{
    private int x;
    private int y;
    private int owner = 0;
    private Image sprite;
    
    public Castle(int newX, int newY)throws SlickException{
        x = newX;
        y = newY;
        sprite = new Image("res/auraNeutral.png");
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getID(){
        return owner;
    }
    
    public void setX(int newX)
    {
        x=newX;
    }
    
    public void setY(int newY)
    {
        y=newY;
    }
    
    public Image getSprite(){
        return sprite;
    }
    
    public void capture(int newOwner)throws SlickException{
        owner = newOwner;
        if(owner == Main.player){
           sprite = new Image("res/auraPlayer.png"); 
        }else if(owner == Main.opponant){
            sprite = new Image("res/auraEnemy.png");
        }
    }
}