// This class will make a new popup
//      it takes in a scroll pane that was made earlier
//      which holds a JTable
//          then this class puts all that into a new Frame

//package SM;

import javax.swing.*;

public class PopUp extends JFrame{

    public PopUp(JScrollPane sPane, String TITLE){
        this.setTitle(TITLE);
        
        setSize(800,400);
        setLocation(600,300);

        JPanel p = new JPanel();
        p.add(sPane);
        add(p);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}