package snake;

import java.awt.Color;
import simplegui.SGMouseListener;
import simplegui.SimpleGUI;

public class Game{
    
    SimpleGUI sg;
    gameListenerClass glc;
    
    Game(SimpleGUI sg1){
        sg=sg1;
        glc = new gameListenerClass(sg,this);
    }
    public void reset(){
        sg.eraseAllDrawables();
        new Game(sg);
    }
    public void start(){
    }
}
