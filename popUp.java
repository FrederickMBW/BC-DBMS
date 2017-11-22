// This class will make a new popup
//      it takes in a scroll pane that was made earlier
//      which holds a JTable
//          then this class puts all that into a new Frame

//package SM;

import javax.swing.*;

public class popUp extends JFrame{

    public popUp(JScrollPane sPane, String TITLE){

        JTextField viewTITLE = new JTextField(TITLE);

        setSize(800,400);
        setLocation(600,300);

        JPanel p = new JPanel();
        p.add(viewTITLE);
        p.add(sPane);
        add(p);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }


}
