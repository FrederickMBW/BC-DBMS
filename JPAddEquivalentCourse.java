import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPAddEquivalentCourse extends JPanel {
	private JComboBox<Integer> jbAllBCCID, jbAllOtherCID;
	private JComboBox<String> jbIsEquivalent;
    private JLabel lbCID1, lbCID2, lbIsEquivalent, lbComment, lbModified;
	private JTextField tfComment, tfCourseName1, tfCourseSchool1, tfCourseName2, tfCourseSchool2, tfDate;
    private JButton btnAdd, btnUpdate;

	public JPAddEquivalentCourse(Connection con) throws SQLException {
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
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfCourseName1, cs);
        
        tfCourseSchool1 = new JTextField(30);
        tfCourseSchool1.setEditable(false);
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
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseName2, cs);
        
        tfCourseSchool2 = new JTextField(30);
        tfCourseSchool2.setEditable(false);
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
        
        lbModified = new JLabel("Last Modified:");
        cs.gridx = 0;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(lbModified, cs);
        
        tfDate = new JTextField(20);
        tfDate.setEditable(false);
        tfDate.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(tfDate, cs);
   
        btnAdd = new JButton("Add Equivalent Course");
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnUpdate= new JButton("Update Course");
        cs.gridx = 2;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        //Update the window with correct information
        updateCourse1(con);
        updateCourse2(con);
        updateWindow(con);
        
        //Active listener for the add button
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
        				addCourse(con);
        				updateWindow(con);
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "You have successfully added an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Add Fail", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        //Active listener for the update button
        btnUpdate.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				ResultSet rs = Queries.getEquivalentInfo(con, getCID1(), getCID2());
	    				if (rs.next()) {
	    					updateCourse(con);
	    					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "You have successfully updated an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
	    				} else {
	    					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Create Equivalent Course First!", "Error", JOptionPane.ERROR_MESSAGE);
	    				}
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Update Failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
        });
        
        //Load Course Name and School for CID1 Every time drop down is adjusted
        jbAllBCCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse1(con);
	    				updateWindow(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
        
      //Load Course Name and School for CID2 Every time drop down is adjusted
        jbAllOtherCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse2(con);
	    				updateWindow(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
	}
	
	//Return every Bellevue College CID in ascending order
	public Integer[] getAllBCCID(Connection con) throws SQLException {
		ResultSet rsAllBCCID = Queries.getAllBCCID(con);
		
		int rows = getRowCount(rsAllBCCID);
		
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
		
		int rows = getRowCount(rsAllNonBCCID);
		
		Integer[] result = new Integer[rows];
		
		for (int i = 0; i < rows; i++) {
			rsAllNonBCCID.next();
			result[i] = rsAllNonBCCID.getInt(1);
		}
		
		Arrays.sort(result);
		
		return result;
	}
	
	//Count the number of rows in the result set
	public int getRowCount(ResultSet rs) throws SQLException {
		int count = 0;
		while (rs.next()) {
			count++;
		}
		
		rs.beforeFirst();
		
		return count;
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
    public String getIsEquivalent() {
    		return (String) jbIsEquivalent.getSelectedItem();
    }
    
	//Return the course equivalent comment
    public String getComment() {
        return tfComment.getText().trim();
    }

    //Add a course equivalancy to the database
    public void addCourse(Connection con) throws SQLException, NumberFormatException {
    		int intCID1 = getCID1();
    		int intCID2 = getCID2();
    		String strIsEquivalent = getIsEquivalent().trim();
    		String strComment = getComment().trim();

    		Queries.addEquivalentCourse(con, intCID1, intCID2, Boolean.valueOf(strIsEquivalent), strComment);
    }
    
    //Update the information shown for course1
    public void updateCourse1(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID1();
    	
    		ResultSet rs = Queries.getCourseNameAndSchool(con, intCID);
    		if (rs.next()) {
    			tfCourseName1.setText(rs.getString(1));
    			tfCourseSchool1.setText(rs.getString(2));
    		} else {
    			JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
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
    			JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
    
    //Update the window information
    public void updateWindow(Connection con) throws SQLException, NumberFormatException {
    		ResultSet rs = Queries.getEquivalentInfo(con, getCID1(), getCID2());
    		updateComment(con, rs);
    		updateEquivalent(con, rs);
    		updateDate(con, rs);
    }
    
    //Update the comment for the two selected courses
    public void updateComment(Connection con, ResultSet rs) throws SQLException {
    		if (rs.next()) {
    			tfComment.setText(rs.getString(2));
    		} else {
    			tfComment.setText("");
    		}
    		rs.beforeFirst();
    }
    
    //Update if the courses are equivalent
    public void updateEquivalent(Connection con, ResultSet rs) throws SQLException {
    		if (rs.next()) {
    			if (rs.getBoolean(1) == true) {
    				jbIsEquivalent.setSelectedIndex(0);
    			} else {
    				jbIsEquivalent.setSelectedIndex(1);
    			}
    		} else {
    			jbIsEquivalent.setSelectedIndex(2);
    		}
    		rs.beforeFirst();
    }
    
    //Update if the courses are equivalent
    public void updateDate(Connection con, ResultSet rs) throws SQLException {
    		if (rs.next()) {
    			tfDate.setText(rs.getDate(3).toString()); 
    		} else {
    			tfDate.setText("");
    		}
    		rs.beforeFirst();
    }
    
   //Update a course equivalency
   public void updateCourse(Connection con) throws SQLException {
	   Queries.updateCourseEquivalent(con, getCID1(), getCID2(), Boolean.parseBoolean(getIsEquivalent()), getComment());
   }
}