import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JPUpdateSchool extends JPanel {
	private JComboBox<Integer> jbAllSID;
    private JLabel lbSID;
	private JTextField tfOID;
    private JLabel lbOID;
	private JTextField tfName;
    private JLabel lbName;
	private JTextField tfAddress;
    private JLabel lbAddress;
	private JTextField tfCity;
    private JLabel lbCity;
	private JTextField tfState;
    private JLabel lbState;
	private JTextField tfZip;
    private JLabel lbZip;
    private JButton btnUpdate;
	
	public JPUpdateSchool(Connection con) throws SQLException {
		super(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL; 
        
        //Make all the buttons and fields
        lbSID = new JLabel("SID (School ID):");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(lbSID, cs);
        
        Integer[] aryAllSID = getAllSID(con);
        jbAllSID = new JComboBox<Integer>(aryAllSID);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(jbAllSID, cs);
        
        lbOID = new JLabel("OID (FAFSA):");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(lbOID, cs);
        
        tfOID = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(tfOID, cs);
        
        lbName = new JLabel("School Name:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(lbName, cs);
        
        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(tfName, cs);
        
        lbAddress = new JLabel("School Address:");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(lbAddress, cs);
        
        tfAddress = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(tfAddress, cs);
        
        lbCity = new JLabel("School City:");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(lbCity, cs);
        
        tfCity = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        this.add(tfCity, cs);
        
        lbState = new JLabel("School State:");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(lbState, cs);
        
        tfState = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 1;
        this.add(tfState, cs);
        
        lbZip = new JLabel("School Zip Code:");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(lbZip, cs);
        
        tfZip = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 1;
        this.add(tfZip, cs);
        
        btnUpdate = new JButton("Update School");
        cs.gridx = 1;
        cs.gridy = 7;
        cs.gridwidth = 1;
        this.add(btnUpdate, cs);
        
        //Load the default school into the window
        loadSchool(con);
        
        //Update the database when the update button is hit
        btnUpdate.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			try {
					updateSchool(con);
					JOptionPane.showMessageDialog(JPUpdateSchool.this, "You have successfully updated a school.", "You Did It!", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException|NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPUpdateSchool.this, "Error 404", "Error", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        //Load every time a new SID is selected from the drop down
        jbAllSID.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
				loadSchool(con);
			} catch (SQLException|NumberFormatException e1) {
				JOptionPane.showMessageDialog(JPUpdateSchool.this, "Load Failed", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    });
	}
	
	//Return every SID in a sorted array
	public Integer[] getAllSID(Connection con) throws SQLException {
		ResultSet rsAllSID = Queries.getEverySID(con);
		
		int rows = getRowCount(rsAllSID);
		
		Integer[] result = new Integer[rows];
		
		for (int i = 0; i < rows; i++) {
			rsAllSID.next();
			result[i] = rsAllSID.getInt(1);
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
	
	//Return the SID
    public int getSID() {
    		return (int) jbAllSID.getSelectedItem();
    }
	
	//Return the OID
    public String getOID() {
    		return tfOID.getText().trim();
    }
    
	//Return the course name
    public String getName() {
    		return tfName.getText().trim();
    }
    
	//Return the school address
    public String getAddress() {
    		return tfAddress.getText().trim();
    }
    
	//Return the school city
    public String getCity() {
        return tfCity.getText().trim();
    }
    
	//Return the school state
    public String getState() {
        return tfState.getText().trim();
    }
    
	//Return the school zipcode
    public String getZipCode() {
        return tfZip.getText().trim();
    }
    
    //Create a pop up for the result of the search
    public void updateSchool(Connection con) throws SQLException, NumberFormatException {
    		int intSID = getSID();
    		String strOID = getOID().trim();
    		String strName = getName().trim();
    		String strAddress = getAddress().trim();
    		String strCity =  getCity().trim();
    		String strState =  getState().trim();
    		String strZip =  getZipCode().trim();
    		
    		Queries.updateSchool(con, intSID, strOID, strName, strAddress, strCity, strState, strZip);
    }
    
    //Load the data for a school
    public void loadSchool(Connection con) throws SQLException, NumberFormatException {
    		int intSID = getSID();
    	
    		ResultSet rs = Queries.getInfoSID(con, intSID);
    		if (rs.next()) {
    			tfOID.setText(rs.getString(2));
    			tfName.setText(rs.getString(3));
    			tfAddress.setText(rs.getString(4));
    			tfCity.setText(rs.getString(5));
    			tfState.setText(rs.getString(6));
    			tfZip.setText(rs.getString(7));
    		} else {
    			JOptionPane.showMessageDialog(JPUpdateSchool.this, "School Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    }
}