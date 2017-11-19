import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main {
	static Connection con;
	
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

    		//After login window is closed, open options for gui
    		loginDlg.addWindowListener(new WindowAdapter() {
    			public void windowClosed(WindowEvent e) {
    				if(loginDlg.isSucceeded()){
    					con = loginDlg.getConnection();
    				} else {
    					JDialog failed = new JDialog();
    					failed.setVisible(true);
    					JOptionPane.showMessageDialog(failed, "Login Failed", "Login", JOptionPane.ERROR_MESSAGE);
    					failed.dispose();
    				}
    			}
    		});
	}
}