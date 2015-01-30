package classes;
import org.newdawn.slick.*;
public class Unit
{
    
    private int x;
    private int y;
    private int baseHp;
    private int currentHp;
    private int movement;
    private int attack;
    private int defence;
    private int magic;
    private int resist;
    private int ownerID;
    private UnitClass unitClass;
    private Image sprite;
    private int actions;
    private Boolean doneTurn=false;
    private int range;
    private String name;

    public Unit(int newX, int newY, Image newSprite, int ID, UnitClass newClass)
    { 
        unitClass=newClass;
        x=newX;
        y=newY;
        sprite = newSprite;
        ownerID = ID;
        
        baseHp = unitClass.getHp();
        currentHp = unitClass.getHp();
        movement = unitClass.getMove();
        attack = unitClass.getAtk();
        defence = unitClass.getDef();
        magic = unitClass.getMagic();
        resist = unitClass.getResist();
        range = unitClass.getRange();
        name = unitClass.getName();
        
        BoardMatrix.board[x][y]=ID;
        
    }
    
    public void setPos(){
        BoardMatrix.board[x][y]=ownerID;
    }
    
    public Image getSprite(){
        return sprite;
    }
    
    public int getID(){
        return ownerID;
    }
   
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setX(int newX)
    {
        x=newX;
    }
    
    public void setY(int newY)
    {
        y=newY;
    }
    
    public void setHp(int newHp)
    {
        currentHp = newHp;
    }
    
    public int getHp(){
         return currentHp;
    }
    
    public int getMove(){
         return movement;
    }
    
    public int getAtk(){
         return attack;
    }
    
    public int getDef(){
         return defence;
    }
    
    public int getMagic(){
         return magic;
    }
    
    public int getResist(){
         return resist;
    }
    
    public int getBaseHp(){
        return baseHp;
    }
    
    public int getActions(){
        return actions;
    }
    
    public void takeAction(){
        actions++;
    }
    
    public void endTurn(){
        actions=0;
    }
    
    public UnitClass getUnitClass(){
        return unitClass;
    }
    
    public int getRange(){
        return range;
    }
    
    public String getName(){
        return name;
    }
}