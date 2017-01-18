package snake;

import simplegui.GUIListener;
import simplegui.KeyboardListener;
import simplegui.SGMouseListener;
import simplegui.SimpleGUI;
import simplegui.TimerListener;
import java.awt.Color;
import java.util.LinkedList;

public class gameListenerClass  implements TimerListener, KeyboardListener{
    
    SimpleGUI sg;
    Game g;
    boolean gameOver = false;
    boolean directionScreen = true;
    boolean gameScreen = false;
    boolean gameStart=false;
    boolean CLOSED=false;
    double headx = 287.5;
    double heady = 262.5;
    double goalx;
    double goaly;
    char dir;
    LinkedList direction=new LinkedList();
    char[] dirr = new char[1000];
    int snakeLength=0;
    int it=0;
    
    gameListenerClass(SimpleGUI sg1,Game g1){
        sg=sg1;
        g=g1;
        sg.registerToTimer(this);
        sg.registerToKeyboard(this);
        sg.eraseAllDrawables();
        sg.drawBox(25, 25, 550, 450, Color.red, 1, 3, "rim");
        sg.drawFilledBox(190, 210, 205, 140, Color.red,1, "directions");
        sg.drawBox(192, 212, 201, 136);
        sg.drawText("DIRECTIONS:", 225, 230);
        sg.drawText("A to move left", 225, 245);
        sg.drawText("D to move right", 225, 260);
        sg.drawText("S to move down", 225, 275);
        sg.drawText("W to move up", 225, 290);
        sg.drawText("PRESS SPACE TO CONTINUE", 195, 325);
        sg.drawText("SNAKE", 270, 150, Color.green,1,null);
    }
    
    @Override
    public void reactToKeyboardEntry(String string) {
        }

    @Override
    public void reactToKeyboardSingleKey(String string) {
        if(!CLOSED){
        if(gameOver&&string.equals("v")){
            sg.eraseAllDrawables();
            CLOSED=true;
            sg.timerPause();
            sg.removeFromTimer(this);
            sg.removeFromKeyboard(this);
            g.reset();
        }
        if(directionScreen&&string.equals(" ")){
            gameScreen=true;
            directionScreen=false;
            drawGrid();
        }
        if(!gameStart&&gameScreen){
            g.start();
            sg.timerStart(150);
            gameStart = true;
        }
        if(gameScreen&&string.equals("a"))dir='a';
        if(gameScreen&&string.equals("w"))dir='w';
        if(gameScreen&&string.equals("d"))dir='d';
        if(gameScreen&&string.equals("s"))dir='s';
    }
    }

    @Override
    public void reactToTimer(long l) {
        if(!CLOSED){
        direction.addFirst(dir);
        it++;
        if(dir=='a') headx-=25;
        if(dir=='d') headx+=25;
        if(dir=='w') heady-=25;
        if(dir=='s') heady+=25;
        if(headx<25||heady<25||headx>575||heady>475) gameOver();
        sg.eraseSingleDrawable("head");
        sg.drawDot(headx, heady, 12.5, Color.green,1,"head");
        if(gameScreen&&headx==goalx&&heady==goaly){
            sg.eraseSingleDrawable("goal");
            newGoal();
            sg.drawDot(goalx, goaly, 12.5, Color.yellow,1,"goal");
            snakeLength++;
        }
        drawSnake();
        }
    }
    
    public void drawGrid(){
        sg.eraseAllDrawables();
        sg.drawBox(25, 25, 550, 450, Color.red, 1, 3, "rim");
        for(int i=0; i<550;i+=25)
            sg.drawLine(25, 50+i, 575, 50+i, Color.red, 1, 1, null);
        for(int i=0; i<550;i+=25)
            sg.drawLine(50+i, 25, 50+i,475, Color.red, 1, 1, null);
        directionScreen=false;
        newGoal();
        sg.drawDot(goalx, goaly, 12.5, Color.yellow,1,"goal");
    }
    public void newGoal(){
        int i = (int) (22*Math.random());
        int j = (int) (18*Math.random());
        goalx=37.5+25*i;
        goaly=37.5+25*j;
    }
    
    public void gameOver(){
        gameOver=true;
        sg.timerPause();
        sg.eraseAllDrawables();
        sg.drawFilledBox(200, 200, 200, 100, Color.red,1, "gameOver");
        sg.drawBox(202, 202, 196, 96);
        sg.drawText("GAME OVER", 240, 230);
        sg.drawText("SCORE: "+snakeLength, 240, 260);
        sg.drawText("Press 'v' to replay", 210, 400, Color.red, 1, "gameOverDir");
    }
    
    public void drawSnake(){
        for(int j=0;j<snakeLength;j++) sg.eraseSingleDrawable("snake");
        double tempx=headx,tempy=heady;
        for(int i=0;i<snakeLength;i++){
            if((char)direction.get(i)=='a') tempx+=25;
            if((char)direction.get(i)=='d') tempx-=25;
            if((char)direction.get(i)=='w') tempy+=25;
            if((char)direction.get(i)=='s') tempy-=25;
            sg.drawDot(tempx, tempy, 12.5, Color.green, 1, "snake");
            if(headx==tempx&&heady==tempy&&i!=0) gameOver();
            }
    }
}
