import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPUpdateCourse extends JPanel {
	private JComboBox<Integer> jbAllCID;
    private JLabel lbCID;
	private JTextField tfSID;
    private JLabel lbSID;
	private JTextField tfName;
    private JLabel lbName;
	private JTextField tfTitle;
    private JLabel lbTitle;
	private JTextField tfDepartment;
    private JLabel lbDepartment;
	private JTextField tfCredits;
    private JLabel lbCredit;
    private JScrollPane spDescription;
	private JTextArea taDescription;
    private JLabel lbDescription;
    private JScrollPane spOutcomes;
	private JTextArea taOutcomes;
    private JLabel lbOutcomes;
	private JTextField tfContactEmail;
    private JLabel lbContactEmail;
	private JTextField tfContactName;
    private JLabel lbContactName;
    private JButton btnUpdate;
	
	public JPUpdateCourse(Connection con) throws SQLException {
		super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        //Make all the buttons and fields
        lbCID = new JLabel("CID (Course ID):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbCID, cs);
        
        Integer[] aryAllCID = getAllCID(con);
        jbAllCID = new JComboBox<Integer>(aryAllCID);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(jbAllCID, cs);
        
        lbSID = new JLabel("SID (School ID):");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbSID, cs);
        
        tfSID = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfSID, cs);
        
        lbName = new JLabel("Name:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbTitle = new JLabel("Title:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbTitle, cs);
        
        tfTitle = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfTitle, cs);
        
        lbDepartment = new JLabel("Department:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbDepartment, cs);
        
        tfDepartment = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfDepartment, cs);
        
        lbCredit = new JLabel("Credits:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbCredit, cs);
        
        tfCredits = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfCredits, cs);
        
        lbDescription = new JLabel("Description:");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(lbDescription, cs);
        
        taDescription = new JTextArea(5, 30);
        taDescription.setSize(new Dimension(5, 30));
        taDescription.setWrapStyleWord(true);
        taDescription.setLineWrap(true);
        spDescription = new JScrollPane(taDescription);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(spDescription, cs);
        
        lbOutcomes = new JLabel("Outcomes:");
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(lbOutcomes, cs);
        
        taOutcomes = new JTextArea(5, 30);
        taOutcomes.setSize(new Dimension(5, 30));
        taOutcomes.setWrapStyleWord(true);
        taOutcomes.setLineWrap(true);
        spOutcomes = new JScrollPane(taOutcomes);
        cs.gridx = 1;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(spOutcomes, cs);
        
        lbContactEmail = new JLabel("Contact Email:");
        cs.gridx = 0;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(lbContactEmail, cs);
        
        tfContactEmail = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 8;
        cs.gridwidth = 1;
        this.add(tfContactEmail, cs);
        
        lbContactName = new JLabel("Contact Name:");
        cs.gridx = 0;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(lbContactName, cs);
        
        tfContactName = new JTextField(30);
        cs.gridx = 1;
        cs.gridy = 9;
        cs.gridwidth = 1;
        this.add(tfContactName, cs);
        
        btnUpdate = new JButton("Update Course");
        cs.gridx = 1;
        cs.gridy = 10;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        //Load the default school into the window
        loadCourse(con);
        
        //Update the database when the update button is hit
        btnUpdate.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					updateCourse(con);
					JOptionPane.showMessageDialog(JPUpdateCourse.this, "You have successfully updated a course.", "You Did It!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPUpdateCourse.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        //Load every time a new CID is selected from the drop down
        jbAllCID.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			try {
	    				loadCourse(con);
	    			} catch (SQLException|NumberFormatException e1) {
	    				JOptionPane.showMessageDialog(JPUpdateCourse.this, "Load Failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
	    		}
        });
	}
	
	//Return every CID in a sorted array
	public Integer[] getAllCID(Connection con) throws SQLException {
		ResultSet rsAllCID = Queries.getEveryCID(con);
		
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
	
	//Return the CID 
    public int getCID() throws NumberFormatException {
    		return (int) jbAllCID.getSelectedItem();
    }
	
	//Return the SID
    public int getSID() throws NumberFormatException {
    		return Integer.parseInt(tfSID.getText().trim());
    }
    
	//Return the course name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the course title
    public String getTitle() {
    		return tfTitle.getText().trim();
    }
    
	//Return the school city
    public String getDepartment() {
        return tfDepartment.getText().trim();
    }
    
	//Return the credits
    public int getCredits() throws NumberFormatException {
        return Integer.parseInt(tfCredits.getText().trim());
    }
    
	//Return the description
    public String getDescription() {
        return taDescription.getText().trim();
    }
    
    //Return the outcomes
    public String getOutcomes() {
    		return taOutcomes.getText().trim();
    }
    
    //Return the contact email address
    public String getContactEmail() {
		return tfContactEmail.getText().trim();
    }
    
    //Return the contact name
    public String getContactName() {
		return tfContactName.getText().trim();
    }
    
    //Create a pop up for the result of the search
    public void updateCourse(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID();
    		int intSID = getSID();
    		String strName = getName().trim();
    		String strTitle = getTitle().trim();
    		String strDepartment =  getDepartment().trim();
    		int intCredits =  getCredits();
    		String strDescription =  getDescription().trim();
    		String strOutcomes = getOutcomes().trim();
    		String strContactEmail = getContactEmail().trim();
    		String strContactName = getContactName().trim();
    		
    		Queries.updateCourse(con, intCID, intSID, strName, strTitle, strDepartment, intCredits, strDescription, strOutcomes, strContactEmail, strContactName);
    }
    
    //Load the data for a course
    public void loadCourse(Connection con) throws SQLException, NumberFormatException {
    		int intCID = getCID();
    	
    		ResultSet rs = Queries.getInfoCID(con, intCID);
    		
    		if (rs.next()) {
    			tfSID.setText(rs.getString(2));
    			tfName.setText(rs.getString(3));
    			tfTitle.setText(rs.getString(4));
    			tfDepartment.setText(rs.getString(5));
    			tfCredits.setText(rs.getString(6));
    			taDescription.setText(rs.getString(7));
    			taOutcomes.setText(rs.getString(8));
    			tfContactEmail.setText(rs.getString(9));
    			tfContactName.setText(rs.getString(10));
    		} else {
    			JOptionPane.showMessageDialog(JPUpdateCourse.this, "School Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
}