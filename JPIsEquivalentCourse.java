import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class JPIsEquivalentCourse extends JPanel {
	private JTextField tfBCCourseName;
    private JLabel lbBCCourseName;
	private JTextField tfOtherCourseName;
    private JLabel lbOtherCourseName;
	private JTextField tfOtherSchoolName;
    private JLabel lbOtherSchoolName;
	private JTextField tfOtherSchoolSID;
    private JLabel lbOtherSchoolSID;
    private JButton btnSearch;
	
	public JPIsEquivalentCourse(Connection con) {
		super(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        lbBCCourseName = new JLabel("Bellevue College Course:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbBCCourseName, cs);
        
        tfBCCourseName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(tfBCCourseName, cs);
        
        lbOtherCourseName = new JLabel("Course Name:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbOtherCourseName, cs);
        
        tfOtherCourseName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfOtherCourseName, cs);
        
        lbOtherSchoolName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbOtherSchoolName, cs);
        
        tfOtherSchoolName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfOtherSchoolName, cs);
        
        lbOtherSchoolSID = new JLabel("School ID Number:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbOtherSchoolSID, cs);
        
        tfOtherSchoolSID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfOtherSchoolSID, cs);
        
        btnSearch = new JButton("Search");
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(btnSearch, cs);
        
        btnSearch.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					showResultSet(con);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPIsEquivalentCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
	}
	
	//Return the BC course name
    public String getBCCourseName() {
    		return tfBCCourseName.getText().trim();
    }
    
	//Return the course name
    public String getOtherCourseName() {
    		return tfOtherCourseName.getText().trim();
    }
    
	//Return the school name
    public String getSchoolName() {
    		return tfOtherSchoolName.getText().trim();
    }
    
	//Return the school SID
    public String getOtherSchoolSID() {
        return tfOtherSchoolSID.getText().trim();
    }
    
    //Create a pop up for the result of the search
    public void showResultSet(Connection con) throws SQLException, NumberFormatException {
    		String strBCCourseName = "%" + getBCCourseName().trim().replace(' ', '%') + "%";
    		String strOtherCourseName = "%" + getOtherCourseName().trim().replace(' ', '%') + "%";
    		String strOtherSchoolName = "%" + getSchoolName().trim().replace(' ', '%') + "%";
    		String strOtherSID = getOtherSchoolSID().trim();
    		ResultSet rsResults;
    		JTable jtbResult;
    		int intWidth;

    		if (getOtherSchoolSID().equals("")) {
    			rsResults = Queries.isEquivilent(con, strBCCourseName, strOtherCourseName, strOtherSchoolName);
    			jtbResult = Queries.ResultSetToJTable(rsResults);
    			jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(1).setPreferredWidth(65);
    			jtbResult.getColumnModel().getColumn(2).setPreferredWidth(65);
    			jtbResult.getColumnModel().getColumn(3).setPreferredWidth(200);
    			jtbResult.getColumnModel().getColumn(4).setPreferredWidth(35);
    			jtbResult.getColumnModel().getColumn(5).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(6).setPreferredWidth(100);
    			jtbResult.getColumnModel().getColumn(7).setPreferredWidth(85);
    			jtbResult.getColumnModel().getColumn(8).setPreferredWidth(200);
    			intWidth = 900;
    		} else {
    			rsResults = Queries.isEquivilentSID(con, strBCCourseName, strOtherCourseName, Integer.parseInt(strOtherSID));      
    			jtbResult = Queries.ResultSetToJTable(rsResults);
    			jtbResult.getColumnModel().getColumn(0).setPreferredWidth(50);
    			jtbResult.getColumnModel().getColumn(1).setPreferredWidth(65);
    			jtbResult.getColumnModel().getColumn(2).setPreferredWidth(65);
    			jtbResult.getColumnModel().getColumn(3).setPreferredWidth(65);
    			jtbResult.getColumnModel().getColumn(4).setPreferredWidth(85);
    			jtbResult.getColumnModel().getColumn(5).setPreferredWidth(200);
    			intWidth = 500;
    		}
    		new PopUp(new JScrollPane(jtbResult), "Results", intWidth, 300);
    }
}