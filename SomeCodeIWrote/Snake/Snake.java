package snake;

import java.awt.Color;
import simplegui.SimpleGUI;

public class Snake {

    public static void main(String[] args) {
        new Snake();
    }
    
    Snake(){
    SimpleGUI sg = new SimpleGUI(600,500);
    sg.setBackgroundColor(Color.black);
    startScreen sc = new startScreen(sg);
}
}
