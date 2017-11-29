import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPEquivalentCourse extends JPanel {
	private JComboBox<Integer> jbAllBCCID, jbAllOtherCID;
	private JComboBox<String> jbIsEquivalent;
    private JLabel lbCID1, lbCID2, lbIsEquivalent, lbComment, lbModified, lbApprovedBy, lbCourseName, lbCourseTitle, lbCourseDepartment, lbCourseSchool;
	private JTextField tfComment, tfCourseName1, tfCourseTitle1, tfCourseDepartment1, tfCourseSchoolName1, tfCourseName2, tfCourseTitle2, tfCourseDepartment2, tfCourseSchoolName2, tfDate, tfApprovedSID;
    private JButton btnAdd, btnUpdate, btnSearch, btnClear;

	public JPEquivalentCourse(Connection con) throws SQLException {
		super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbCourseName = new JLabel("Course Name");
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCourseName, cs);
        
        lbCourseTitle = new JLabel("Course Title");
        cs.gridx = 3;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCourseTitle, cs);
        
        lbCourseDepartment = new JLabel("Department");
        cs.gridx = 4;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCourseDepartment, cs);
        
        lbCourseSchool = new JLabel("School");
        cs.gridx = 5;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCourseSchool, cs);
        
        lbCID1 = new JLabel("CID1:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbCID1, cs);
        
        Integer[] aryAllBCCID = Queries.getArrayAllBCCID(con);
        jbAllBCCID = new JComboBox<Integer>(aryAllBCCID);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(jbAllBCCID, cs);
        
        tfCourseName1 = new JTextField(10);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseName1, cs);
        
        tfCourseTitle1 = new JTextField(15);
        cs.gridx = 3;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseTitle1, cs);
        
        tfCourseDepartment1 = new JTextField(15);
        cs.gridx = 4;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseDepartment1, cs);
        
        tfCourseSchoolName1 = new JTextField(20);
        cs.gridx = 5;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseSchoolName1, cs);
        
        lbCID2 = new JLabel("CID2:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbCID2, cs);
        
        Integer[] aryAllNonBCCID = Queries.getArrayAllNonBCCID(con);
        jbAllOtherCID = new JComboBox<Integer>(aryAllNonBCCID);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(jbAllOtherCID, cs);
        
        tfCourseName2 = new JTextField(10);
        cs.gridx = 2;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfCourseName2, cs);
        
        tfCourseTitle2 = new JTextField(15);
        cs.gridx = 3;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfCourseTitle2, cs);
        
        tfCourseDepartment2 = new JTextField(15);
        cs.gridx = 4;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfCourseDepartment2, cs);
        
        tfCourseSchoolName2 = new JTextField(20);
        cs.gridx = 5;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfCourseSchoolName2, cs);
        
        lbIsEquivalent = new JLabel("Is Equivalent:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbIsEquivalent, cs);
        
        String[] aryTrueFalse = {"True", "False", "Unknown"};
        jbIsEquivalent = new JComboBox<String>(aryTrueFalse);
        jbIsEquivalent.setSelectedIndex(2);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(jbIsEquivalent, cs);
        
        lbComment = new JLabel("Comment:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbComment, cs);
        
        tfComment = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfComment, cs);
        
        lbApprovedBy = new JLabel("Approved By:");
        cs.gridx = 0;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(lbApprovedBy, cs);
 
        tfApprovedSID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(tfApprovedSID, cs);
        
        lbModified = new JLabel("Last Modified:");
        cs.gridx = 0;
        cs.gridy = 11;
        cs.gridwidth = 1;
        this.add(lbModified, cs);
        
        tfDate = new JTextField(20);
        tfDate.setEditable(false);
        tfDate.setBackground(Color.LIGHT_GRAY);
        cs.gridx = 1;
        cs.gridy = 11;
        cs.gridwidth = 1;
        this.add(tfDate, cs);
        
        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 12;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);
        
        btnUpdate = new JButton("Update");
        cs.gridx = 2;
        cs.gridy = 12;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        btnAdd = new JButton("Add");
        cs.gridx = 3;
        cs.gridy = 12;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        btnClear = new JButton("Clear");
        cs.gridx = 4;
        cs.gridy = 12;
        cs.gridwidth = 1;
        this.add(btnClear, cs);
        
        //Add a equivalent course to the database
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
        				addCourse(con);
        				updateWindow(con);
					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "You have successfully added an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					CommonDialogs.mySqlErrorMessage(e1);
				} catch (NumberFormatException e2) {
					CommonDialogs.numberFormatErrorMessage(e2);
				} catch (IllegalArgumentException e3) {
					CommonDialogs.illegalArgumnetExceptionMessage(e3);
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
	    					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "You have successfully updated an equivalent course.", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
	    				} else {
	    					JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Create an Equivalent Course First!", "Error", JOptionPane.ERROR_MESSAGE);
	    				}
				} catch (SQLException e1) {
					CommonDialogs.mySqlErrorMessage(e1);
				} catch (NumberFormatException e2) {
					CommonDialogs.numberFormatErrorMessage(e2);
				}
	        }
        });
        
        //Update the equivalent course in the database
        btnSearch.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
					searchAndDisplay(con);
				} catch (SQLException e1) {
					CommonDialogs.mySqlErrorMessage(e1);
				}
	        }
        });
        
        //Clear all the fields when clear button is clicked
        btnClear.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			clearFields();
	        }
        });
        
        //Update the data fields every time the Bellevue College Course is changed
        jbAllBCCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse1(con);
	    				updateWindow(con);
	    			} catch (SQLException e1) {
	    				CommonDialogs.mySqlErrorMessage(e1);
				} catch (NumberFormatException e2) {
					CommonDialogs.numberFormatErrorMessage(e2);
				}
	    		}
        });
        
      //Update the data fields every time the non-Bellevue College course is changed
        jbAllOtherCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				updateCourse2(con);
	    				updateWindow(con);
	    			} catch (SQLException e1) {
	    				CommonDialogs.mySqlErrorMessage(e1);
				} catch (NumberFormatException e2) {
					CommonDialogs.numberFormatErrorMessage(e2);
				}
	    		}
        });
	}
	
	//Return the CID of course on (Bellevue College Course)
    public int getCID1() {
    		return (int) jbAllBCCID.getSelectedItem();
    }
    
	//Return the course name (Non-BC course)
    public int getCID2() {
    		return (int) jbAllOtherCID.getSelectedItem();
    }
    
    //Get course name 1
    public String getCourseName1() {
    		return tfCourseName1.getText().trim();
    }
    
    //Get course title 1
    public String getCourseTitle1() {
    		return tfCourseTitle1.getText().trim();
    }
    
    //get course department 1
    public String getCourseDepartment1() {
    		return tfCourseDepartment1.getText().trim();
    }
    
    //Get school name 1
    public String getCourseSchoolName1() {
    		return tfCourseSchoolName1.getText().trim();
    }
    
    //Get course name 2
    public String getCourseName2() {
    		return tfCourseName2.getText().trim();
    }
    
    //Get course title 2
    public String getCourseTitle2() {
    		return tfCourseTitle2.getText().trim();
    }
    
    //get course department 2
    public String getCourseDepartment2() {
    		return tfCourseDepartment2.getText().trim();
    }
    
    //Get school name 2
    public String getCourseSchoolName2() {
    		return tfCourseSchoolName2.getText().trim();
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
    public void addCourse(Connection con) throws SQLException, NumberFormatException, IllegalArgumentException {
    		int intCID1 = getCID1();
    		int intCID2 = getCID2();
    		boolean blIsEquivalent = getIsEquivalent();
    		String strComment = getComment();
    		String strSID = getSID();

    		illegalDataFieldsCheck(intCID1, intCID2);
    		Queries.addEquivalentCourse(con, intCID1, intCID2, blIsEquivalent, strComment, strSID);
    }
    
    //Update the information shown for course1
    public void updateCourse1(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID1();
    	
    		if (getCID1() != 0) {
	    		ResultSet rsSchoolName = Queries.getSchoolNameWithCID(con, intCID);
	    		ResultSet rsCourseInfo = Queries.getCourse(con, intCID);
	    		
	    		if (rsSchoolName.next() && rsCourseInfo.next()) {
	    			tfCourseName1.setText(rsCourseInfo.getString(3));
	    			tfCourseTitle1.setText(rsCourseInfo.getString(4));
	    			tfCourseDepartment1.setText(rsCourseInfo.getString(5));
	    			tfCourseSchoolName1.setText(rsSchoolName.getString(1));
	    			
	    			tfCourseName1.setEditable(false);
	    			tfCourseTitle1.setEditable(false);
	    			tfCourseDepartment1.setEditable(false);
	    			tfCourseSchoolName1.setEditable(false);
	    			
	       		tfCourseName1.setBackground(Color.LIGHT_GRAY);
	    			tfCourseTitle1.setBackground(Color.LIGHT_GRAY);
	    			tfCourseDepartment1.setBackground(Color.LIGHT_GRAY);
	    			tfCourseSchoolName1.setBackground(Color.LIGHT_GRAY);
	    		} else {
	    			JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
	    		}
    		} else {
    			tfCourseName1.setText("");
    			tfCourseTitle1.setText("");
    			tfCourseDepartment1.setText("");
    			tfCourseSchoolName1.setText("");
    			
    			tfCourseName1.setEditable(true);
    			tfCourseTitle1.setEditable(true);
    			tfCourseDepartment1.setEditable(true);
    			tfCourseSchoolName1.setEditable(true);
    			
    			tfCourseName1.setBackground(Color.WHITE);
    			tfCourseTitle1.setBackground(Color.WHITE);
    			tfCourseDepartment1.setBackground(Color.WHITE);
    			tfCourseSchoolName1.setBackground(Color.WHITE);
    		}
    }
    
    //Update the information shown for course2
    public void updateCourse2(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID2();
    	
    		if (getCID2() != 0) {
	    		ResultSet rsSchoolName = Queries.getSchoolNameWithCID(con, intCID);
	    		ResultSet rsCourseInfo = Queries.getCourse(con, intCID);
	    		
	    		if (rsSchoolName.next() && rsCourseInfo.next()) {
	    			tfCourseName2.setText(rsCourseInfo.getString(3));
	    			tfCourseTitle2.setText(rsCourseInfo.getString(4));
	    			tfCourseDepartment2.setText(rsCourseInfo.getString(5));
	    			tfCourseSchoolName2.setText(rsSchoolName.getString(1));
	    			
	    			tfCourseName2.setEditable(false);
	    			tfCourseTitle2.setEditable(false);
	    			tfCourseDepartment2.setEditable(false);
	    			tfCourseSchoolName2.setEditable(false);
	    			
	       		tfCourseName2.setBackground(Color.LIGHT_GRAY);
	    			tfCourseTitle2.setBackground(Color.LIGHT_GRAY);
	    			tfCourseDepartment2.setBackground(Color.LIGHT_GRAY);
	    			tfCourseSchoolName2.setBackground(Color.LIGHT_GRAY);
	    		} else {
	    			JOptionPane.showMessageDialog(JPEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
	    		} 
    		} else {
    			tfCourseName2.setText("");
    			tfCourseTitle2.setText("");
    			tfCourseDepartment2.setText("");
    			tfCourseSchoolName2.setText("");
    			
    			tfCourseName2.setEditable(true);
    			tfCourseTitle2.setEditable(true);
    			tfCourseDepartment2.setEditable(true);
    			tfCourseSchoolName2.setEditable(true);
    			
    			tfCourseName2.setBackground(Color.WHITE);
    			tfCourseTitle2.setBackground(Color.WHITE);
    			tfCourseDepartment2.setBackground(Color.WHITE);
    			tfCourseSchoolName2.setBackground(Color.WHITE);
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
    
    //Search for an equivalent course with the given information
    //Create a pop up with all the equivalent courses that match given information
    public void searchAndDisplay(Connection con) throws SQLException {
    		//Get all the data from the fields and edit format with helper method
    		String strCourseName1 = SearchHelper.searchHelper(getCourseName1());
    		String strCourseName2 = SearchHelper.searchHelper(getCourseName2());
    		String strCourseTitle1 = SearchHelper.searchHelper(getCourseTitle1());
    		String strCourseTitle2 = SearchHelper.searchHelper(getCourseTitle2());
    		String strCourseDepartment1 = SearchHelper.searchHelper(getCourseDepartment1());
    		String strCourseDepartment2 = SearchHelper.searchHelper(getCourseDepartment2());
    		String strCourseSchoolName1 = SearchHelper.searchHelper(getCourseSchoolName1());
    		String strCourseSchoolName2 = SearchHelper.searchHelper(getCourseSchoolName2());
    		
    		//Query for all the matching schools
    		ResultSet rs = Queries.searchEquivalent(con, strCourseName1, strCourseName2, strCourseTitle1, strCourseTitle2, strCourseDepartment1, strCourseDepartment2, strCourseSchoolName1, strCourseSchoolName2);
    		
    		//Create a JTable to hold the results and set the preferred width for the columns
    		JTable jtbResult = Queries.ResultSetToJTable(rs);
		int intWidth = 900;
		
		//Create a popup with the results.
    		new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }
    
    //Update if the courses are equivalent
    public void updateEquivalent(Connection con, ResultSet rs) throws SQLException {
		if (rs.getBoolean(1) == true) {
			jbIsEquivalent.setSelectedIndex(0);
		} else {
			jbIsEquivalent.setSelectedIndex(1);
		}
    }
    
   //Update a course equivalent
   public void updateCourse(Connection con) throws SQLException {
		int intCID1 = getCID1();
		int intCID2 = getCID2();
		boolean blIsEquivalent = getIsEquivalent();
		String strComment = getComment();
		String strSID = getSID();
	   
		Queries.updateCourseEquivalent(con, intCID1, intCID2, blIsEquivalent, strComment, strSID);
   }
   
   //Make sure none of the NOT NULL data fields in the database are blank
	public void illegalDataFieldsCheck(int CID1, int CID2) throws IllegalArgumentException{
	   String exceptionMessage = "";
	   
	   if (CID1 == 0) {
		   exceptionMessage += "CID1 Can't Be 0\n";
	   }
	   
	   if (CID2 == 0) {
		   exceptionMessage += "CID2 Can't Be 0\n";
	   }
	   
	   if (!exceptionMessage.equals("")) {
		   throw new IllegalArgumentException(exceptionMessage);
	   }
	}
   
   //Clear all data fields
   public void clearFields() {
	   jbAllBCCID.setSelectedIndex(0);
	   jbAllOtherCID.setSelectedIndex(0);
   }
}