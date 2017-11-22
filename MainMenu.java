import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;

public class MainMenu extends JDialog {
    private JButton btnItems;
    private JButton btnCourses;
    private JButton btnCancel;
    private Connection con;
    private JPanel bp;
	
	public MainMenu(Connection con) {
		this.con = con;
        
        btnItems = new JButton("Check Out Items");
        btnItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		//prgram me!
            }
        });
        
        btnCourses = new JButton("Course Equivalents");
        btnCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		//TODO - Playing around here. Get set into a table for viewing
            		ResultSet set = Queries.getSIDwithSchoolName(con, "%Washington%");
            	    // It creates and displays the table
            		try {
						while (set.next()) {
							System.out.println(set.getInt(1) + " " + set.getString(2));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        bp = new JPanel();
        bp.add(btnItems);
        bp.add(btnCourses);
        bp.add(btnCancel);
     
        getContentPane().add(bp, BorderLayout.CENTER);
        
        pack();
        setResizable(false);
	}
}