package snake;

import java.awt.Color;
import simplegui.SimpleGUI;

public class startScreen {

    SimpleGUI sg;
    startScreen(SimpleGUI sg1){
        sg=sg1;
        sg.drawFilledBox(200, 200, 200, 50, Color.red,1, "startMenu");
        sg.drawBox(202, 202, 196, 46);
        sg.drawText("START", 275, 230);
        startListenerClass slc = new startListenerClass(sg);
    }
    
}
