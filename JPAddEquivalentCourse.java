import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPAddEquivalentCourse extends JPanel {
	private JComboBox<Integer> jbAllBCCID;
    private JLabel lbCID1;
	private JComboBox<Integer> jbAllCID;
    private JLabel lbCID2;
    private JLabel lbIsEquivalent;
    private JComboBox<String> jbTrueFalse;
	private JTextField tfComment;
    private JLabel lbComment;
    private JTextField tfCourseName1;
    private JTextField tfCourseSchool1;
    private JTextField tfCourseName2;
    private JTextField tfCourseSchool2;
    private JButton btnAdd;

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
        jbAllCID = new JComboBox<Integer>(aryAllCID);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(jbAllCID, cs);
        
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
        
        String[] aryTrueFalse = {"True", "False"};
        jbTrueFalse = new JComboBox<String>(aryTrueFalse);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(jbTrueFalse, cs);
        
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
   
        btnAdd = new JButton("Add Equivalent Course");
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(btnAdd, cs);
        
        //Load course information
        loadCourse1(con);
        loadCourse2(con);
        
        btnAdd.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					addCourse(con);
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "You have successfully added an equivalent course.", "Nice Job!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        //Load Course Name and School for CID1 Every time drop down is adjusted
        jbAllBCCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				loadCourse1(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Erro", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
        
      //Load Course Name and School for CID2 Every time drop down is adjusted
        jbAllCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				loadCourse2(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
	}
	
	//Return every CID from Bellevue College
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
	
	//Return every CID
	public Integer[] getAllCID(Connection con) throws SQLException {
		ResultSet rsAllCID = Queries.getAllCID(con);
		
		int rows = getRowCount(rsAllCID);
		
		Integer[] result = new Integer[rows];
		
		for (int i = 0; i < rows; i++) {
			rsAllCID.next();
			result[i] = rsAllCID.getInt(1);
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
    
	//Return the course name
    public int getCID2() {
    	return (int) jbAllCID.getSelectedItem();
    }
    
	//Return if the course if equivalent
    public String getIsEquivalent() {
    		return (String) jbTrueFalse.getSelectedItem();
    }
    
	//Return the comment
    public String getComment() {
        return tfComment.getText().trim();
    }

    //Create a pop up for the result of the search
    public void addCourse(Connection con) throws SQLException, NumberFormatException {
    		int intCID1 = getCID1();
    		int intCID2 = getCID2();
    		String strIsEquivalent = getIsEquivalent().trim();
    		String strComment = getComment().trim();

    		Queries.addEquivalentCourse(con, intCID1, intCID2, Boolean.valueOf(strIsEquivalent), strComment);
    }
    
    //Update the data for course1
    public void loadCourse1(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID1();
    	
    		ResultSet rs = Queries.getCourseNameAndSchool(con, intCID);
    		if (rs.next()) {
    			tfCourseName1.setText(rs.getString(1));
    			tfCourseSchool1.setText(rs.getString(2));
    		} else {
    			JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
    
    //Update the data for course2
    public void loadCourse2(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID2();
    	
    		ResultSet rs = Queries.getCourseNameAndSchool(con, intCID);
    		if (rs.next()) {
    			tfCourseName2.setText(rs.getString(1));
    			tfCourseSchool2.setText(rs.getString(2));
    		} else {
    			JOptionPane.showMessageDialog(JPAddEquivalentCourse.this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
}