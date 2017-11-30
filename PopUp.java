package com.company;
// This class will make a new popup
//      it takes in a scroll pane that was made earlier
//      which holds a JTable
//          then this class puts all that into a new Frame

//package SM;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class PopUp extends JFrame{
    public PopUp(JScrollPane sPane, String TITLE, int width, int height){
        this.setTitle(TITLE);
        setSize(width + 100, height + 50);

        setLocation(0, 0);
        Dimension dim = new Dimension(width, height);

        JPanel p = new JPanel();
        sPane.setPreferredSize(dim);
        p.add(sPane);
        add(p);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}