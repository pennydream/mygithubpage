package snake;

import simplegui.SGMouseListener;
import simplegui.SimpleGUI;

public class startListenerClass implements SGMouseListener{
    
    SimpleGUI sg;
    
    startListenerClass(SimpleGUI sg1){
        sg=sg1;
        sg.registerToMouse(this);
        sg.labelButton1("");
        sg.labelButton2("");
        sg.labelSwitch("");
    }
    @Override
    public void reactToMouseClick(int x, int y) {
        if(x>200&&x<400&&y>200&&y<250)
            new Game(sg);
        }
}
