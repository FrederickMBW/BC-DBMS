package com.company;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CommonDialogs {
    //A pop up for MySql errors
    public static void mySqlErrorMessage(SQLException e) {
        String errorTitle = "MySql Error";
        String errorMessage = e.getErrorCode() + "\n" + e.getMessage();
        JOptionPane.showMessageDialog(new JPanel(), errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    public static void standardErrorMessage(String s1, String s2){
        JOptionPane.showMessageDialog(new JPanel(), s2, s1, JOptionPane.ERROR_MESSAGE);
    }

    public static void standardMessageBox(String s1,String s2){
        JOptionPane.showMessageDialog(new JPanel(), s2, s1, JOptionPane.INFORMATION_MESSAGE);
    }

    //A pop up for Number Format errors
    public static void numberFormatErrorMessage(NumberFormatException e) {
        String errorTitle = "Number Format Error";
        String errorMessage = e.getMessage();
        JOptionPane.showMessageDialog(new JPanel(), errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    //A pop up for illegal arguments
    public static void illegalArgumnetExceptionMessage(IllegalArgumentException e) {
        String errorTitle = "Illegal Argument";
        String errorMessage = e.getMessage();
        JOptionPane.showMessageDialog(new JPanel(), errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }
}