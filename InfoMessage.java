//********************************************************************
//
//  Info Message Box Class
//
//      This class is used to make informational popups
//      Pass the infoPOPdyn( ) function a string argument as your message
//
//********************************************************************



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;


public class InfoMessage extends JFrame{

//    public static void infoPOP(/*String MESSAGE*/){
//        JOptionPane.showMessageDialog(new InfoMessage(), "Student Updated!"/*MESSAGE*/, "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
//
//}


    public static void infoPOPdyn(String MESSAGE){
        JOptionPane.showMessageDialog(new InfoMessage(), MESSAGE, "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
    }

};
