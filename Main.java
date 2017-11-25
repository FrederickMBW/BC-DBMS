import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		//Load driver for mysql connection
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        		//TODO - Throw dialog box with error
            return;
        }
		
        //Login to the database
		LoginDialog loginDlg = new LoginDialog();
    		loginDlg.setVisible(true);

    		//After login window is closed, 
    		//Open main menu if login successful
    		//Else give show message and close program
    		loginDlg.addWindowListener(new WindowAdapter() {
    			public void windowClosed(WindowEvent e) {
    				if(loginDlg.isSucceeded()){
    					openMainMenu(loginDlg.getConnection());
    				} else {
    					JDialog failed = new JDialog();
    					failed.setVisible(true);
    					JOptionPane.showMessageDialog(failed, "Login Failed", "Login", JOptionPane.ERROR_MESSAGE);
    					failed.dispose();
    				}
    			}
    		});
	}
	
	public static void openMainMenu(Connection con) {
		Test menu = new Test(con);
		menu.setVisible(true);
		
		menu.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					con.close();
				} catch (SQLException e1) {
					//Do Nothing
				}
			}
		});
	}
}