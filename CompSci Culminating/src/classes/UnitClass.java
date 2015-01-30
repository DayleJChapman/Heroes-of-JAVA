package classes;
public class UnitClass {
    private int baseHp;
    private int movement;
    private int attack;
    private int defence;
    private int magic;
    private int resist;
    private boolean mage;
    private int range;
    private String name;
    
    public UnitClass(int hp, int move, int atk, int def, int mag, int res, boolean magical, int newRange, String newName){
        baseHp = hp;
        movement = move;
        attack = atk;
        defence = def;
        magic = mag;
        resist = res;
        mage = magical;
        range = newRange;
        name = newName;
    }
    
    public int getHp(){
         return baseHp;
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
    
    public boolean isMage(){
         return mage;
    }
    
    public int getRange(){
        return range;
    }
    
    public String getName(){
        return name;
    }
}