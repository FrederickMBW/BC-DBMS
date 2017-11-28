import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPEquivalentCourse extends JPanel {
	private JComboBox<Integer> jbAllBCCID, jbAllOtherCID;
	private JComboBox<String> jbIsEquivalent;
    private JLabel lbCID1, lbCID2, lbIsEquivalent, lbComment, lbModified, lbApprovedBy;
	private JTextField tfComment, tfCourseName1, tfCourseSchool1, tfCourseName2, tfCourseSchool2, tfDate, tfApprovedSID;
    private JButton btnAdd, btnUpdate;

	public JPEquivalentCourse(Connection con) throws SQLException {
		super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbCID1 = new JLabel("CID1:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCID1, cs);
        
        Integer[] aryAllBCCID = getAllBCCID(con);
        jbAllBCCID = new JComboBox<Integer>(aryAllBCCID);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(jbAllBCCID, cs);
        
        tfCourseName1 = new JTextField(20);
        tfCourseName1.setEditable(false);
        tfCourseName1.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfCourseName1, cs);
        
        tfCourseSchool1 = new JTextField(30);
        tfCourseSchool1.setEditable(false);
        tfCourseSchool1.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 3;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfCourseSchool1, cs);
        
        lbCID2 = new JLabel("CID2:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbCID2, cs);
        
        Integer[] aryAllCID = getAllCID(con);
        jbAllOtherCID = new JComboBox<Integer>(aryAllCID);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(jbAllOtherCID, cs);
        
        tfCourseName2 = new JTextField(20);
        tfCourseName2.setEditable(false);
        tfCourseName2.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseName2, cs);
        
        tfCourseSchool2 = new JTextField(30);
        tfCourseSchool2.setEditable(false);
        tfCourseSchool2.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 3;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseSchool2, cs);
        
        lbIsEquivalent = new JLabel("Is Equivalent:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbIsEquivalent, cs);
        
        String[] aryTrueFalse = {"True", "False", "Unknown"};
        jbIsEquivalent = new JComboBox<String>(aryTrueFalse);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(jbIsEquivalent, cs);
        
        lbComment = new JLabel("Comment:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbComment, cs);
        
        tfComment = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfComment, cs);
        
        lbApprovedBy = new JLabel("Approved By:");
        cs.gridx = 0;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(lbApprovedBy, cs);
 
        tfApprovedSID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(tfApprovedSID, cs);
        
        lbModified = new JLabel("Last Modified:");
        cs.gridx = 0;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(lbModified, cs);
        
        tfDate = new JTextField(20);
        tfDate.setEditable(false);
        tfDate.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(tfDate, cs);
   
        btnAdd = new JButton("Add Equivalent Course");
        cs.gridx = 1;
        cs.gridy = 11;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnUpdate= new JButton("Update Course");
        cs.gridx = 2;
        cs.gridy = 11;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        //Update the window with correct information
        updateCourse1(con);
        updateCourse2(con);
        updateWindow(con);
        
        //Add a equivalent course to the database
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
        				addCourse(con);
        				updateWindow(con);
					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "You have successfully added an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Failed to Add", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        //Update the equivalent course in the database
        btnUpdate.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				ResultSet rs = Queries.getEquivalentInfo(con, getCID1(), getCID2());
	    				if (rs.next()) {
	    					updateCourse(con);
	    					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "You have successfully updated an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
	    				} else {
	    					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Create Equivalent Course First!", "Error", JOptionPane.ERROR_MESSAGE);
	    				}
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Failed to Update", "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
        });
        
        //Update the data fields every time the Bellevue College Course is changed
        jbAllBCCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse1(con);
	    				updateWindow(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
        
      //Update the data fields every time the non-Bellevue College course is changed
        jbAllOtherCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse2(con);
	    				updateWindow(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
	}
	
	//Return every Bellevue College CID in ascending order
	public Integer[] getAllBCCID(Connection con) throws SQLException {
		ResultSet rsAllBCCID = Queries.getAllBCCID(con);
		
		int rows = Queries.getRowCount(rsAllBCCID);
		
		Integer[] result = new Integer[rows];
		
		for (int i = 0; i < rows; i++) {
			rsAllBCCID.next();
			result[i] = rsAllBCCID.getInt(1);
		}
		
		Arrays.sort(result);
		
		return result;
	}
	
	//Return every non-Bellevue College CID in ascending order
	public Integer[] getAllCID(Connection con) throws SQLException {
		ResultSet rsAllNonBCCID = Queries.getAllNonBCCID(con);
		
		int rows = Queries.getRowCount(rsAllNonBCCID);
		
		Integer[] result = new Integer[rows];
		
		for (int i = 0; i < rows; i++) {
			rsAllNonBCCID.next();
			result[i] = rsAllNonBCCID.getInt(1);
		}
		
		Arrays.sort(result);
		
		return result;
	}
	
	//Return the CID of course on (Bellevue College Course)
    public int getCID1() {
    		return (int) jbAllBCCID.getSelectedItem();
    }
    
	//Return the course name (Non-BC course)
    public int getCID2() {
    		return (int) jbAllOtherCID.getSelectedItem();
    }
    
	//Return if the courses are equivalent
    public boolean getIsEquivalent() {
    		return Boolean.parseBoolean((String) jbIsEquivalent.getSelectedItem());
    }
    
	//Return the course equivalent comment
    public String getComment() {
        return tfComment.getText().trim();
    }

	//Return the SID of the approval 
    public String getSID() {
        return tfApprovedSID.getText().trim();
    }
    
    //Add a course equivalancy to the database
    public void addCourse(Connection con) throws SQLException, NumberFormatException {
    		int intCID1 = getCID1();
    		int intCID2 = getCID2();
    		boolean blIsEquivalent = getIsEquivalent();
    		String strComment = getComment();
    		String strSID = getSID();

    		Queries.addEquivalentCourse(con, intCID1, intCID2, blIsEquivalent, strComment, strSID);
    }
    
    //Update the information shown for course1
    public void updateCourse1(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID1();
    	
    		ResultSet rs = Queries.getCourseNameAndSchool(con, intCID);
    		if (rs.next()) {
    			tfCourseName1.setText(rs.getString(1));
    			tfCourseSchool1.setText(rs.getString(2));
    		} else {
    			JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
    
    //Update the information shown for course2
    public void updateCourse2(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID2();
    	
    		ResultSet rs = Queries.getCourseNameAndSchool(con, intCID);
    		if (rs.next()) {
    			tfCourseName2.setText(rs.getString(1));
    			tfCourseSchool2.setText(rs.getString(2));
    		} else {
    			JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
    
    //Update the window information
    public void updateWindow(Connection con) throws SQLException, NumberFormatException {
    		ResultSet rsCourseInfo = Queries.getEquivalentInfo(con, getCID1(), getCID2());
    		if (rsCourseInfo.next()) {
    			tfComment.setText(rsCourseInfo.getString(2));
        		updateEquivalent(con, rsCourseInfo);
        		tfDate.setText(rsCourseInfo.getDate(3).toString()); 
        		tfApprovedSID.setText(rsCourseInfo.getString(4));
    		} else {
    			tfComment.setText("");
    			jbIsEquivalent.setSelectedIndex(2);
        		tfDate.setText(""); 
        		tfApprovedSID.setText("");
    		}
    }
    
    //Update if the courses are equivalent
    public void updateEquivalent(Connection con, ResultSet rs) throws SQLException {
		if (rs.getBoolean(1) == true) {
			jbIsEquivalent.setSelectedIndex(0);
		} else {
			jbIsEquivalent.setSelectedIndex(1);
		}
    }
    
   //Update a course equivalency
   public void updateCourse(Connection con) throws SQLException {
		int intCID1 = getCID1();
		int intCID2 = getCID2();
		boolean blIsEquivalent = getIsEquivalent();
		String strComment = getComment();
		String strSID = getSID();
	   
	   Queries.updateCourseEquivalent(con, intCID1, intCID2, blIsEquivalent, strComment, strSID);
   }
}