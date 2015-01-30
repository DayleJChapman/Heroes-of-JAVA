package classes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.*;
public class GameScreen extends BasicGameState{   
    //TODO (v1.0.0): 
    //  - #MOVING MULTIPLE CHARACTERS ON SCREEN
    //  - #MAKE BOARD MATRIX 
    //  - #RENDER UNIT LOCATIONS BASED ON BOARD MATRIX
    //  - #UPDATE MATRIX WHEN UNITS MOVE
    //  - #RENDER OPPONENTS UNITS
    //  - #MAKE OPPONENTS UNITS UNCONTROLABLE
    //  - #MAKE UNIT CLASS
    //  - #FIX MOVEMENT CALCULATIONS
    //  - #DO ATTACK CALCULATIONS
    //  - #SEND UPDATED DATA TO SREVER
    //  - #UPDATE SCREEN BASED ON DATA RECIEVED FROM SERVER
    //  - #ADD CAPTURE OBJECTIVES TO MAP
    //  - #MAKE WIN/LOSS CONDITIONS
    //  - #PROMPT USER FOR USERNAME
    //  - #SEND WINS AND LOSSES TO SERVER
    //  - #MAKE LEADERBOARD WORK AS INTENDED
    //  - #FIX BUGS WITH MOVEMENT
    //  - #ALLOW PLAYERS TO CHOSE ATTACK TARGET
    //  - #SHOW UNIT HP UNDER UNITS
    //  - #SHOW UNIT STATS WHEN 'Q' IS PRESSED OVER A UNIT
    //  - #MAKE ARCHER CLASS
    //  - #MAKE MAGE CLASS RANGED
    //  - #ADD LARGER MAP AND SCROLLING SCREEN
    //  - MAKE SURE SPRITES HAVE IMAGES THAT ARE EASY TO TELL APART
    //  - ADD MORE UNIT CLASSES
    //  - #ADD MORE INFO TO ABOUT
    //  - #UPDATE ALL PLACEHOLDER IMAGES/TEXT
    //  - FIX MOVEMENT BUG (TRACK CURSOR LOCATION IN MAP)
    public GameScreen(int state){}
    Image map, cursor, mageSprite1, grass, mageSprite2, soldierSprite1, soldierSprite2, horseSprite1, horseSprite2, hp100, hp75, hp50, hp25;
    long time = System.currentTimeMillis();
    boolean moveDelay = true, showMenu = false, isMoving = false, keyDelay = false, isAttacking=false, showStats=false;
    String move = "MOVE", hp, atk, def, mag, res, id, movement, baseHp, unitClass;
    static ArrayList<Unit> unitList = new ArrayList<>(); 
    static ArrayList<Castle> castleList = new ArrayList<>(); 
    Unit unitTemp, oppTemp, unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8, unit9, unit10, unit11, unit12;
    static int cursorX = 2, cursorY = 2, newX = 0, newY = 0, oldX, oldY, distance=0, pos, menuSelectorPos=0, playerUnits, opponantUnits, playerScore=0, opponantScore=0, screenX=0, screenY=0, globalX=2, globalY=2;
    
    @Override
    public int getID() {return 2;}

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setShowFPS(false);
        BoardMatrix.init();
        unitList.clear();
        castleList.clear();
        cursor = new Image("res/cursor.png");
        mageSprite1 = new Image("res/unitMage2.png");
        mageSprite2 = new Image("res/unitMage3.png");
        soldierSprite1 = new Image("res/unitSoldier1.png");
        soldierSprite2 = new Image("res/unitSoldier3.png");
        horseSprite1 = new Image("res/unitHorse1.png");
        horseSprite2 = new Image("res/unitHorse3.png");
        hp100 = new Image("res/hp100.png");
        hp75 = new Image("res/hp75.png");
        hp50 = new Image("res/hp50.png");
        hp25 = new Image("res/hp25.png");
        map = new Image("res/map.png");
        
        //UnitClass(HP, MOVEMENT, ATK, DEF, MAGIC, RESIST, IS MAGE?, RANGE, NAME)
        UnitClass soldier = new UnitClass(10,6,5,2,1,2,false,1, "Soldier");
        UnitClass mage = new UnitClass(6,1,0,1,7,5,true,2, "Mage");
        UnitClass knight = new UnitClass(14,6,4,3,0,1,false,1,"Knight");
        //UnitClass uber = new UnitClass(9999,9999,9999,9999,9999,9999,false,9999,"uber");
        UnitClass archer = new UnitClass(8,5,3,0,3,3,false,2,"Archer");
        
        unit1=new Unit(0,2,horseSprite1,1,knight);
        unitList.add(unit1);
        unit2=new Unit(0,8,horseSprite1,1,knight);
        unitList.add(unit2);
        unit3=new Unit(1,3,mageSprite1,1,mage);
        unitList.add(unit3);
        unit4=new Unit(0,5,soldierSprite1,1,soldier);
        unitList.add(unit4);
        unit5=new Unit(2,5,soldierSprite1,1,soldier);
        unitList.add(unit5);
        unit6=new Unit(1,7,mageSprite1,1,mage);
        unitList.add(unit6);
        
        unit7=new Unit(16,2,horseSprite2,2,knight);
        unitList.add(unit7);
        unit8=new Unit(16,8,horseSprite2,2,knight);
        unitList.add(unit8);
        unit9=new Unit(15,3,mageSprite2,2,mage);
        unitList.add(unit9);
        unit10=new Unit(16,5,soldierSprite2,2,soldier);
        unitList.add(unit10);
        unit11=new Unit(14,5,soldierSprite2,2,soldier);
        unitList.add(unit11);
        unit12=new Unit(15,7,mageSprite2,2,mage);
        unitList.add(unit12);
        
        
        Castle castle1 = new Castle(5,1);
        castleList.add(castle1);
        Castle castle2 = new Castle(8,6);
        castleList.add(castle2);
        Castle castle3 = new Castle(5,8);
        castleList.add(castle3);
        Castle castle4 = new Castle(11,4);
        castleList.add(castle4);
        Castle castle5 = new Castle(11,11);
        castleList.add(castle5);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.drawImage(map, screenX*100, screenY*100);
        for(int x = 0; x < castleList.size(); x++){
            g.drawImage(castleList.get(x).getSprite(),castleList.get(x).getX()*100,castleList.get(x).getY()*100);
        }
        g.drawImage(cursor,cursorX*100,cursorY*100);
        for(int x = 0; x < unitList.size(); x++){
            g.drawImage(unitList.get(x).getSprite(),unitList.get(x).getX()*100,unitList.get(x).getY()*100);
            if(unitList.get(x).getHp()==unitList.get(x).getBaseHp()){
                g.drawImage(hp100, unitList.get(x).getX()*100,unitList.get(x).getY()*100);
            }else if(((double)unitList.get(x).getHp()/(double)unitList.get(x).getBaseHp())>0.75){
                g.drawImage(hp75, unitList.get(x).getX()*100,unitList.get(x).getY()*100);
            }else if(((double)unitList.get(x).getHp()/(double)unitList.get(x).getBaseHp())>0.5){
                g.drawImage(hp50, unitList.get(x).getX()*100,unitList.get(x).getY()*100);
            }else{
                g.drawImage(hp25, unitList.get(x).getX()*100,unitList.get(x).getY()*100);
            }
        }
        g.setColor(Color.white);
        g.drawString("Your Score: "+playerScore, 0,0);
        g.drawString("Opponant Score: "+opponantScore, 0, 15);
        if(showMenu == true&&(cursorX>=6&&cursorY>=4)){
            g.setColor(Color.lightGray);
            g.fillRect(5, 345, 140, 250);
            g.setColor(Color.white);
            g.drawRect(10, 350+menuSelectorPos*60, 130, 60);
            g.setColor(Color.black);
            g.drawString(move, 20, 375);
            g.drawString("ATTACK", 20, 435);
            g.drawString("END TURN", 20, 495);
            g.drawString("BACK", 20, 555);
        }else if(showMenu == true){            
            g.setColor(Color.lightGray);
            g.fillRect(655, 345, 140, 250);
            g.setColor(Color.white);
            g.drawRect(660, 350+menuSelectorPos*60, 130, 60);
            g.setColor(Color.black);
            g.drawString(move, 670, 375);
            g.drawString("ATTACK", 670, 435);
            g.drawString("END TURN", 670, 495);
            g.drawString("BACK", 670, 555);
        }  
        if(showStats==true){
            g.setColor(Color.lightGray);
            g.fillRect((cursorX*100)+100, cursorY*100, 150, 140);
            g.setColor(Color.black);
            g.drawString("CLASS: "+unitClass,(cursorX*100)+105 , cursorY*100+5);
            g.drawString("HP: "+hp+"/"+baseHp,(cursorX*100)+105 , cursorY*100+20);
            g.drawString("MOVE: "+movement,(cursorX*100)+105 , cursorY*100+35);
            g.drawString("ATTACK: "+atk,(cursorX*100)+105 , cursorY*100+50);
            g.drawString("DEFENCE: "+def,(cursorX*100)+105 , cursorY*100+65);
            g.drawString("MAGIC: "+mag,(cursorX*100)+105 , cursorY*100+80);
            g.drawString("RESISTANCE: "+res,(cursorX*100)+105 , cursorY*100+95);
            g.drawString("OWNER: PLAYER "+id,(cursorX*100)+105 , cursorY*100+110);
        }
    }
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    Input input = gc.getInput();
        if(time+150<=System.currentTimeMillis()&&showStats==false){moveDelay = true;}
        if((input.isKeyDown(Input.KEY_W)||input.isControlPressed(2)) && cursorY-1>-1 && moveDelay == true){
            if(showMenu==false){
                if(cursorY-1>0||(screenY==0&&cursorY!=0)){
                    if((isMoving==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) <= unitTemp.getRange())){
                    newY--;
                    distance+=moveMod(globalY, globalY-1, unitTemp.getY());
                    if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
                }else if((isMoving==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) > unitTemp.getRange())){
                    cursor = new Image("res/cursorGrey.png");
                    newY--;
                    distance+=moveMod(globalY, globalY-1, unitTemp.getY());
                }
                cursorY-=1;
                globalY-=1;
                }else if(screenY>-12){
                    if((isMoving==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) <= unitTemp.getRange())){
                        newY--;
                        distance+=moveMod(globalY, globalY-1, unitTemp.getY());
                        if(isAttacking==true){
                            cursor = new Image("res/rcursor.png");
                        }else{
                            cursor = new Image("res/cursorMove.png");
                        }
                    }else if((isMoving==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY-1, unitTemp.getY()) > unitTemp.getRange())){
                        cursor = new Image("res/cursorGrey.png");
                        newY--;
                        distance+=moveMod(globalY, globalY-1, unitTemp.getY());
                    }
                    for(int x=0; x < unitList.size(); x++){
                        unitList.get(x).setY(unitList.get(x).getY()+1);
                    }
                    for(int x=0; x < castleList.size(); x++){
                        castleList.get(x).setY(castleList.get(x).getY()+1);
                    }
                    screenY+=1;
                    globalY-=1;
                }
            }else if(menuSelectorPos>0){
                menuSelectorPos--;
            }
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if((input.isKeyDown(Input.KEY_S)||input.isControlPressed(3)) && cursorY+1<6 && moveDelay == true){
            if(showMenu==false){
                if(cursorY+1<5||(screenY==-7&&cursorY!=-6)){
                    if((isMoving==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) <= unitTemp.getRange())){
                    newY++;
                    distance+=moveMod(globalY, globalY+1, unitTemp.getY());
                    if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
                }else if((isMoving==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) > unitTemp.getRange())){
                    cursor = new Image("res/cursorGrey.png");
                    newY++;
                    distance+=moveMod(globalY, globalY+1, unitTemp.getY());
                }
                cursorY+=1;
                globalY+=1;
                }else if(screenX<15
                        ){
                    if((isMoving==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) <= unitTemp.getRange())){
                    newY++;
                    distance+=moveMod(globalY, globalY+1, unitTemp.getY());
                    if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
                }else if((isMoving==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalY, globalY+1, unitTemp.getY()) > unitTemp.getRange())){
                    cursor = new Image("res/cursorGrey.png");
                    newY++;
                    distance+=moveMod(globalY, globalY+1, unitTemp.getY());
                }
                screenY-=1;
                globalY+=1;
                for(int x=0; x < unitList.size(); x++){
                        unitList.get(x).setY(unitList.get(x).getY()-1);
                    }
                    for(int x=0; x < castleList.size(); x++){
                        castleList.get(x).setY(castleList.get(x).getY()-1);
                    }
                }
            }else if(menuSelectorPos<3){
                menuSelectorPos++;
            }moveDelay = false;
            time = System.currentTimeMillis();
        }
        if((input.isKeyDown(Input.KEY_A)||input.isControlPressed(0)) && cursorX-1>-1 && moveDelay == true&&showMenu==false){
            if(cursorX-1>0||(screenX==0&&cursorX!=0)){
                 if((isMoving==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) <= unitTemp.getRange())){
                newX--;
                distance+=moveMod(globalX, globalX-1, unitTemp.getX());
                if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
            }else if((isMoving==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) > unitTemp.getRange())){
                cursor = new Image("res/cursorGrey.png");
                newX--;
                distance+=moveMod(globalX, globalX-1, unitTemp.getX());
            }
            cursorX-=1;
            globalX-=1;
            }else if(screenX>-16){
                 if((isMoving==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) <= unitTemp.getRange())){
                newX--;
                distance+=moveMod(globalX, globalX-1, unitTemp.getX());
                if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
            }else if((isMoving==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX-1, unitTemp.getX()) > unitTemp.getRange())){
                cursor = new Image("res/cursorGrey.png");
                newX--;
                distance+=moveMod(globalX, globalX-1, unitTemp.getX());
            }
            screenX+=1;
            globalX-=1;
            for(int x=0; x < unitList.size(); x++){
                        unitList.get(x).setX(unitList.get(x).getX()+1);
                    }
                    for(int x=0; x < castleList.size(); x++){
                        castleList.get(x).setX(castleList.get(x).getX()+1);
                    }
            }
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if((input.isKeyDown(Input.KEY_D)||input.isControlPressed(1)) && cursorX+1<8 && moveDelay == true&&showMenu==false){
            if(cursorX+1<7||(screenX==-9&&cursorX!=-8)){
                if((isMoving==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) <= unitTemp.getRange())){
                newX++;
                distance+=moveMod(globalX, globalX+1, unitTemp.getX());
                if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                }
            }else if((isMoving==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) > unitTemp.getRange())){
                cursor = new Image("res/cursorGrey.png");
                newX++;
                distance+=moveMod(globalX, globalX+1, unitTemp.getX());
            }
            cursorX+=1;
            globalX+=1;
            moveDelay = false;
            time = System.currentTimeMillis();
            }else if(screenX+1<11){//SCROLL SCREEN
                if((isMoving==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) <= unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) <= unitTemp.getRange())){
                newX++;
                distance+=moveMod(globalX, globalX+1, unitTemp.getX());
                if(isAttacking==true){
                        cursor = new Image("res/rcursor.png");
                    }else{
                        cursor = new Image("res/cursorMove.png");
                    }
            }else if((isMoving==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) > unitTemp.getMove())||(isAttacking==true && distance+moveMod(globalX, globalX+1, unitTemp.getX()) > unitTemp.getRange())){
                cursor = new Image("res/cursorGrey.png");
                newX++;
                distance+=moveMod(globalX, globalX+1, unitTemp.getX());
            }
            screenX-=1;
            globalX+=1;
            for(int x=0; x < unitList.size(); x++){
                        unitList.get(x).setX(unitList.get(x).getX()-1);
                    }
                    for(int x=0; x < castleList.size(); x++){
                        castleList.get(x).setX(castleList.get(x).getX()-1);
                    }
            moveDelay = false;
            time = System.currentTimeMillis();
            }
        }
        if((input.isKeyPressed(Input.KEY_E)||input.isControlPressed(4))&&moveDelay==true&&Main.turn==Main.player){
            if(showMenu==false){
                showMenu = true;
                moveDelay = false;
                time = System.currentTimeMillis();
            }else{
                for(int x = 0; x < unitList.size(); x++){
                    if(unitList.get(x).getX()==globalX&&unitList.get(x).getY()==globalY){
                        unitTemp = unitList.get(x);
                        pos = x;
                    }
                }
                if(menuSelectorPos == 0&& unitTemp.getActions()<2){
                    showMenu = false;
                    moveDelay = false;
                    if(isMoving==false&&BoardMatrix.board[globalX][globalY] == Main.player){                    
                        newX=oldX=globalX;
                        newY=oldY=globalY;
                        isMoving = true;
                        cursor = new Image("res/cursorMove.png");
                        move="CONFIRM MOVE";
                        
                    }
                    else if(isMoving==true && BoardMatrix.board[globalX][globalY] == 0 && distance <= unitTemp.getMove() ){
                        BoardMatrix.board[oldX][oldY] = 0;
                        unitTemp.setX(newX);
                        unitTemp.setY(newY);
                        unitTemp.setPos();
                        unitList.set(pos,unitTemp);
                        pos=0;
                        cursor = new Image("res/cursor.png");
                        isMoving=false;
                        move="MOVE";
                        distance = 0;
                        new Thread(new SendThread(null,"tm"+String.valueOf(oldX)+","+String.valueOf(oldY)+","+String.valueOf(newX)+","+String.valueOf(newY))).start();
                        
                        unitTemp.takeAction();
                    }
                }else if(menuSelectorPos == 1&&moveDelay==true&&unitTemp.getActions()<2&&isAttacking==false){ //DAMAGE CALCULATIONS
                    moveDelay = false;
                    time = System.currentTimeMillis();
                    showMenu=false;
                    isAttacking=true;
                }else if(menuSelectorPos == 1&&moveDelay==true&&unitTemp.getActions()<2&&isAttacking==true&&distance<=unitTemp.getRange()){
                    if(BoardMatrix.board[globalX][globalY]==Main.opponant){
                        for(int x = 0; x < unitList.size(); x++){
                            if(unitList.get(x).getX()==globalX&&unitList.get(x).getY()==globalY){
                                oppTemp = unitList.get(x);
                            }
                        }
                        if(unitTemp.getUnitClass().isMage()==false){
                            oppTemp.setHp(oppTemp.getHp()-(unitTemp.getAtk() - oppTemp.getDef()));
                            new Thread(new SendThread(null,"ta"+String.valueOf(oppTemp.getX())+","+String.valueOf(oppTemp.getY())+","+String.valueOf(oppTemp.getHp())+",")).start();
                        }else if(unitTemp.getUnitClass().isMage()==true){
                            oppTemp.setHp(oppTemp.getHp()-(unitTemp.getMagic() - oppTemp.getResist()));
                            new Thread(new SendThread(null,"ta"+String.valueOf(oppTemp.getX())+","+String.valueOf(oppTemp.getY())+","+String.valueOf(oppTemp.getHp())+",")).start();
                        }
                        System.out.println(oppTemp.getHp());
                        unitTemp.takeAction();
                        cursor = new Image("res/cursor.png");
                        distance = 0;
                        showMenu = false;
                        isAttacking=false;
                        menuSelectorPos=0;
                    }       
                }else if(menuSelectorPos == 2&&moveDelay==true){
                    for(int x = 0; x < unitList.size(); x++){
                        if(unitList.get(x).getID()==Main.player){
                            unitList.get(x).endTurn();
                        }
                    }
                    showMenu = false;
                    moveDelay = false;
                    isMoving=false;
                    menuSelectorPos = 0;
                    Main.turn = Main.opponant;//SWITCHES TURN NUM TO OPP'S ID
                    for(int x = 0; x < castleList.size(); x++){
                        if(castleList.get(x).getID() == Main.player){
                            playerScore+=50;
                        }
                    }
                    new Thread(new SendThread(null,"tS"+playerScore)).start();
                    //ENDING TURN
                    new Thread(new SendThread(null,"tugo")).start();
                }else if(menuSelectorPos == 3){
                    showMenu = false;
                    moveDelay = false;
                    isMoving=false;
                    menuSelectorPos = 0;
                    move="MOVE";
                    distance=0;
                }
            }
        }
        if(input.isKeyPressed(Input.KEY_R)||input.isControlPressed(5)){
            showMenu = false;
            moveDelay = true;
            menuSelectorPos = 0;
            distance=0;
            showStats=false;
            if(isMoving==true){
                isMoving=false;
                move="MOVE";
                cursor=new Image("res/cursor.png");
                newX=oldX;
                newY=oldY;
            }
            isAttacking=false;
        }
        if(input.isKeyPressed(Input.KEY_Q)||input.isControlPressed(8)||input.isControlPressed(9)&&moveDelay==true){
            for(int x = 0; x < unitList.size(); x++){
                if(unitList.get(x).getX()==globalX&&unitList.get(x).getY()==globalY){
                    unitTemp = unitList.get(x);
                    pos = x;
                }
            }
            hp=String.valueOf(unitTemp.getHp());
            atk=String.valueOf(unitTemp.getAtk());
            def=String.valueOf(unitTemp.getDef());
            mag=String.valueOf(unitTemp.getMagic());
            res=String.valueOf(unitTemp.getResist());
            movement=String.valueOf(unitTemp.getMove());
            baseHp=String.valueOf(unitTemp.getBaseHp());
            unitClass=String.valueOf(unitTemp.getName());
            id=String.valueOf(unitTemp.getID());
            showStats=true;
            moveDelay = false;
            time = System.currentTimeMillis();
        }
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            new Thread(new SendThread(null,"tOpponant RQ'd")).start();
            new Thread(new SendThread(null,"dc")).start();
            new Thread(new SendThread(null,"i"+Main.playerName+"-")).start();
            Main.ig=false;
        }
        for(int x = 0; x < unitList.size(); x++){
            if(unitList.get(x).getHp()<=0){
                BoardMatrix.board[unitList.get(x).getX()][unitList.get(x).getY()] = 0;
                unitList.remove(x);             
            }
            if(unitList.get(x).getID()==Main.player){
                playerUnits++;
            }else if(unitList.get(x).getID()==Main.opponant){
                opponantUnits++;
            }
        }
        if(playerUnits==0||opponantScore==500){
                //YOU LOSE!
                new Thread(new SendThread(null,"i"+Main.playerName+"-")).start();
                new Thread(new SendThread(null,"dc")).start();
                sbg.enterState(0);
            }else if(opponantUnits==0||playerScore==500){
                //YOU WIN!
                new Thread(new SendThread(null,"i"+Main.playerName+"+")).start();
                new Thread(new SendThread(null,"dc")).start();
                sbg.enterState(0);
            }
            playerUnits=0;
            opponantUnits=0;
        if(Main.ig==false){
            BoardMatrix.init();
            init(gc, sbg);
            new Thread(new SendThread(null,"dc")).start();
            //stop ping thread
            pingThread.shutdown();
            System.out.println("Ping stops now!!S");
            sbg.enterState(0);
        }
        for(int x = 0; x < unitList.size(); x++){
            for(int y = 0; y < castleList.size(); y++){
                if(unitList.get(x).getX()==castleList.get(y).getX() && unitList.get(x).getY()==castleList.get(y).getY()){
                    castleList.get(y).capture(unitList.get(x).getID());
                }
            }
        }
        if(Main.turn==Main.opponant){
            cursor=new Image("res/cursorGrey.png");
        }else if(isAttacking==false&&isMoving==false){
            cursor = new Image("res/cursor.png");
        }
        //System.out.println(globalX + " , " + globalY);
    }
    public static int moveMod(int current, int dest, int origin){
        int mod=0;
        if((current-origin)*(current-origin)>(dest-origin)*(dest-origin)){
            return -1;
        }else{
            return 1;
        }
    }
}