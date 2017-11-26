import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPQueryCourse extends JPanel {
	private JTextField tfCourseName;
    private JLabel lbCourseName;
	private JTextField tfCourseTitle;
    private JLabel lbCourseTitle;
	private JTextField tfCourseDepartment;
    private JLabel lbCourseDepartment;
	private JTextField tfSchoolSID;
    private JLabel lbSchoolSID;
	private JTextField tfSchoolName;
    private JLabel lbSchoolName;
    private JButton btnSearch;
	
	public JPQueryCourse(Connection con) {
		super(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbCourseName = new JLabel("Course Name:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCourseName, cs);
        
        tfCourseName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfCourseName, cs);
        
        lbCourseTitle = new JLabel("Course Title:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbCourseTitle, cs);
        
        tfCourseTitle = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfCourseTitle, cs);
        
        lbCourseDepartment = new JLabel("Course Department:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbCourseDepartment, cs);
        
        tfCourseDepartment = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfCourseDepartment, cs);
        
        lbSchoolSID = new JLabel("SID:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbSchoolSID, cs);
        
        tfSchoolSID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfSchoolSID, cs);
        
        lbSchoolName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbSchoolName, cs);
        
        tfSchoolName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfSchoolName, cs);
        
        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);
        
        btnSearch.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					showResultSet(con);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPQueryCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the course name
    public String getCourseName() {
    		return tfCourseName.getText().trim();
    }
    
	//Return the course title
    public String getCourseTitle() {
    		return tfCourseTitle.getText().trim();
    }
    
	//Return the course department
    public String getCourseDepartment() {
    		return tfCourseDepartment.getText().trim();
    }
    
	//Return the school SID
    public String getSchoolSID() {
        return tfSchoolSID.getText().trim();
    }
    
	//Return the school Name
    public String getSchoolName() {
        return tfSchoolName.getText().trim();
    }
    
    //Create a pop up for the result of the search
    public void showResultSet(Connection con) throws SQLException, NumberFormatException {
    		String strCourseName = "%" + getCourseName().trim().replace(' ', '%') + "%";
    		String strCourseTitle = "%" + getCourseTitle().trim().replace(' ', '%') + "%";
    		String strCourseDepartment = "%" + getCourseDepartment().trim().replace(' ', '%') + "%";
    		String strSID = getSchoolSID().trim();
    		String strSchoolName = "%" + getSchoolName().trim().replace(' ', '%') + "%";
    		ResultSet rsResults;
    		JTable jtbResult;
    		int intWidth;

    		if (getSchoolSID().equals("")) {
    			rsResults = Queries.getCID(con, strCourseName, strCourseTitle, strCourseDepartment, strSchoolName);
    			jtbResult = Queries.ResultSetToJTable(rsResults);
    			jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(1).setPreferredWidth(75);
    			jtbResult.getColumnModel().getColumn(2).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(3).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(4).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(5).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(6).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(7).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(8).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(9).setPreferredWidth(150);
    			jtbResult.getColumnModel().getColumn(10).setPreferredWidth(50);
    			intWidth = 975;
    		} else {
    			rsResults = Queries.getCIDwithSID(con, strCourseName, strCourseTitle, strCourseDepartment, Integer.parseInt(strSID));      
    			jtbResult = Queries.ResultSetToJTable(rsResults);
    			jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(1).setPreferredWidth(75);
    			jtbResult.getColumnModel().getColumn(2).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(3).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(4).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(5).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(6).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(7).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(8).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(9).setPreferredWidth(50);
    			intWidth = 825;
    		}
    		new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }
}